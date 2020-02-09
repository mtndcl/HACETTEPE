#include <string.h>
#include <stdlib.h>
#include <stdio.h>




typedef struct Node
{
	
	int data;
	struct Node *next;
	struct Node *down;
	int fitness;
	int rank;
} MyNode;

typedef struct FolderData
{
	
	int size;
	char *data;
	
} MyFolderData;

typedef struct SelectData
{
	
	int *X;
	int *Y;
	int size;
	
} MySelectData;

void AddNext(int data,MyNode *Head){


		MyNode *node=(MyNode*)malloc(sizeof(MyNode));

		node->data=data;

		node->fitness=0;

		node->rank=0;

		node->next=NULL;

		node->down=NULL;

		MyNode *temp=Head;
		
		while(temp->next!=NULL){


			temp=temp->next;
		}

		temp->next=node;
		
}

MyNode * AddDown(int data,MyNode *Head){


		MyNode *node=(MyNode*)malloc(sizeof(MyNode));

		node->data=data;

		node->next=NULL;

		node->down=NULL;

		MyNode *temp=Head;

		node->fitness=0;

		node->rank=0;

		while(temp->down!=NULL){


			temp=temp->down;
		}

		temp->down=node;

		return node;
		
}
MyNode *initlazeHead(int data,MyNode *Head){

	 MyNode *node=(MyNode*)malloc(sizeof(MyNode));

	 node->data=data;

	 node->fitness=0;

	
	node->rank=0;
	
	node->next=NULL;

	 node->down=NULL;

	 Head=node;

	 return Head;

}

int Power(int base,int exponent){

	int result=1;
	for (exponent; exponent>0; exponent--)
	{
		result = result * base;
	}
	return result;
}
void SetValue(MyNode *initHead,int GenNumber){

	MyNode *temphead=initHead;
	int fitness=0;
	int exponent=(GenNumber-1);

	while(temphead!=NULL){
		
		MyNode *next=temphead;
		while(next!=NULL){

			

			fitness+=Power(2,exponent)*next->data;
			
			exponent--;
			
			
			next=next->next;
		}
		temphead->fitness=fitness;
		fitness=0;
		exponent=(GenNumber-1);
		//printf("---------data: %d-- >>%d\n", temphead->data, temphead->value);
		temphead=temphead->down;
	}
}

MyNode *sortList ( MyNode *head,int num_nodes)
{
  
    int i;

    for (i = 0; i < num_nodes; i++) {
         MyNode* current = head;
         MyNode* next = current->down;
         MyNode* previous = NULL;

        while(next != NULL) {
            int compare = current->fitness-next->fitness;
            if (compare > 0) {
                if (current == head){
                    head = next;
                } else {
                    previous->down = next;
                }
                current->down = next->down;
                next->down = current; 

                previous = next;
                next = current->down;
            }
            else {
                previous = current;
                current = current->down;
                next = current->down;
            }
        }
    }
   
    return head;
}


MyNode * CreateLinkedList(MyNode *initHead,int GenNumber ,int ChromoNumber){

	FILE *populationfile=fopen("population","r");

	
	char  ch;
	int currentGenNumber=0;
	int currentChromoNumber=0;
	
	MyNode *temphead;
	int init=0;
	
	while((ch = fgetc(populationfile)) != EOF)
    {		

    	if (ch=='0' || ch=='1')
    	{
    		int data = ch - '0';
    		if (initHead==0)
    		{
    			initHead=initlazeHead(data,initHead);
    			temphead=initHead;
    			currentGenNumber++;
    		}else if (currentGenNumber<GenNumber)
    		{

	    		AddNext(data,temphead);
	    		currentGenNumber++;
	    	
    		}else if (currentChromoNumber<ChromoNumber){
    			
    			
    			currentGenNumber=1;
    			currentChromoNumber++;
    			temphead=AddDown(data,temphead);
    		}

    	}
    }


    SetValue(initHead,GenNumber);
    
    initHead=sortList(initHead,ChromoNumber);

 
    return initHead;


   
}

int FolderSize(char *foldername){


	FILE *folder=fopen(foldername,"r");
	
	if (folder==NULL)
	{
		printf("dont have such folder %s\n",foldername );
		exit(1);
	}
	char ch;
	int size=0;
	while((ch = fgetc(folder))!= EOF){
		size++;
	}

	fclose(folder);
	return size;

}
void LoadData(MyFolderData *SelectionFolder,char *foldername){

	char ch;
	int size=0;
	FILE *selection=fopen(foldername,"r");

	while((ch = fgetc(selection))!= EOF){

		SelectionFolder->data[size++]=ch;
	
	}
	fclose(selection);
}

