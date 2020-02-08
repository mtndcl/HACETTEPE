#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#define MAX 20
#define MAXFILE 10
#define MAXFILENAME 200 // for max absolute path

typedef struct node {
    char  word[MAX];
    int count;
    struct node * next;
    struct node * down;
	struct  node  *prev;
	struct  node  *up;


} node_t;

node_t * initiliaze(){

      /// char  wor[20]="head";
       node_t *head = (node_t *)malloc(sizeof(node_t));
       head->next = NULL;
       head->down = NULL;
	   head->prev=NULL;
	   head->up=NULL;
       head->count=0;

       return head;
}

void addNext(node_t *p){
    p->next = (node_t *)malloc(sizeof(node_t));
    p->next->next = NULL;
    p->next->down = NULL;
	p->next->up=NULL;
	p->next->prev=p;
    p->next->count =0;
}

void addDown(node_t *p){
    p->down = (node_t *)malloc(sizeof(node_t));
    p->down->next = NULL;
	p->down->up=p;
    p->down->down = NULL;
    p->down->count = 0;
 
}


void   sorted(node_t *head){


    node_t  *i,*j;
	node_t  *node;
    int number;
    char   words[MAX];

    for(i=head;i->next!=NULL;i=i->next){

        for(j=i->next;j!=NULL;j=j->next){


         if(i->count    <   j->count){

                number = i->count;
				node =i->down;
				
				
                strcpy( words ,i->word);
				
				
				i->down=j->down;
                i->count = j->count;
                strcpy( i->word ,j->word);
				
				j->down=node;
                j->count = number;
                strcpy( j->word ,words);


            }
        }

    }
   
}

node_t    *   ReadingFile(FILE *input,char secondcommand[], node_t *head){
    char  c;
    char  word[MAX];
 

    int wordsize=0;

    node_t *temp = head;

    

	
	
	
	
    if((input = fopen(secondcommand, "r")) == NULL) {
         printf("Are you sure you have this  file\n", secondcommand);
           
    }
    while ((c =fgetc(input)) != EOF ){

        if (c >= 'A' && c<= 'Z'  ||  c >= '0' && c<= '9' ||   c >= 'a' && c<= 'z' ){



             if ((c >= 65) && (c <= 90)){

                 c=c+32;
              }
             word[wordsize++]=c;

        }else if(strlen(word) > 0 ) {

			


			strcpy(temp->word,word);
			temp->count=1;
            addNext(temp);


            temp = temp->next;

			memset(word, '\0', MAX);
            wordsize=0;
        }
    }

	if(strlen(word)>0){

		strcpy(temp->word,word);
		temp->count=1;
	
	}
		
	return   head;

}
void  AddDown(node_t  *real,node_t  *  fake){
	
	
	node_t  *j=real;
	node_t  *d=fake;
	int havenode=0;
	while(real->down!=NULL){
		
		node_t  *i=fake;
		while(i->down!=NULL){

			if(!strcmp(real->down->word,i->down->word)){
				
				real->down->count++;
				havenode=1;

			}
		
			
		i=i->down;
			
		
		}
	
	real=real->down;
		
		
	}
	
	if(!havenode){
		
		
		while(j->down!=NULL){
			
			j=j->down;
		}
		while(d->down!=NULL){
			
			d=d->down;
		}
		
	
		addDown(j);
		j=j->down;
		strcpy(j->word, d->word);
		j->count=1;

		
	}

	
}
void  FindSameword(node_t  *head){
	
	node_t  *temp=head;
	node_t  *j=head;

			
			
	while(temp->next!=NULL){
		
		
		node_t  *i=temp->next;
		node_t *k = temp;
		while(i!=NULL){
			if(!strcmp(temp->word,i->word)){
				
				temp->count++;
				if(i->next != NULL){

					addDown(k);
					strcpy(k->down->word, i->word);
					k->down->count = 1;
				}
		
			}
			
			
			i=i->next;
		}
		addDown(temp);
		strcpy(temp->down->word,temp->next->word);
		temp->down->count=1;
		
		temp=temp->next;
	}
	
	while(j->next!=NULL){
		
		node_t  *k=j->next;
		while(k->next!=NULL){
			
			

			if(!strcmp(j->word,k->word)){
				
				
					AddDown(j,k);
			
	
			}
		k=k->next;
		}
	j=j->next;
	}
	
	
}


void  fixhead(node_t   *head){
	
	node_t   *temp=head;
	node_t *storenext;
	node_t *storeprev;
	char   c[MAX];
	
	
	node_t   *d;
	while(temp->next!=NULL){
		
		node_t   *i=temp->next;
	
		while(i->next!=NULL){
			
			strcpy(c,i->word);
			if(!strcmp(temp->word,i->word)){
				
					
					
					
					storenext=i->next;
					storeprev=i->prev;
					storenext->prev=storeprev;
					storeprev->next=storenext;
                   
					
					node_t *d=i;
					while(d!=NULL)			   
						d=d->down;
                   while(d != NULL){
					   d = d->up;
					   free(d->down);
					   d->down = NULL;
				   }
				   free(i);
                         
 		
		   }
		i=i->next;
		}
		

	temp=temp->next;	
	}

	
	node_t  *k=temp->prev;
	while(k!=NULL){
		
		if(!strcmp(k->word,c)){
			
			
			
			node_t  *a=temp->prev;
			a->next=NULL;
			 free(a);
			
			
	}
	k=k->prev;
	}
	
	
	
	
}

