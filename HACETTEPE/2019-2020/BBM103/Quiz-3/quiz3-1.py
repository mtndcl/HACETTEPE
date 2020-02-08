

import sys


number=sys.argv[1]
power=sys.argv[2]
#Output  25 = 32 = 3 + 2 = 5

try:
	number=int(sys.argv[1])
	power=int(sys.argv[2])
except :
	print("Pls give two integer value")
result=number**power
print("Output : {}^{} = {} ".format(number,power,result),end="" )

while 1:
	
	result=str(result)
	lenght=len(result)
	if lenght==1:
		print()
		break
	else:
		print("= ",end="" )
		value=0
		mylist=list(str(result))
		for i in range(len(mylist)):
			value+=int(mylist[i])
			print("{} ".format(str(mylist[i])),end="" )
			if i!=len(mylist)-1:
				print("+ ",end="" )
		print("= {} ".format(value),end="" )
		result=value
