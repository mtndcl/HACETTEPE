

# Student Name:Metin

# Student ID:21526902

# BBM103 Introduction to Programming Laboratory I, Fall 2016 

# Assignment 3: Mission: Save the Earth







import sys

sozluk = open(sys.argv[1],"r")

human_message = open(sys.argv[3],"r")
  
allien_message= open(sys.argv[2],"r")

message =open("message.txt","w")
computation =open("computation.txt","w")
binarian_translate=open("binarian_translate.txt","w")
def read_dictionary(sozluk, process):

	dic={}

	for line in sozluk.readlines():

		line = line.strip("\n")

		sozlukveri =line.split(" ")

		if(process=="binarian_to_english"):

			dic[sozlukveri[0]] = sozlukveri[1]

		elif(process=="english_to_binarian"):

			dic[sozlukveri[1]] = sozlukveri[0]


	return dic



def english_to_binarian(message):

	process="english_to_binarian"

	dic = read_dictionary(sozluk, process)

	alphabet = "'abcdefghijklmnoqprstuvwyxzABCDEFGHIJKLMNOQPRSTUVYXZW"

	sayi = "0123456789"

	peace_message=""

	for line in message.readlines():

		line=line.rstrip("\n")

		line=line.strip()

		line=line.split(" ")

		for text in line:

			text = text.lower()

			if(text[0] in sayi):

				peace_message += str(decimal_to_binary(text)) + " "

			else:

				for i in text:

					if(i not in alphabet):

						text = text.strip(i)

				try:

					peace_message += dic[text] + " "

				except:

					peace_message += text + " "

		peace_message += '\n'

                    

	return peace_message



def binarian_to_english(message):

    process="binarian_to_english"

    dic = read_dictionary(sozluk, process)

    sayi="01"

    peace_message=""

    for line in message.readlines():

        line=line.rstrip("\n")

        line=line.split(" ")

        if(line[0][0] == '#' or line[0][0] == '+'):

            continue

        for text in line:

            if(text[0] in sayi):

                peace_message += str(binary_to_decimal(text)) + " "

            else:

                try:

                    peace_message += dic[text] + " "

                except:

                    peace_message += text + " "

        peace_message += '\n'

                    

    return peace_message


def binary_to_decimal(number):

    list1=[]



    for m in number:

    

        list1.extend(m)



    list2=list1[::-1]

    b=0



    for i in range(len(list2)):

        a=int(list2[i])*(2**int(i))

        b=a+b

    return b





def decimal_to_binary(number):

    number = int(number)

    binary=""

    while number!=1 and number!=0:

    

        mod=number%2

        mod1=str(mod)

        binary=mod1+binary

        number=number//2

    binary = '1' + binary

    return binary







def fix_output2(message):

    process="english_to_binarian"

    dic = read_dictionary(sozluk, process)

    



    alphabet1 = "'abcdefghijklmnoqprstuvwyxzABCDEFGHIJKLMNOQPRSTUVYXZW"

    sayi1 = "0123456789"

    age="Data about Binarian planet:\n"

    for satir in message.readlines():

        satir=satir.rstrip("\n")

        satir=satir.split(" ")

        if(satir[0][0] == '+'):

            for text in satir:

                text = text.lower()

                if(text[0] in sayi1):

                    age += str(binary_to_decimal(text)) + " "

                else:

                    for i in text:

                        if(i not in alphabet1):

                            text = text.strip(i)

                    try:

                        age += dic[text] + " "

                    except:

                        age += text + " "

            age += '\n'
       
                    

    return age

def fonk(km):
    
    km=km.split(" ")
    
    bir=km.index("hata")
    iki=km.index("bav'do")
    uc=km.index("chuqd") 
    
    lightyear=9460700000000000.0
    number=int(km[bir+1])
    son=lightyear*number

    
    
    
    out="Data about Binarian planet: \n"
    
    
    if "hata" in km:
        out+="Distance from the Earth: " +str(float(son))+" km  \n"
           
        "bav'do" in km
        out+="Planet temperature: " +str(float(km[iki+1]))+" degrees Celsius  \n"
      
        "chuqd" in km
        out+="Orbital speed: " +str(float(km[uc+1]))+" km/s  \n"
        
    return out







encrypted_message = binarian_to_english(allien_message)

allien_message.seek(0)
sozluk.seek(0)
km=fix_output2(allien_message)

a=fonk(km)

allien_message.seek(0)
sozluk.seek(0)
decrypted_message = english_to_binarian(human_message)
print(encrypted_message[:-2])
binarian_translate.write(encrypted_message)
print(a[:-2])
computation.write(a)
print(decrypted_message[:-2])
message.write(decrypted_message)
sozluk.close()
allien_message.close()
human_message.close()



