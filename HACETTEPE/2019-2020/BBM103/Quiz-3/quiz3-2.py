

import sys



def Diff(list1, list2): 
    return [i for i in list1 + list2 if i not in list1 or i not in list2]

mylist=sys.argv[1].split(",")

mylist = list(map(int, mylist))


lucky_index=0

count=0;
victims=[]
for i in range(len(mylist)):
	

	count+=1
	if count==2:
		
		victims.append(mylist[i])
		count=0
mylist=Diff(mylist,victims)
while 1:
	jump_value=mylist[lucky_index+1]

	victims=[]

	count=0;
	over=1

	for i in range(len(mylist)):
		count+=1;

		if count==jump_value:
			
			over=0
			victims.append(mylist[i])
			count=0
	mylist=Diff(mylist,victims)
	
	lucky_index+=1
	if over ==1:
		print("Output :",end="")
		for x in mylist:
			print("{} ".format(str(x)),end="")
		print()
		break

