
import sys

if __name__ == '__main__':
	
	size=int(sys.argv[1])
	
	myrange_x=list(range(size)) 
	myrange_x+= list(reversed(range(size-1)))
	for x in myrange_x:
	    
		first=size-x-1
		second=x*2+1
		print(' '*first,end='')
		print('*'*second,end='')
		print()
	    


