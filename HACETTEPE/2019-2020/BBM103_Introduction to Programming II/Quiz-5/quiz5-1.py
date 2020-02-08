
import sys

def draw(began_point, end_point, mid_point):
	if end_point>began_point :
		if mid_point>began_point:
			if end_point==began_point:
				print(' '*(mid_point-began_point)+'*'*((began_point<<1)+1),end='')
			else:
				print(' '*(mid_point-began_point)+'*'*((began_point<<1)+1))
		else:
			if end_point==began_point:
				print(' '*(began_point-mid_point)+'*'*((end_point-began_point<<1)-1),end='')
			else:
				print(' '*(began_point-mid_point)+'*'*((end_point-began_point<<1)-1))
		draw(began_point + 1, end_point, mid_point)

if __name__ == '__main__':
	
	size=(int(sys.argv[1])*2)-1

	draw(0,size,size >> 1)