void   AddnewElement(char  addingword[],int  numberofword,node_t   *head ){
	
	int haveword=0;
	
	node_t  *temp=head;
	while(temp->next!=NULL){
		
		if(!strcmp(temp->word,addingword)){
			haveword=1;
			temp->count=temp->count+numberofword;
		}
		
		temp=temp->next;
	}
	if(!haveword){
		
		addNext(temp);
		strcpy(temp->next->word,addingword);
		temp->next->count=numberofword;
	}
	
	 

	sorted(head);
	
	
	
}



int   FindMostThreePairs(node_t  *head, int maxNumber,int  counter){
	
	
	node_t  *temp=head;
	char usedwords[3][MAX];
	int  number=0,a;
	char  firstword[MAX];
	char  secondword[MAX];
	
	
	while(temp->next!=NULL){
		
			node_t  *i=temp;	
			while(i->down!=NULL){
				if(i->down->count  >= number  &&  i->down->count <= maxNumber  ){
					
					
						int  usedword=0;
						
						
						for(a=0;a<counter;a++){
							
							if(!strcmp(usedwords[a],i->down->word)){
								
								usedword=1;
								
							}
						
						}
						if(!usedword){
							
							number=i->down->count;
							strcpy(secondword,i->down->word );
							strcpy(firstword,temp->word );
						}
						
				}
				
			i=i->down;
			}
		temp=temp->next;
	}
	maxNumber=number;
	number=0;

	printf("%s - %s (%d)\n",firstword,secondword,maxNumber);
	
	strcpy(usedwords[counter],secondword);
	
	
	


	memset(firstword, '\0', MAX);
	memset(secondword, '\0', MAX);
	
	return maxNumber;
	
	
	
}
node_t   *   deleteitem(node_t   *head  , char   word[]){
	
	int  i;
	
	char c;
	
	
	int   haveword=0;
	char  item[MAX];
	
	for( i = 0; i<=strlen(word); i++){
			
			
			c=word[i];
			
		    if ((c >= 65) && (c <= 90)){

                 c=c+32;
             }
		
		
		item[i] = c;
	}

	
	node_t   *temp=head;

	node_t *storenext;
	node_t *storeprev;
	
	
	
	node_t   *d;
	while(temp!=NULL){

	
			if(!strcmp(temp->word,item)){
				
				
				haveword=1;
				if(!temp->prev){

					head = temp->next;

					head->prev = NULL;
					
					
					node_t *d=temp;
					while(d!=NULL)			   
						d=d->down;
                   while(d != NULL){
					   d = d->up;
					   free(d->down);
					   d->down = NULL;
				   }
				   free(temp);
				}
				else if(!temp->next){
					
					storeprev=temp->prev;
					storeprev->next=NULL;
					
				} else {
					
					storenext=temp->next;
					storeprev=temp->prev;
					storenext->prev=storeprev;
					storeprev->next=storenext;
					
					
					node_t *d=temp;
					while(d!=NULL)			   
						d=d->down;
                   while(d != NULL){
					   d = d->up;
					   free(d->down);
					   d->down = NULL;
				   }
				   free(temp);
				}
				
				
				

 		
		   }
	temp=temp->next;
	}
	if(!haveword){
		
		printf("%s  does not exist in the text\n",word);
	}
		

	
	
	
	return head;
}  


void   FinfSimilariti(node_t *firtlisthead,node_t *secondlisthead,char   fileone[] ,char  filetwo[]){
	
	int  Matrix[2][20];
	int numberofwordfile1=0;
	int a;
	
	float  dot_product=0;
	float d1=0;
	float d2=0;
	int  matrixsize=0;
	node_t  *head1=firtlisthead;
	while(head1!=NULL  &&   numberofwordfile1<10){
		
		
		int sameword=0;
		node_t  *head2=secondlisthead;
		while(head2!=NULL){
			
			
			
			if(!strcmp(head1->word,head2->word)){
				
				
				Matrix[0][matrixsize]=head1->count;
				Matrix[1][matrixsize]=head2->count;
				sameword=1;
				matrixsize++;
			}
			
		head2=head2->next;
		}
		if(!sameword){
			Matrix[0][matrixsize]=head1->count;
			Matrix[1][matrixsize]=0;
			
			matrixsize++;
			
		}
		
	head1=head1->next;
	numberofwordfile1++;
	}
	///////////////////////////////////7
	numberofwordfile1=0;
	
	
	
	

	node_t   *head3=secondlisthead;
	
	while(head3!=NULL  &&   numberofwordfile1<10 ){
		
		
		int sameword=0;
		node_t  *head2=firtlisthead;
		while(head2!=NULL  ){
			
			if(!strcmp(head3->word,head2->word)){
				
				sameword=1;
			}
				
			
			
		head2=head2->next;
		}
		if(!sameword){
			
			Matrix[0][matrixsize]=0;
			Matrix[1][matrixsize]=head3->count;
		
			matrixsize++;
			
		}
		
	head3=head3->next;
	numberofwordfile1++;
	}
	
	
	
	
	for(a=0;a<20;a++){
		
		
		if(a<matrixsize){
			
			
			dot_product= dot_product+Matrix[0][a]*Matrix[1][a];
			d1=d1+(Matrix[0][a]*Matrix[0][a]);
			d2=d2+(Matrix[1][a]*Matrix[1][a]);
			
		}
		
	}
	
	printf("------------------------------------\n");
	printf("Cosine Similarity of %s and %s = %.3f\n",fileone,filetwo,dot_product/(sqrt(d1)*sqrt(d2)));
	printf("------------------------------------\n");
	

	
	
}



