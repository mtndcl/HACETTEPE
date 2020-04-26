
import random 

import operator

import string

import math

class Token:


    def __init__(self, first, second,number):
    	self.first = first
    	self.second = second
    	self.number=number
    	self.prob=0
    	self.sprob=0




def dataset(path):


	senteces=[]
	data = open(path, "r")
	for sentece in data.readlines():
		sentece=sentece.strip('\n')
		sentece=sentece.split(' ', 1)
		if len(sentece)==2:
			
			sentece=sentece[1]
			sentece=sentece.translate(str.maketrans('', '', string.punctuation))

			senteces.append(sentece)
	return senteces

def Ngram(n,senteces):
	

	Tokens=[]
	size=0
	for sentece in senteces:
		sentece=sentece.strip('\n')
		sentece=sentece.split(' ')
		sentece[0]='<s>'
		sentece.append('<s>')
		for i in range(len(sentece)-1):
			have=False
			for token in Tokens:

				if sentece[i]==token.first and sentece[i+1]==token.second:
					have=True
					token.number=token.number+1
					size=size+1
					break
			if have==False:
				size=size+1
				token=Token(sentece[i],sentece[i+1],1)
				Tokens.append(token)

	

	for token in Tokens:
		token.prob=token.number/size
		token.sprob=token.sprob=(token.number+1)/(size+token.number)
	return Tokens


def Sprob(model):
	unique=len(model)

	for token in model:

		token.sprob=(token.number+1)/(unique+token.number)
def generate(lenght,number,model):

	generated=[]
	keys=list(model.keys())
	
	print(keys)

	for i in range(number):
		sentece=''
		size=0
		while size<lenght:
			index=random.randint(0, len(keys)-1)
			size=size+1

			sentece=sentece+' '+keys[index]

		generated.append(sentece)
	return generated


def get_Table(word,model):

	Table=[]

	for token in model:
		if word==token.first:
			Table.append(token)

	return Table


def generate_Bigram(lenght,number,model):
	

	senteces=[]

	for i in range(number):
		size=0


		Begin_Table=get_Table('<s>',model)
		index=random.randint(0, len(Begin_Table)-1)

		word=Begin_Table[index].second
		sentece='<s> '+word
		while size< lenght:

			Table=get_Table(word,model)
			if len(Table) ==0:
				break
			sorted_x = sorted(Table, key=operator.attrgetter('number'),reverse=True)
			select=random.randint(0, int(len(Table)/4))
			word =Table[select].second
			sentece=sentece+ ' '+ word
			
			if word=='<s>':
				break
			size=size+1
		if not word=='<s>':
			sentece=sentece+'<s>'
		senteces.append(sentece)
	for token in sorted_x:
		pass
		#print("{}:{}:{}:{}".format(token.first,token.second,token.number,token.prob))
	#print(sorted_x)

	return senteces



def Unigram(senteces):
	
	

	model={}
	
	wordnumber=0
	for sentece in senteces:
		
		sentece=sentece.split(' ')
		for word in sentece:
			if word in model:

				model[word]=model.get(word)+1
			else :
				model[word]=1
			wordnumber=wordnumber+1
		#print(sentece)for field, possible_values in fields.iteritems():
   
	for key,value in model.items():
		model[key]=value/wordnumber
	return model

def printRsult(senteces):
	for sentece in senteces:
		print(sentece)

def UnigramTable(model,poss):

	table={}
	for key ,value in model.items():
		table[key]=abs(value-poss)
	

	return table
def generateUnigram(sentece_number,lenght,model):

	
	senteces=[]
	current=0
	while  sentece_number>current:
		size=0
		sentece='<s> '
		while size<lenght:
			poss= random.randint(0,len(model)-1)

			sentece=sentece+ ' '+list(model.keys())[poss]
			size=size+1
		sentece=sentece+' <s>'
		senteces.append(sentece)
		current=current+1
	return senteces
	
def  printBigramResult(Bigramresult):

	for result in Bigramresult:
		print(result)

def TrigramModel(senteces):




	Tokens=[]
	size=0
	for sentece in senteces:
		sentece=sentece.strip('\n')
		sentece=sentece.split(' ')
		sentece[0]=''
		
		for i in range(len(sentece)-2):
			have=False
			for token in Tokens:
				first=sentece[i]+' '+sentece[i+1]
				if first==token.first and sentece[i+2]==token.second:
					have=True
					token.number=token.number+1
					size=size+1
					break
			if have==False:
				token=Token(sentece[i]+' '+sentece[i+1],sentece[i+2],1)
				Tokens.append(token)
				size=size+1


	for token in Tokens:
		token.prob=token.number/size
		token.sprob=token.sprob=(token.number+1)/(size+token.number)


	return Tokens
def  get_Next(Tokens,word):

	table=[]
	for token in Tokens:
		if token.first==word:
			table.append(token)
	table = sorted(table, key=lambda x: x.number, reverse=True)



	return table

def  generate_Trigram(model,sentece_number,lenght):
	

	
	senteces=[]
	current=0
	while sentece_number>current:
		index=random.randint(0,len(model))
		word=model[index].first
		sentece='<s> '+word
		size=0
		while lenght>size:
			
			nextword=''
			nexttable=get_Next(model,word)
			if len(nexttable)==0:
				index=random.randint(0,len(model))
				nextword=model[index].first
			else:
				newword=nexttable[0].second
			sentece=sentece+' '+newword
			word=word.split(' ')[1]+' '+newword
			size=size+1

		current=current+1
		sentece=sentece+'<s>'
		senteces.append(sentece)


	return senteces


