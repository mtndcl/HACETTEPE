import sys


myinput=sys.argv[1]

myinput.replace('"', '')

numbers=myinput.split(',')

sum_of_even_number=0
sum_of_all_number=0
even_number=0
print('Even Numbers: "',end="")
for number in numbers:
	number=int(number)
	
	if number%2==0 and number>0:
		even_number+=1
		sum_of_even_number+=number
		if even_number==1:
			print("{}".format(number),end="")
		else:
			print(",{}".format(number),end="")
	if number>0:
		sum_of_all_number+=number
print('"\nSum of Even Numbers: {}'.format(sum_of_even_number))
rate=round(sum_of_even_number/sum_of_all_number,3)
print("Even Number Rate: {} ".format(rate))

