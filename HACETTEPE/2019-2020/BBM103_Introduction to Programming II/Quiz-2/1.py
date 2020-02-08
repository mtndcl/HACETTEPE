import sys


a=int(sys.argv[1])
b=int(sys.argv[2])
c=int(sys.argv[3])


delta=(b**2)-(4*a*c)

if delta>0:

	print("There are two solutions")
	one=(-b+(delta**(1/2)))/(2*a)
	two=(-b-(delta**(1/2)))/(2*a)
	print("Solution(s): {}  {} ".format(one,two))
elif delta==0:
	one=(-b+(delta**(1/2)))/(2*a)
	
	print("Solution: {}  ".format(one))
else:
	print("There is not any solutions")