MySelectData *Selection(MyFolderData *SelectionFolder,int Generation,MySelectData *SelectData){

	char  ch;

	int *selectedChoroX=(int*)malloc(sizeof(int));
	int *selectedChoroY=(int*)malloc(sizeof(int));

	MyNode *temphead;

	int first,second;

	char *key=(char*)malloc(sizeof(char));

	int countX=0;
	int countY=0;
	int index=0;
	int order=0;
	int i,j;
	int line=1;
	for ( j = 0; j < SelectionFolder->size; ++j){

		ch=SelectionFolder->data[j];
    	if (ch==':' || ch== ' ')
    	{	
    		key[index]='\0';
    		//printf("-%s-\n",key );
    		index=0;
    		if (order%2==0)
    		{
    			selectedChoroX=realloc(selectedChoroX,sizeof(int)*(countX+1));
    			selectedChoroX[countX++]=atoi(key);
    		}else{
    			selectedChoroY=realloc(selectedChoroY,sizeof(int)*(countY+1));
    			selectedChoroY[countY++]=atoi(key);
    		}
    		order++;
    		memset(key,0,sizeof(key));
    		
    	}else if ( ch=='\n'){
    		key[index]='\0';
    		
    		if (order%2==0)
    		{
    			selectedChoroX=realloc(selectedChoroX,sizeof(int)*(countX+1));
    			selectedChoroX[countX++]=atoi(key);
    		}else{
    			selectedChoroY=realloc(selectedChoroY,sizeof(int)*(countY+1));
    			selectedChoroY[countY++]=atoi(key);
    		}
    		memset(key,0,sizeof(key));
    		int x=0;
    		for(i=0;i<countX;i++){
    			x++;
    			//printf("%d-",selectedChoroX[i]);
    		}
    	
    		SelectData->X=selectedChoroX;
    		SelectData->Y=selectedChoroY;
    		SelectData->size=x;
    

    		if (line==Generation)
    		{
    			return SelectData;
    		}
    		memset(selectedChoroX,0,sizeof(selectedChoroX));
    		memset(selectedChoroY,0,sizeof(selectedChoroY));
    		countX=0;
    		countY=0;
    		order=0;
    		index=0;
    		line++;
    	}else {
    		    
    		key=realloc(key,sizeof(char)*(index+1));
    		key[index]=ch;
    		index++;
    	}
    	
	}


}

MyNode *GetNode(MyNode *initHead,int Generation){
	MyNode *temp=initHead;
	int count=1;
	while(temp!=NULL){

		if (count==Generation)
		{
			return temp;
		}
		count++;
		temp=temp->down;
	}
	return NULL;

}

void  ApplyXOVER(MyNode *initHead ,int X,int Y,MySelectData *SelectXover){

	//printf("---------------- %d--%d,,,,,,,,xover %d--%d\n",X,Y,SelectXover->X[0],SelectXover->Y[0]);
	MyNode *Xnode=GetNode(initHead,X);
	MyNode *Ynode=GetNode(initHead,Y);
	int i;
	int *cloneX=(int*)malloc(sizeof(int));
	int *cloneY=(int*)malloc(sizeof(int));
	int clonenumberX=0;
	int clonenumberY=0;
	MyNode *temp=Xnode;
	//printf("Node order : %d\n",X );
	int count=1;
	while(temp!=NULL){

		///printf("%d--",temp->data );
		if (count>=SelectXover->X[0] && count<=SelectXover->Y[0])
		{
			cloneX=realloc(cloneX,sizeof(int)*(clonenumberX+1));
			cloneX[clonenumberX]=temp->data;
			clonenumberX++;
		}
		count++;
		
		temp=temp->next;
	}
	count=1;
	temp=Ynode;
	while(temp!=NULL){

		//printf("%d--",temp->data );
		if (count>=SelectXover->X[0] && count<=SelectXover->Y[0])
		{
			cloneY=realloc(cloneY,sizeof(int)*(clonenumberY+1));
			cloneY[clonenumberY]=temp->data;
			clonenumberY++;
		}
		count++;

		temp=temp->next;
	}

	temp=Xnode;
	count=1;
	int in=0;
	while(temp!=NULL){
		if (count>=SelectXover->X[0] && count<=SelectXover->Y[0])
		{

			temp->data=cloneY[in++];
		}
		count++;
		temp=temp->next;
	}
	
	temp=Ynode;
	count=1;
	in=0;
	while(temp!=NULL){
		if (count>=SelectXover->X[0] && count<=SelectXover->Y[0])
		{

			temp->data=cloneX[in++];
		}
		count++;
		temp=temp->next;
	}
	/*printf("XXXXXX\n");
	for ( i = 0; i < clonenumberX; ++i)
	{
		printf("%d-",cloneX[i] );
	}
	printf("\nYYYYYY\n");
	for ( i = 0; i < clonenumberY; ++i)
	{
		printf("%d-",cloneY[i] );
	}
	printf("\n");*/
	memset(cloneX,0,sizeof(cloneX));
	memset(cloneY,0,sizeof(cloneY));

	//printf("%d----------------%d\n",X,Y );


}

