# -*- coding: utf8 -*-


import sys
import time
import threading


def ReadFile(path):

    folder = open(path, 'r',encoding='utf-8')

    dictonary={}
    for line in folder.readlines():
        

        line=line.rstrip()
        line=line.split(":")
        
        dictonary[line[0]]=line[1].split(",")
    return dictonary

def Up(word):
    
    data={'ı':'I','i':'İ','ç':'Ç','ş':'Ş','ö':'Ö','ü':'Ü','ğ':'Ğ','j':'J'}
    result=''
    for letter in word:
        
        if letter in 'ıiçşöüğj':
            result+=data[letter]
        else:
            result+=letter.upper()

    return result
def Down(word):
    
    data={'I':'ı','İ':'i','Ç':'ç','Ş':'ş','Ö':'ö','Ü':'ü','Ğ':'ğ','J':'j'}
    result=''
    for letter in word:
        
        if letter in 'IİÇŞÖÜĞJ':
            result+=data[letter]
        else:
            result+=letter.upper()

    return result
def countTo():
   
    time.sleep(5)
    print("Time is Up")

def is_Guessed(Guessed_Word,user_input):

    user_input=Up(user_input)
    for word in Guessed_Word :

        word=Up(word)
        if word==user_input:
            return True
    return False

def FindScore(LettersValue,Guessed_Word):

    score=0
    for word in Guessed_Word:
        word=Up(word)
        charvalue=0
        for char in list(word):

            try :
                charvalue+=int(LettersValue[char][0])
            except :
                charvalue+=0
        score+=len(word)*charvalue
    return score

def  is_ValidWord(validwords,user_input):
    
    for word in validwords:
        word=Up(word)
        user_input=Up(user_input)
       
        if user_input==word:
            return True
    return False
if __name__== "__main__":


    if len(sys.argv)!=3:
        print("You must write two arguments for this program")
    Puzzle=ReadFile(sys.argv[1])
    LettersValue=ReadFile(sys.argv[2])

    #timer = threading.Thread(target=countTo)
   



   
   
    start = time.time()
    elapsed =0
    for shufflekey in Puzzle:
        print("Shuffled letters are : {} Please guess words for these letters with minimun three letters ".format(shufflekey))
        
        Guessed_Word=[]
        start = time.time()
        elapsed =0
        while 1:
            print("Gussed Word : ",end="")
            user_input=input()
           
            end = time.time()

            elapsed = end-start
            elapsed=int(elapsed)
            remaintime=30-elapsed;
            if remaintime<0:
                remaintime=0
            if remaintime>0:
                if len(user_input)<3:
                    print("Too short try again")
                
                elif is_Guessed(Guessed_Word,user_input):
                    print("This word guessed before")
                elif not is_ValidWord(Puzzle[shufflekey],user_input):
                    print("Your guessed word is not a valid word")
                else :
                    Guessed_Word.append(user_input)
                
                  
            

        
            print("You have {} time ".format(remaintime));
            if remaintime==0:
                
                score=FindScore(LettersValue,Guessed_Word)
                print("Score for {} is {} and guessed words are: ".format(shufflekey,score),end="")
                for i in range(len(Guessed_Word)):

                    if i+1==len(Guessed_Word):
                        
                        print("{}".format(Guessed_Word[i]));
                    else :
                        print("{}-".format(Guessed_Word[i]),end="");
                if len(Guessed_Word)==0:
                    print()
                break

        