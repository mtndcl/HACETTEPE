#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


#define MAX 30
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int read_file(FILE *inp, int maze[][MAX]){

    char  b[MAX];
    int size = 0, i, j;
    
    fscanf(inp, "%s", b);
    
    size = strlen(b);
    
    for(i = 0; i<size; i++){
         maze[0][i] = b[i];
         
    }
         
    //Put character in the maxtris
    for(i=1; i < size; i++){
            fscanf(inp, "%s", b);
            for(j = 0; j<size; j++)
                  maze[i][j] = b[j];
               
    }
    return size;
}


int FindStartLocation( int maze[][MAX],int size){
    
    
    
    int i;
    //Find colunm of start location
    for(i= 0; i<size; i++)
             if(maze[0][i]=='S'){
                  return i;
   }
 
}



int Walk(int maze[][MAX],int row,int column,int keys[MAX],int numberofkey,int checkedopendoor,FILE *path){
    
    
    int i;
    int a,b;
    
    //printf(" row = %d ; column = %d: %c \n",row , column, maze[row][column]);

    
     //Check you find exit or not
    if(maze[row][column]=='E'){
        
       
        fprintf(path, " EXIT"); 
       // printf("EXIT");
    }
    else{
    
         //CHECK DOOR YOU HAVE KEY OR NOT(Only the door of EAST)  
         if(maze[row][column+1]>='A' &&  maze[row][column+1]<='Z'  &&  !checkedopendoor &&  maze[row][column+1]!='E' ){
            int opened=0;
            checkedopendoor=1;
             //Try to get key
             
            
            for(i=0;i<numberofkey;i++){
                if(keys[i]-32==maze[row][column+1]){
                    opened=1;
                    }
            }
            if(opened){
                
               
                fprintf(path, " E"); 
                fprintf(path, " %c",maze[row][column+1]);
                maze[row][column]='2';
                column++;
              
                
            }
            return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 
            
         }
         //CHECK DOOR YOU HAVE KEY OR NOT(Only the door of SOUTH) 
         else if(maze[row+1][column]>='A' &&  maze[row+1][column]<='Z'  &&  !checkedopendoor && maze[row+1][column]!='E'){
            int opened=0;
            checkedopendoor=1;
             //Try to open the door
            for(i=0;i<numberofkey;i++){
                if(keys[i]-32==maze[row+1][column]){
                    opened=1;
                    }
            }
            if(opened){
                fprintf(path, " S"); 
                fprintf(path, " %c",maze[row+1][column]);
                maze[row][column]='2';
                row++;
                
                
            }
            return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 
           
         }
          //CHECK DOOR YOU HAVE KEY OR NOT(Only the door of WEST) 
         else  if(maze[row][column-1]>='A' &&  maze[row][column-1]<='Z'  &&  !checkedopendoor && maze[row][column-1]!='E'   ){
            int opened=0;
            checkedopendoor=1;
             //Try to open the door 
            for(i=0;i<numberofkey;i++){
                if(keys[i]-32==maze[row][column-1]){
                    opened=1;
                }
            }
            if(opened){
                 fprintf(path, " W"); 
                fprintf(path," %c ",maze[row][column-1]);
                maze[row][column]='2';
                column--;
               
                
            }
            return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 
            
         }
         //CHECK DOOR YOU HAVE KEY OR NOT(Only the door of NORTH) 
         else  if(maze[row-1][column]>='A' &&  maze[row-1][column]<='Z' &&  !checkedopendoor  &&  maze[row-1][column]!='E'   ){
            int opened=0;
            checkedopendoor=1;
             //Try to open the door 
            for(i=0;i<numberofkey;i++){
                if(keys[i]-32==maze[row-1][column]){
                    opened=1;
                }
            }
            if(opened){
                 fprintf(path, " N"); 
                 fprintf(path, " %c",maze[row-1][column]);
                 maze[row][column]='2';
                row--;
               
                
            }
            return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 
         }
         //If you move to a new blok first time (Only for EAST)
        else if(maze[row][column+1]=='0'||  maze[row][column+1]>='a' &&  maze[row][column+1]<='z' ||  maze[row][column+1]=='E' ){
                   checkedopendoor=0;
                   fprintf(path, " E"); 
                    //Try to get key 
                   if(maze[row][column+1]>='a' &&  maze[row][column+1]<='z'){
                      
                      keys[numberofkey]=maze[row][column+1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column+1]);
                      
                  }
                       
                 
                  maze[row][column]='2';
                  column++;
                                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok first time (Only for SOUTH)
        else if(maze[row+1][column]=='0'||  maze[row+1][column]>='a' &&  maze[row+1][column]<='z' || maze[row+1][column]=='E' ){
                   checkedopendoor=0;
                    fprintf(path, " S"); 
                     //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row+1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row+1][column]);
                   }
                  
                  maze[row][column]='2';
                  row++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok first time (Only for WEST)
        else if(maze[row][column-1]=='0'||  maze[row][column-1]>='a' &&  maze[row][column-1]<='z' || maze[row][column-1]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " W"); 
                    //Try to get key 
                   if(maze[row][column-1]>='a' &&  maze[row][column-1]<='z'){
                      
                      keys[numberofkey]=maze[row][column-1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column-1]);
                      
                  }
                  maze[row][column]='2';
                  column--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok first time (Only for NORTH)
        else if(maze[row-1][column]=='0'||  maze[row-1][column]>='a' &&  maze[row-1][column]<='z'  || maze[row-1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " N"); 
                    //Try to get key 
                   if(maze[row-1][column]>='a' &&  maze[row-1][column]<='z'){
                      
                      keys[numberofkey]=maze[row-1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row-1][column]);
                      
                      
                  }
                  maze[row][column]='2';
                  row--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok SECOND time (Only for EAST)
         else if(maze[row][column+1]=='2'||  maze[row][column+1]>='a' &&  maze[row][column+1]<='z'  || maze[row][column+1]=='E' ){
                   checkedopendoor=0;
                    fprintf(path, " E"); 
                     //Try to get key 
                   if(maze[row][column+1]>='a' &&  maze[row][column+1]<='z'){
                      
                      keys[numberofkey]=maze[row][column+1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column+1]);
                  }
                  maze[row][column]='3';
                  column++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok SECOND time (Only for SOUTH)
        else if(maze[row+1][column]=='2'||  maze[row+1][column]>='a' &&  maze[row+1][column]<='z'   || maze[row+1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " S");
                    //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row+1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row+1][column]);
                   }
                  
                  maze[row][column]='3';
                  row++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok SECOND time (Only for WEST)
        else if(maze[row][column-1]=='2'||  maze[row][column-1]>='a' &&  maze[row][column-1]<='z'   || maze[row][column-1]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " W"); 
                    //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row][column-1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column-1]);
                  }
                  maze[row][column]='3';
                  column--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok SECOND time (Only for NORTH)
        else if(maze[row-1][column]=='2'||  maze[row-1][column]>='a' &&  maze[row-1][column]<='z'      || maze[row-1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " N"); 
                    //Try to get key 
                   if(maze[row-1][column]>='a' &&  maze[row-1][column]<='z'){
                      
                      keys[numberofkey]=maze[row-1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row-1][column]);
                  }
                  maze[row][column]='3';
                  row--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }                                 
        //If you move to a new blok THIRD time (Only for EAST)
         else if(maze[row][column+1]=='3'||  maze[row][column+1]>='a' &&  maze[row][column+1]<='z'  || maze[row][column+1]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " E"); 
                    //Try to get key 
                   if(maze[row][column+1]>='a' &&  maze[row][column+1]<='z'){
                      
                      keys[numberofkey]=maze[row][column+1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column+1]);
                  }
                  maze[row][column]='4';
                  column++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok THIRD time (Only for SOUTH)
        else if(maze[row+1][column]=='3'||  maze[row+1][column]>='a' &&  maze[row+1][column]<='z'   || maze[row+1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " S"); 
                    //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row+1][column];
                      numberofkey++;
                      fprintf(path,  " %c", maze[row+1][column]);
                   }
                  
                  maze[row][column]='4';
                  row++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok THIRD time (Only for WEST)
        else if(maze[row][column-1]=='3'||  maze[row][column-1]>='a' &&  maze[row][column-1]<='z'   || maze[row][column-1]=='E'){
                   checkedopendoor=0;
                    fprintf(path, " W"); 
                     //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row][column-1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column-1]);
                  }
                  maze[row][column]='4';
                  column--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok THIRD time (Only for NORTH)
        else if(maze[row-1][column]=='3'||  maze[row-1][column]>='a' &&  maze[row-1][column]<='z'   || maze[row-1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " N"); 
                    //Try to get key 
                   if(maze[row-1][column]>='a' &&  maze[row-1][column]<='z'){
                      
                      keys[numberofkey]=maze[row-1][column];
                      numberofkey++;
                      fprintf(path,  " %c", maze[row-1][column]);
                  }
                  maze[row][column]='4';
                  row--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        
        //If you move to a new blok FOURTH time (Only for EAST)   
          else if(maze[row][column+1]=='4'||  maze[row][column+1]>='a' &&  maze[row][column+1]<='z'   || maze[row][column+1]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " E"); 
                    //Try to get key 
                   if(maze[row][column+1]>='a' &&  maze[row][column+1]<='z'){
                      
                      keys[numberofkey]=maze[row][column+1];
                      numberofkey++;
                      fprintf(path, " %c ", maze[row][column+1]);
                  }
                  maze[row][column]='5';
                  column++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok FOURTH time (Only for SOUTH)
        else if(maze[row+1][column]=='4'||  maze[row+1][column]>='a' &&  maze[row+1][column]<='z'  || maze[row+1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " S"); 
                    //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row+1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row+1][column]);
                   }
                  
                  maze[row][column]='5';
                  row++;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok FOURTH time (Only for WEST)
        else if(maze[row][column-1]=='4'||  maze[row][column-1]>='a' &&  maze[row][column-1]<='z'  || maze[row][column-1]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " W"); 
                    //Try to get key 
                   if(maze[row+1][column]>='a' &&  maze[row+1][column]<='z'){
                      
                      keys[numberofkey]=maze[row][column-1];
                      numberofkey++;
                      fprintf(path, " %c", maze[row][column-1]);
                  }
                  maze[row][column]='5';
                  column--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //If you move to a new blok FOURTH time (Only for NORTH)
        else if(maze[row-1][column]=='4'||  maze[row-1][column]>='a' &&  maze[row-1][column]<='z'    || maze[row-1][column]=='E'){
                   checkedopendoor=0;
                   fprintf(path, " N");
                   //Try to get key 
                   if(maze[row-1][column]>='a' &&  maze[row-1][column]<='z'){
                      
                      keys[numberofkey]=maze[row-1][column];
                      numberofkey++;
                      fprintf(path, " %c", maze[row-1][column]);
                  }
                  maze[row][column]='5';
                  row--;                                                    
               return Walk(maze,row,column,keys,numberofkey,checkedopendoor,path); 

        }
        //SORRY,YOU DONT HAVE ANY WAY
        else{
             fprintf(path, " DON'T HAVE ANY WAY  FOR EXIT");
        }                             
    }


}



int main(int argc, char *argv[]) {
   
   
    int Locationrow=0;
    int Locationcolumn;
    int numberofkey=0;
    int  checkedopendoor;
    
   
    
   
    
    int maze[MAX][MAX];
    int keys[MAX];
    int size, i, j;
   
   
   FILE *path = NULL;

   path = fopen("path.txt" ,"w");

   
    //FILE *inp = fopen("maze2.txt","r");
    FILE *inp = fopen(argv[1], "r"); // "r" for read
    
    //Read file and put all char in maxtris
    size = read_file(inp, maze);
    
    
    //Find start location 
    Locationcolumn=FindStartLocation(maze,size);
     
  
    
    
   fprintf(path, "START");
   //Try to find exit
   Walk(maze,Locationrow,Locationcolumn,keys,numberofkey,checkedopendoor, path);
   
   
   //Close file
  fclose(path);
  fclose(inp);
  return 0;
}
