
########HMM   Class
class HMM:

    def __init__(self):
        pass

    ####Read train dataset than set senteces,tags ,tag of first word in sentece and exisitng states
    def Dataset(self,foldername):

        trainFolder=open(foldername,'r')

        sentences=[]
        tags=[]

        sentence=[]
        tag=[]
        
        states=set()

        firstSentenceTag=[]
        for line in trainFolder.readlines():

            if len(line)==1:

                sentences.append(sentence)
                tags.append(tag)
                sentence=[]
                tag=[]
            else :
                line=line.strip('\n')
                line=line.strip()
            
                line=line.split(' ')
                sentence.append(line[0])
                firstSentenceTag.append(line[-1])
                tag.append(line[-1])
                states.add(line[-1])
        self.sentences=sentences
        self.tags=tags
        self.firstSentenceTag=firstSentenceTag
        self.states=states
    
    ####Calculate initial probability 
    def Initial_probability(self):
        b = {}
        for item in self.firstSentenceTag:
            b[item] = b.get(item, 0) + 1

        sumall=sum(b.values())

        for item in b:
            b[item]=b[item]/sumall
        self.initial_pro=b
    ####Calculate Transition probability 
    def Transition_probability(self):
        b={}
        for tag in self.tags:
            for i in range(len(tag)-1):
                key=(tag[i],tag[i+1])
                if  key  in b:
                    b[key]=b.get(key)+1
                else :
                    b[key]=1
        
        sumall=sum(b.values())

        for item in b:
            b[item]=b[item]/sumall
        self.transition_pro=b
    ####Check in the list have the dictonary with key
    def exact(self,mylist,tag):

        for a in mylist:
            if tag in a :
                return a
        return None
    ####Calculate Emission probability 
    def Emission_probability(self):
    
        b={}
        tagdic={}
        for sentence, tagarray in zip(self.sentences,self.tags):
            for word, tag in zip(sentence,tagarray):
                key=(tag,word)
                if  key  in b:
                    b[key]=b.get(key)+1
                else :
                    b[key]=1
                if tag in tagdic:
                    tagdic[tag]=tagdic.get(tag)+1
                else:
                    tagdic[tag]=1
        for pair in b:

            b[pair]=b[pair]/tagdic[pair[0]]
        self.emission_pro=b

    def getEmission(self,key):
        if key in self.emission_pro:
            return self.emission_pro[key]
        else :
            return 0
    def getTransition(self,key):
        if key in self.transition_pro:
            return self.transition_pro[key]
        else :
            return 0
    def getInitial(self,key):
        if key in self.initial_pro:
            return self.initial_pro[key]
        else :
            return 0

####Viberti Algorithm
def viterbi(sentence, model):
   

    tags=model.states
    tracklist = [{}]
 
    path = {}

    for tag in tags:
        tracklist[0][tag] = model.getInitial(tag) * model.getEmission((tag,sentence[0]))
        path[tag] = [tag]
    for i in range(1,len(sentence)):
        tracklist.append({})
        new_path = {}
        for tag in tags:

            (probability, possible_state) = max(
            [(tracklist[i-1][item] * model.getTransition((item,tag)) 
            * model.getEmission((tag,sentence[i])), item) for item in tags])
            tracklist[i][tag] = probability
            new_path[tag] = path[possible_state] + [tag]

        path = new_path
    (probability, tag) = max([(tracklist[len(sentence) - 1][tag], tag) for tag in tags])
  
    return path[tag]
def ReadTestDataset(trainFolderName):

    sentences=[]
    sentence=''
    tokens=[]
    firstword=True
    trainFolder=open(trainFolderName,'r');
    token=[]
    for line in trainFolder.readlines():
        line=line.strip('\n')
        line=line.split(' ')
        sentence=sentence+' '+line[0]

        token.append(line[-1])

        if len(line)==1:

            sentences.append(sentence)
            del token[-1:]
            tokens.append(token)
            sentence=''
            token=[]
                    
    sentences.append(sentence)
    del token[-1:]
    tokens.append(token)
    return sentences, tokens

def accuracy(predicted,testtokens):
    
    right=0
    total=0
    for pre, test in zip(predicted,testtokens):
        
        for tagPredict, ecaxtTag in zip(pre,test):
            total=total+1
            if tagPredict==ecaxtTag:
                right=right+1
    return right/total

if __name__ == '__main__':
    
    HMM_Model=HMM()

    HMM_Model.Dataset('train.txt')

    HMM_Model.Initial_probability()
    HMM_Model.Transition_probability()
    HMM_Model.Emission_probability()


    testsentences,testtokens=  ReadTestDataset('test.txt')
    predicted=[]
    for a  in testsentences:
        sentence=a.strip(' ')
        sentence=sentence.split(' ')
        predicted.append(viterbi(sentence,HMM_Model))
    print('Accuracy is : {} '.format(accuracy(predicted,testtokens)))