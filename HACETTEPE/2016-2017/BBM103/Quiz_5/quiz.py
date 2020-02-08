print("quız 5-d: EXCEPTİON)")
import sys
number=open("number.txt","r")
out=open("df","w")

dic={}
for line in number.readlines():
    line=line.rstrip("\n")
    line1=line.split(";")
    dic[line1[0]]=line1[:1]

for line in number.readlines():
    line = line.rstrip("\n")


    try:
        print(line1[0],sum(line))

    except:
        print(line,"the item cannot converted to int ")


def sum(number):
    number=int(number)
    number+=number
    return number