void ApplyMutation(MyNode *initHead,int mutate){


	MyNode *temp=initHead;
	int count;
	while(temp!=NULL){
		
		count=1;
		MyNode *next=temp;
		while(next!=NULL){

			if (count==mutate)
			{
				if (next->data==0)
				{
					next->data=1;
				}else{
					next->data=0;
				}
			}
			count++;
			
			next=next->next;
		}
		
		temp=temp->down;
		
	}
}
void Print(MyNode *initHead,int Generation,int best[],int GenNumber,int bestvalue){
	
	int i;
	printf("GENERATION: %d\n",Generation);
	MyNode *temp=initHead;
	while(temp!=NULL){
		
		MyNode *next=temp;
		while(next!=NULL){

			if (next->next==NULL)
			{
				printf("%d", next->data);
			}else{
				printf("%d:", next->data);
			}
			next=next->next;
		}
		printf(" -->  %d\n",temp->fitness);
		temp=temp->down;
	
	}
	printf("Best chromosome found so far: ");
	for ( i = 0; i < GenNumber; ++i)
	{
		if (i==GenNumber-1)
		{
			printf("%d --> %d\n",best[i],bestvalue);
		}else{
			printf("%d:",best[i]);
		}

	}

}
void Generation(MyNode *initHead,int GenerationNumber,int GenNumber,int ChromoNumber){


	MyFolderData *SelectionFolder=(MyFolderData*)malloc(sizeof(MyFolderData));
	SelectionFolder->size=FolderSize("selection");
	SelectionFolder->data=(char*)malloc(sizeof(char)*(SelectionFolder->size));

	MyFolderData *CrossOverFolder=(MyFolderData*)malloc(sizeof(MyFolderData));
	CrossOverFolder->size=FolderSize("xover");
	CrossOverFolder->data=(char*)malloc(sizeof(char)*(CrossOverFolder->size));
	
	MyFolderData *MutationFolder=(MyFolderData*)malloc(sizeof(MyFolderData));
	MutationFolder->size=FolderSize("mutate");
	MutationFolder->data=(char*)malloc(sizeof(char)*(MutationFolder->size));
	
	LoadData(SelectionFolder,"selection");
	LoadData(CrossOverFolder,"xover");
	LoadData(MutationFolder,"mutate");

	MySelectData *SelectSelection=(MySelectData*)malloc(sizeof(MySelectData));
	MySelectData *SelectXover=(MySelectData*)malloc(sizeof(MySelectData));
	MySelectData *SelectMutation=(MySelectData*)malloc(sizeof(MySelectData));

	int i,j,count;
	int best[GenNumber];
	MyNode *temp=initHead;
	i=0;
	int bestvalue=initHead->fitness;
	while(temp!=NULL){

		best[i]=temp->data;
		i++;
		temp=temp->next;
	}


	Print(initHead,0,best,GenNumber,bestvalue);
	

	for (i = 1; i < GenerationNumber+1; ++i)
	{
		SelectSelection=Selection(SelectionFolder,i,SelectSelection);

		SelectXover=Selection(CrossOverFolder,i,SelectXover);

		SelectMutation=Selection(MutationFolder,i,SelectMutation);
		
		for (j = 0; j <SelectSelection->size ; ++j)
		{
			ApplyXOVER(initHead,SelectSelection->X[j],SelectSelection->Y[j],SelectXover);
		}

		ApplyMutation(initHead,SelectMutation->X[0]);
		SetValue(initHead,GenNumber);

		initHead=sortList(initHead,ChromoNumber);
		
		if (initHead->fitness < bestvalue)
		{
			temp=initHead;
			count=0;
			bestvalue=initHead->fitness;
			while(temp!=NULL){

				best[count]=temp->data;
				count++;
				temp=temp->next;
			}
		}
		Print(initHead,i,best,GenNumber,bestvalue);

	}

}


int main(int argc, char const *argv[])
{
	
	MyNode *initHead=NULL;

	int GenNumber=atoi(argv[1]);
	int ChromoNumber=atoi(argv[2]);
	int GenerationNumber=atoi(argv[3]);

	initHead=CreateLinkedList(initHead,GenNumber,ChromoNumber);

	Generation(initHead,GenerationNumber,GenNumber,ChromoNumber);

	return 0;
}