def printTrigram(result_trigram):
	for result in result_trigram:
		print(result)



def  ProbUnigram(sentece,model):
	

	sentece=sentece.strip('<s>')
	sentece=sentece.split(' ')


	result=1
	for word in sentece:

		#print(model[word])
		result=result+math.log2(model[word])

	return result
def SprobBigram(sentece,model):
	result=1
	
	for i in range(len(sentece)-1):
		key=sentece[i]+' '+sentece[i+1]

		for token in model:
			
			if key==token.first :
				result=result+math.log2(token.sprob)
				break

	return result
			
def ProbBigram(sentece ,model):
	sentece=sentece.strip('<s>')
	sentece=sentece.split(' ')

	result=1
	fail=True
	for i in range(len(sentece)-1):
		key=sentece[i]+' '+sentece[i+1]
		
		fail=True
		for token in model:
			
			if key==token.first:
				fail=False
				
				result=result+math.log2(token.prob)
				break


		if  fail:
			result=result+math.log2(token.sprob)
		
	return result

def ProbTrigram(sentece,model):

	sentece=sentece.strip('<s>')
	sentece=sentece.split(' ')

	result=1
	fail=True
	for i in range(len(sentece)-2):
		key=sentece[i]+' '+sentece[i+1]+' '+sentece[i+2]
		
		fail=True
		for token in model:
			
			if key==token.first:
				fail=False
				
				result=result+math.log2(token.prob)
				break


		if  fail:
			result=result+math.log2(token.sprob)

	return result
		
if __name__ == '__main__':
	



	print("--------------------------------------Unigram---------------------------------------")

	senteces=dataset("test.txt");
	
	Unigram_model=Unigram(senteces)
	
	Unigramresult=generateUnigram(3,10,Unigram_model)

	printRsult(Unigramresult)
	
	print("--------------------------------------Bigram---------------------------------------")

	BigramModel=Ngram(2,senteces)

	Bigramresult=generate_Bigram(10,3,BigramModel)
	
	printBigramResult(Bigramresult)

	print("--------------------------------------Trigram---------------------------------------")

	MytrigramModel=TrigramModel(senteces)

	result_trigram=generate_Trigram(MytrigramModel,3,10)

	printTrigram(result_trigram)


	print('--------------------------------------------------------------')
	size=10

	for  sentece in Unigramresult:

		
		print("Sentece : ",sentece)
		print()
		Probunigram=ProbUnigram(sentece,Unigram_model)
		ppl=pow(2,(-1/size))*Probunigram
		print("Generated From : Unigram ,Probablity Type :Unigram  Probablity : {} ,ppl : {}".format(Probunigram,ppl))
		result_bigram=ProbBigram(sentece,BigramModel)
		ppl=pow(2,(-1/size))*result_bigram
		print("Generated From : Unigram ,Probablity Type :Bigram  Probablity : {} ,ppl : {}".format(result_bigram,ppl))
		result_trigram=ProbTrigram(sentece,MytrigramModel)
		ppl=pow(2,(-1/size))*result_trigram
		print("Generated From : Unigram ,Probablity Type :Trigram  Probablity : {} ,ppl : {}\n".format(result_trigram,ppl))
	
	for  sentece in Unigramresult:

		print("Sentece : ",sentece)
		print()
		Probunigram=ProbUnigram(sentece,Unigram_model)
		ppl=pow(2,(-1/size))*Probunigram
		print("Generated From : Bigram ,Probablity Type :Unigram  Probablity : {} ,ppl : {}".format(Probunigram,ppl))
		result_bigram=ProbBigram(sentece,BigramModel)
		ppl=pow(2,(-1/size))*result_bigram
		print("Generated From : Bigram ,Probablity Type :Bigram  Probablity : {} ,ppl : {}".format(result_bigram,ppl))
		result_trigram=ProbTrigram(sentece,MytrigramModel)
		ppl=pow(2,(-1/size))*result_trigram
		print("Generated From : Bigram ,Probablity Type :Trigram  Probablity : {} ,ppl : {}\n".format(result_trigram,ppl))
	

	for  sentece in Unigramresult:

		print("Sentece : ",sentece)
		print()
		
		Probunigram=ProbUnigram(sentece,Unigram_model)
		ppl=pow(2,(-1/size))*Probunigram
		print("Generated From : Trigram ,Probablity Type :Unigram  Probablity : {} ,ppl : {}".format(Probunigram,ppl))
		result_bigram=ProbBigram(sentece,BigramModel)
		ppl=pow(2,(-1/size))*result_bigram
		print("Generated From : Trigram ,Probablity Type :Bigram  Probablity : {} ,ppl : {}".format(result_bigram,ppl))
		result_trigram=ProbTrigram(sentece,MytrigramModel)
		ppl=pow(2,(-1/size))*result_trigram
		print("Generated From : Trigram ,Probablity Type :Trigram  Probablity : {} ,ppl : {}\n".format(result_trigram,ppl))

