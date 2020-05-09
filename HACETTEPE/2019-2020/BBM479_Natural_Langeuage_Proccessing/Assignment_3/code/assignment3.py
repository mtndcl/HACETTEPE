from random import randint



"""
Cerating my model
"""
class Model():
    """Define structure we will use"""
    def __init__(self):
        self.rulelist=[]
        self.nonterminal=[]
        self.generatedSentences=[]
        self.words={}
    """
    Get Rules from input file
    """
    def rules(self,path):
        file=open(path)
        for line in file.readlines():
            if line!='\n' and line[0]!='#':
                line=line.split()
                for tok in line:
                    if '#' in tok:
                        line = line[0:line.index(tok)]
                        break
                self.nonterminal.append(line[0])
                self.rulelist.append(line)
                if len(line) == 2:
                    self.words[line[1]]=line[0]
    """
    Generate Sentence randomly with recursion if leght over 10 stop adding new word
    """
    def CreateSentence(self, symbol, sentence,lenght):
        fitrules = []
        if symbol not in self.nonterminal:
            sentence.append(symbol)
        else:
            for rule in self.rulelist:
                if rule[0] == symbol:
                    fitrules.append(rule)
            #random select rule in possible rule
            selectrandomindex = randint(0, len(fitrules)-1)
            selecetrule = fitrules[selectrandomindex]

            for selectsymbol in selecetrule[1:len(selecetrule)]:
                if lenght<10:
                    self.CreateSentence( selectsymbol, sentence,lenght)
                    lenght+=1
                else:
                    break
    """
    Determine how many random sentences will be generated then generated and store in generatedSentences list
    """
    def  randsentence(self,newfilepath):
        file=open(newfilepath,'w')
        for i in range(10):
            sentence=[]
            self.CreateSentence('ROOT', sentence,0)
            sentence=' '.join(sentence)
            file.write('{}\n'.format(sentence))
            self.generatedSentences.append(sentence)
        file.close()
    """
    Get terminal of words in as array
    """
    def taggedSentence(self,sentence):

        tags=[]
        sentence=sentence.split()
        for word in sentence:
            tag=self.words.get(word)
            if tag!=None:
                tags.append(tag)
        return tags
    def printmatrix(self,matrix):
        for row in matrix:
            print(row,'\n')
   """
   When creating matrix down row union rules.
   """
    def combinationRules(self, colonresult, wayresult):
        combination = []
        if colonresult == '':
            combination.append(wayresult.strip())
        for cell in colonresult.split():
            cell=cell.strip()
            if wayresult == '':
                combination.append(cell)
            for cell in wayresult.split():
                if cell == '':
                    combination.append(cell)
                else:
                    combination.append(cell + ' ' + cell)
        return combination
    """
    Get rules for matrix
    """
    def FindRule(self, combination):
        rules = self.rulelist
        rule_result = ''
        for rule in rules:
            for result in combination:
                if len(rules)==3:
                    if result != '' and result == rule[1] + ' ' + rule[2]:
                        result += rule[0].strip() + ' '
                else:
                    if result != '' and result == rule[1]:
                        rule_result += rule[0].strip() + ' '
        return rule_result.strip()
    #############################
    """
    Starting parsing word then set matrix cell
    """
    def parse(self, matrix, cW, cH):
        width=cW
        height=cH

        wayresult = []
        for i in range(cH):
            result = matrix[height-1][width+1]
            height=height- 1
            width = width+1
            wayresult.append(result)
        colonresult = []
        for i in range( cH):
            result = matrix[i][cW]
            colonresult.append(result)
        exactresult = ''
        for i in range(0, len(wayresult)):
            exactresult += self.FindRule(self.combinationRules(colonresult[i], wayresult[i])) + ' '
        out = ''
        for word in exactresult.split():
            out += word.strip() + ' '
        out = out.strip()

        matrix[cH][cW] = out.strip()

        return matrix
    """
    CYKParser function instance
    """
    def CYKParser(self,sentence):
        nonterminal=self.taggedSentence(sentence)
        size=len(nonterminal)
        matrix = [['' for x in range(size)] for y in range(size)]
        for i in range(size):
            matrix[0][i] = nonterminal[i]
        cW = 0
        cH = 1
        for ch in range(cH, size):
            for cw in range(cW, size-cH):
                matrix = self.parse(matrix, cw, ch)
            cH += 1
        
        if 'S' in matrix[size-1][0] and (sentence.endswith('.') or sentence.endswith('!') or (sentence.endswith('?') and sentence.startswith("is it true that"))):
            return True
        return False
if __name__ == '__main__':
    
    #Initlaze model
    model=Model()
    ##Determine Rule Now all  rule in self.rulelist
    model.rules('cfg.gr')
    ##Create Random sentence and write them into txt file. Rules already in model so dont need send as argument for this function
    model.randsentence('sentences.txt')

    for sentence in model.generatedSentences:
        #print sentence and is it True or not
        print('Sentence : {}  Result {}'.format(sentence,str(model.CYKParser(sentence))))
    
  