int main(int argc,char *argv[]){
     int running=1,i,addcount;
     char  command[5];
     char  secondcommand[256],thirdcommand[256];
     int listcount=0;

     FILE  *inputfile;

     node_t *files[MAXFILE];
	 char path[10][MAXFILENAME];

     int fileCounter = 0;




     while(running){
        printf( "Enter command  :" );
        scanf( "%s", command );

       
        if(command[0] == '-'  && command[1] == 'r'){



               scanf( "%s", secondcommand );
               files[fileCounter] = initiliaze();
			   strcpy(path[fileCounter], secondcommand);
			
				node_t  *head  =ReadingFile(inputfile,secondcommand, files[fileCounter]);

				FindSameword(head);
				fixhead(head);
				sorted(head);
				
                fileCounter++;

        }else if(command[0] == '-'  && command[1] == 'a'){

                  scanf( "%s", secondcommand );
                   scanf( "%d", &addcount );
                   scanf( "%s", thirdcommand );
				   
				   int  havefile=0;
				   for(i=0;i<MAXFILE;i++){
					   if(!strcmp(path[i],thirdcommand)){
						   
						   node_t  *head=files[i];
						   
						   AddnewElement(secondcommand,addcount,head);
						  havefile=1;
						 
						}
					   
					   
					   
					}
					if(!havefile){
					   
					   printf("Sorry this file not exist !!!\n");
					}
			

        }else if(command[0] == '-'  && command[1] == 'n' &&  command[2] == '2'){
              scanf( "%s", secondcommand );
			  
			  
					
					int  havefile1=1;
					int maxnumber=MAXFILENAME;
					
				   for(i=0;i<MAXFILE;i++){
					   if(!strcmp(path[i],secondcommand)){
						   
						   node_t  *head=files[i];
							
							
							printf("--------------------------------------------\n");
							printf("the first 3 pair of words of %s  =\n",secondcommand);
							maxnumber=FindMostThreePairs(head,maxnumber,0);
							node_t  *head1=files[i];
							
							maxnumber=FindMostThreePairs(head1,maxnumber,1);
							node_t  *head2=files[i];
							
							maxnumber= FindMostThreePairs(head2,maxnumber,2);
						  	printf("--------------------------------------------\n");

						   havefile1=1;
						   
						}
					   
					   
					   
					}
					if(!havefile1){
					   
					   printf("Sorry this file not exist!!!\n");
					}
			  
			  
			  
			  

        }else if(command[0] == '-'  && command[1] == 'd'){
                 scanf( "%s", secondcommand );
				scanf( "%s", thirdcommand );
				
				int  hasfile=0;
				 for(i=0;i<MAXFILE;i++){
					if(!strcmp(path[i],thirdcommand)){
	
						   
						   hasfile=1;
						   files[i]=deleteitem(files[i],secondcommand);
				   
					}
				}
				 if(!hasfile){
					 
					 printf("This  file does  not exist  file name is  :%s\n",thirdcommand);
				 }  
				
				
           
        }else if(command[0] == '-'  && command[1] == 's'){
             scanf( "%s", secondcommand );
             scanf( "%s", thirdcommand );
			 
			 int  firsfile=0;
			 int  secondfile=0;
			 node_t  *firstlinllist;
			 node_t  *secondlinllist;
			  for(i=0;i<MAXFILE;i++){
					if(!strcmp(path[i],secondcommand)){
						   
						   firstlinllist=files[i];
						   firsfile=1;
						   
						  
   
					}
				}
				for(i=0;i<MAXFILE;i++){
					if(!strcmp(path[i],thirdcommand)){
						   
						   secondlinllist=files[i];
						   
						   secondfile=1;
						  
   
					}
				}
				if(firstlinllist  &&   secondfile){
					FinfSimilariti(firstlinllist,secondlinllist,thirdcommand,secondcommand);
				}else{
					
					printf("One of this txt files or both of them do not exist,Please read  file or files. After that try again\n");	
					
				}
				
			 
        

        }else if(command[0] == '-'  &&  command[1] == 'q'){

            printf( "GOODBYE \n" );
            running=0;

        }else{

             printf( "This  command  is unavaible\n" );

        }
     }
     return 0;
   }


