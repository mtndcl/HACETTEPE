
def CheckGameover(line,size,player_answer):

	count=0

	done=1
	#Check Diagonal
	for i in range(size):
		
		if line[(i*size) + i]==player_answer:
			count+=1
		

	if count==size:
		
		return True

	count = 0

	for i in range(size):
		if line[(size - 1) * (i + 1)] == player_answer:
			count+=1
	
	if count == size:
		
		return True
	
	#Check row

	for i in range(size):
		count=0
		for x in range(size):
			if line[(i * size) + x]==player_answer:
				count+=1
		if count==size:
			
			return True
	
	#Check column

	for i in range(size):
		count=0
		for x in range(size):
			if line[i + (x*size)]==player_answer:
				count+=1
		if count==size:
			
			return True

	return False


	

def PrintMatrix(line,size):
	

	
	for i in range(len(line)):

		print("{}    ".format(line[i]),end="")
		if (i+1)%size==0:
			print()


	


def Getnumber():

	size=0
	while 1:
		
		size = input()
		try:
			size=int(size)
			break

		except :
			print("Please a integer value")
	return  size
	 
if __name__ == '__main__':



	print("What Size Game Gopy?",end="")
	size=Getnumber()

	player=1

	
	line=[]
	for i in range(size*size):

		
		line.append(i)

	PrintMatrix(line,size)

	while 1:

		
		print("Player {} turn--> ".format(player),end="")
		selected_index=Getnumber()
		if selected_index>=0 and selected_index<size*size:
			  
			if player==1:
				change=1
				if line[selected_index]=="X":
					print("You have made this choice before")
					change=0
				if line[selected_index]=="O":
					print("The other player select this cell before")
					change=0
					
				player=2
				if change==1:
					line[selected_index]="X"
			else:
				change=1
				if line[selected_index]=="O":
					print("You have made this choice before")
					change=0
				if line[selected_index]=="X":
					print("The other player select this cell before")
					change=0

				
				player=1
				if change==1:
					line[selected_index]="O"
		else:
			print("{} Does noy have such a cell".format(selected_index))
		PrintMatrix(line,size)


		
		if player==1 and CheckGameover(line,size,"O"):
			print("Winner O")
			break
		elif player==2 and CheckGameover(line,size,"X"):
			print("Winner X")
			break
			
