import sys
file=sys.argv[1]
def avgFirstThreeDigit(ListIntegers):
   
    ListIntegers=ListIntegers.split(";")
    ListIntegers[-1]= ListIntegers[-1].strip()
    counter=0
    output=[0 for i in range(len(ListIntegers))]
    for i in  ListIntegers[::-1]:
        for j in range(3):
            output[counter]+=int(i[j])
        output[counter]//=3
        counter+=1
    return output
 
file=open(file,"r")
ListIntegers=file.readline();
out=avgFirstThreeDigit(ListIntegers)
print(out)








