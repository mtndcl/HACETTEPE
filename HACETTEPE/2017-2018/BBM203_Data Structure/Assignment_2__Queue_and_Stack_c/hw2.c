#include <stdio.h>
#include <stdlib.h>
#define MAX 500
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
//define a machine
typedef struct {

char *stack;
char *queue;

int top;
int front;
int sizeofstack;
int sizeofqueue;
char log[MAX];
int lCounter;

} machine_t;

//there is make elemant pop to from server firstly from stack ,when stack is empty from queue continues  
void   ServerOP(machine_t *server){
    int eleman,i;
    
    
    //check server is empty or not
   if(server->top==-1){
   
          if(server->front == -1 ){
              
             
                server->log[server->lCounter++] = '3';
          }else{
              
              eleman=server->queue[0];
               for(i=0;i<=server->front;i++){
                   
                    server->queue[i]=server->queue[i+1];
                    
               }
               server->front--;
             
               //make a log for write putput file log history
                server->log[server->lCounter++] = eleman;
             
          }            
   
   
   
   
   
   }else{
       
       
        eleman=server->stack[server->top];
        server->log[server->lCounter++] = eleman;
        server->top--;
   }
    
    
}


//Which element sending server,adding server queue in this function
void  addelemantoServer(machine_t *server,char  eleman ){
        if (server->front==server->sizeofqueue-1){
               
               server->log[server->lCounter++] = '1';
     
           }else{
               
               server->queue[++server->front]=eleman;
       
           }
    
}
//delete elemnt from client in there after that try to adding to server queue which "(machine_t *server,char  eleman )" function
void Dequeue(machine_t *m,machine_t *server){
    
    
    
    int eleman;
    int i;
    if(m->top==-1){
        
        if(m->front == -1 ){
       
              
                m->log[m->lCounter++] = '3';
       }else{
             //delete element from client queue
             eleman=m->queue[0];
            
               for(i=0;i<=m->front;i++){
          
                    m->queue[i]=m->queue[i+1];
                    
                  
               }
               m->front--;
               m->log[m->lCounter++] = eleman;
              addelemantoServer(server,eleman);
              
              
     
        }
    }else{
          //delete element from client stack
        eleman=m->stack[m->top];
        addelemantoServer(server,eleman);
        
        m->log[m->lCounter++] = eleman;
        m->top--;
       
    }

}


//adding element to queue
void Enqueue(machine_t *m, char eleman){ 
            
           if (m->front==m->sizeofqueue-1){
               
          
               m->log[m->lCounter++] = '1';
               
              
               
           }else{
               
               m->queue[++m->front]=eleman;
       
           }
       
       
       
       
       
       }
//adding element to client stack
void push(machine_t *m, char p){


    if(m->top == m->sizeofstack-1){
      
            m->log[m->lCounter++] = '2';
  }
  else{
      m->stack[++m->top] = p;
    
  }  
}




void main(int argc, char *argv[]){

    int number, i,a,sendingeleman;
    FILE *fp;
    FILE *fi;
   
    FILE *output = NULL;

    output = fopen(argv[3] ,"w");

    if((fp = fopen(argv[1], "r")) == NULL) {
         printf("No such %s file\n", argv[1]);
           exit(1);
    }

   fscanf(fp, "%d",&number);
      
   machine_t *client = (machine_t *)malloc(number * sizeof(machine_t)) ;
   machine_t server;
   //make client and server 
   for(i=0; i< number ; i++){
            fscanf(fp, "%d %d", &client[i].sizeofqueue, &client[i].sizeofstack);
            client[i].top = -1;
            client[i].front = -1;
            client[i].lCounter =  0;
            client[i].stack = (char *)malloc(client[i].sizeofstack * sizeof(char));
            client[i].queue = (char *)malloc(client[i].sizeofqueue * sizeof(char));          
   }
   
   
   int commandSize;
   
    if((fi = fopen(argv[2], "r")) == NULL) {
           printf("No such %s file\n", argv[2]);
           exit(1);
    }
   
   fscanf(fi, "%d", &commandSize);

   char command, id;
   char item;


   ///take command from here
    while(fscanf(fi, " %c", &command) != EOF){   
                     
       
       if(command == 'A'){
          
          fscanf(fi, "%d", &id);
          fscanf(fi, " %c ", &item);
          Enqueue(&client[id-1], item);
          
       }else if(command == 'I'){
             fscanf(fi, "%d", &id);
             fscanf(fi, " %c ", &item);
             push(&client[id-1], item);
             
       }
       else if(command == 'S'){
             fscanf(fi, "%d", &id);
             Dequeue(&client[id-1],&client[number-1]);
          
       }
       else if(command == 'O'){
             fscanf(fi, "%d", &id);
             ServerOP(&client[number-1]);
          
       }
   }
   
   //Log history wroten output file 
    for(i=0;i<number;i++){

     for(a=0;a<client[i].lCounter;a++){
         
         
         
         fprintf(output,"%c ",client[i].log[a]);
     }
     fprintf(output,"\n");
     
     
     
 }
   

    
    return;

}
