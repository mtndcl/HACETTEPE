file=open("input.txt","r")
print("quiz 3")
dict={}
def averange(input_list):
    input_list=input_list.split(";")
    input_list[-1]=input_list[-1].strip()
    count=0
    total=0
    for i in input_list[:]:
    
       if int(i)%5==0:
           i=int(i)
           total=total+i
           count=count+1
           avrg1=(total/count)
       if int(i)%7==0:
           i=int(i)
           total=total+i
           count=count+1
           avrg2=(total/count)
           out="divisible5 {} divisible7{}"
           print(out.format(avrg1,avrg2))