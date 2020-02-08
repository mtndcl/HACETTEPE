
import sys

class Maze:
   
    def __init__(self,x,y,maze,Startx,Starty,Finishx,Finishy,health,ishealth):
        self.solution = [ [ 0 for j in range(y) ] for i in range(x) ] 
        self.maze=maze
        self.x=x
        self.y=y
        self.Startx=Startx
        self.Starty=Starty
        self.Finishx=Finishx
        self.Finishy=Finishy
        self.health=health
        self.currenthealth=health
        self.ishealth=ishealth


def printSol(maze,resultfile):


    for i in range(len(maze)):
        for j in range(len(maze[i])):
            if j+1==len(maze[i]):
                resultfile.write('{} '.format(maze[i][j]))
            else:
                resultfile.write('{},'.format(maze[i][j]))
        resultfile.write('\n')
        


def solveMaze(mymaze,resultfile):

    x,y=mymaze.Startx,mymaze.Starty
    if isSafeToGo(mymaze, x+1, y):

        if findPath(mymaze, mymaze.Startx, mymaze.Starty, "down"):
            printSol(mymaze.solution,resultfile)
            return 
    elif isSafeToGo(mymaze, x-1, y):
        if findPath(mymaze, mymaze.Startx, mymaze.Starty, "up"):
            printSol(mymaze.solution,resultfile)
            return 
    elif isSafeToGo(mymaze, x, y+1):
        if findPath(mymaze, mymaze.Startx, mymaze.Starty, "right"):
            printSol(mymaze.solution,resultfile)
            return
    elif isSafeToGo(mymaze, x, y-1):
        if findPath(mymaze, mymaze.Startx, mymaze.Starty, "left"):
            printSol(mymaze.solution,resultfile)
            return 
    else:
        resultfile.write('No Path')
        return
def  findPath(mymaze, x, y, direction):
        
        if x==mymaze.Finishx and y==mymaze.Finishy:
            mymaze.solution[x][y] ='F'
            mymaze.currenthealth-=1
            return True
        
        if isSafeToGo(mymaze, x, y):
            mymaze.maze[x][y]='W'
            if mymaze.Startx==x and mymaze.Starty==y:
                mymaze.solution[x][y] ='S'
            else:
                mymaze.solution[x][y] = 1;
            mymaze.currenthealth-=1
            if direction!="up" and findPath(mymaze, x+1, y, "down"):

               
                return True
            
            elif direction!="left"and findPath(mymaze, x, y+1,"right"):
               
                return True
            
            elif direction!="down" and findPath(mymaze, x-1, y, "up"):
                
                return True
            
            elif direction!="right" and  findPath(mymaze, x, y-1, "left") :
              
                return True
            
            
            mymaze.solution[x][y] = 0;
            mymaze.maze[x][y]='P'
            mymaze.currenthealth+=1
            return False;
        
        return False;
def isSafeToGo(mymaze,x,y):
       
    if  x >= 0 and y >= 0 and x < mymaze.x  and y < mymaze.y and mymaze.maze[x][y] != 'W':

        if mymaze.maze[x][y] == 'H':
            mymaze.currenthealth+=mymaze.health
        if mymaze.ishealth and mymaze.currenthealth<=0:
            return False
            
        return True
        
    return False
def split(word): 
    return [char for char in word]

def Getpoint(maze,point):

    for i in range(len(maze)):
        for j in range(len(maze[0])):
            if maze[i][j] == point:
                return i,j

    return 0,0
def CreateMaze(file):
    maze=[]
    for line in file.readlines():
        line=line.strip('\n')
        
        line=split(line)
        maze.append(line)
    return maze
if __name__ == '__main__':
    

    filename=sys.argv[1]
    file=open(filename,'r')
    resultfile=open(sys.argv[4],'w')
    maze=CreateMaze(file)

    Startx,Starty=Getpoint(maze,'S')
    Finishx,Finishy=Getpoint(maze,'F')
    mymaze = Maze(len(maze), len(maze[0]),maze,Startx,Starty, Finishx,Finishy,0,False)

    resultfile.write('----------------------NORMAL MAZE---------------------------\n')
    solveMaze(mymaze,resultfile);

    #############################
    filename=sys.argv[2]
    file=open(filename,'r')

    health=int(sys.argv[3])


    health_maze=CreateMaze(file)
    Startx,Starty=Getpoint(health_maze,'S')
    Finishx,Finishy=Getpoint(health_maze,'F')
    mymaze = Maze(len(health_maze), len(health_maze[0]),health_maze,Startx,Starty, Finishx,Finishy,health,True)
    resultfile.write('----------------------NORMAL MAZE WITH HEALTH---------------------------\n')
    solveMaze(mymaze,resultfile);

    resultfile.close()