#include <stdio.h>
#include <stdlib.h>
#include <string.h>




////Passenger Struct
typedef struct Passenger{

	char  *flight_id;
	char *name;
	char *class;
	char *priority;
	char  *took_class;
	int error;
	
} MyPassenger;

////Queue Struct
typedef struct Queue{

	MyPassenger *passengers;
	int size;


} MyQueue;

////Flight Struct
typedef struct Flight{


	int closed;
	int sold_b;
	int sold_e;
	int sold_s;
	int seat_b;
	int seat_e;
	int seat_s;
	char *id;

	MyPassenger *passengers_b;
	int passengers_number_b;

	MyPassenger *passengers_s;
	int passengers_number_s;

	MyPassenger *passengers_e;
	int passengers_number_e;
	

	MyQueue Queues[3];

} MyFlight;

////Stack Struct
typedef struct Stack{

	MyFlight *flights;
	int top;

} MyStack;

//Count how many char in txt file
int SizeofFile(const char  *inputfilename){

	FILE *file=fopen(inputfilename,"r");

	file=fopen(inputfilename,"r");

	if (file==NULL)
	{
		
		printf("Does not have such file%s\n",inputfilename );
		exit(1);
	}

	char ch;
	int size=0;
	while(fscanf(file, "%c", &ch) != EOF){
			size++;
	}
	fclose(file);
	size++;
	return size;

}

///Create a flight then initialize  value tit 
MyFlight  *CreateFlight(int flight_number,char *id,char  *seat_type,int seat_number){

	flight_number++;

	MyFlight *flight=(MyFlight*)malloc(sizeof(MyFlight));

	flight->id=(char*)malloc(sizeof(id));
	flight->passengers_b=(MyPassenger*)malloc(sizeof(MyPassenger));
	flight->passengers_number_b=0;
	flight->passengers_e=(MyPassenger*)malloc(sizeof(MyPassenger));
	flight->passengers_number_e=0;
	flight->passengers_s=(MyPassenger*)malloc(sizeof(MyPassenger));
	flight->passengers_number_s=0;
	
	flight->closed=0;
	strcpy(flight->id,id);
	flight->seat_s=0;
	flight->seat_e=0;
	flight->seat_b=0;

	flight->sold_s=0;
	flight->sold_e=0;
	flight->sold_b=0;

	
	flight->Queues[0]=*(MyQueue*)malloc(sizeof(MyQueue));
	flight->Queues[0].passengers=(MyPassenger*)malloc(sizeof(MyPassenger));
	flight->Queues[1]=*(MyQueue*)malloc(sizeof(MyQueue));
	flight->Queues[1].passengers=(MyPassenger*)malloc(sizeof(MyPassenger));
	flight->Queues[2]=*(MyQueue*)malloc(sizeof(MyQueue));
	flight->Queues[2].passengers=(MyPassenger*)malloc(sizeof(MyPassenger));
	

	flight->Queues[0].size=0;
	flight->Queues[1].size=0;
	flight->Queues[2].size=0;
	if (strcmp(seat_type,"business")==0)
	{
		flight->seat_b+=seat_number;

	}else 	if (strcmp(seat_type,"economy")==0)
	{
		flight->seat_e+=seat_number;
	}else 	if (strcmp(seat_type,"standard")==0)
	{
		flight->seat_s+=seat_number;
	}
	return flight;
}

///Get Flight from Stack
MyFlight  *GetFlight(MyStack *Stack,char *id){

	int i;
	for ( i = 0; i < Stack->top; ++i)
	{
		if (strcmp(Stack->flights[i].id,id)==0)
		{	
			return &Stack->flights[i];
		}
	}
	return NULL;
}

///Update seat number of flight
void UpdateSeatNumber(MyFlight *flight,char *seat_type,int seat_number){

	if (strcmp(seat_type,"business")==0)
	{
		flight->seat_b+=seat_number;
	}else 	if (strcmp(seat_type,"economy")==0)
	{
		flight->seat_e+=seat_number;
	}else 	if (strcmp(seat_type,"standard")==0)
	{
		flight->seat_s+=seat_number;
	}
}

///Separeta priority passenger and normal passenger from each other
int GetSplitIndex(MyQueue Queue,char *priority){


	int i;
	for (i = 0; i < Queue.size-1; ++i)
	{
		if (strcmp(Queue.passengers[i].priority,priority)!=0)
		{
			return i;
		}
	}
	return Queue.size-1;
}
///Enqueue passenger to specificaion Queue 
MyPassenger  *Enqueue(char *flight_id,char *class,char *name,char *priority,MyQueue Queues[],FILE *out){

	MyPassenger *passenger=(MyPassenger*)malloc(sizeof(MyPassenger));
	passenger->flight_id=(char*)malloc(sizeof(flight_id));
	passenger->class=(char*)malloc(sizeof(class));
	passenger->name=(char*)malloc(sizeof(name));
	passenger->priority=(char*)malloc(sizeof(priority));
	passenger->took_class=(char*)malloc(sizeof("none\0"));
	passenger->error=0;
	strcpy(passenger->flight_id,flight_id);
	strcpy(passenger->class,class);
	strcpy(passenger->name,name);
	strcpy(passenger->priority,priority);
	strcpy(passenger->took_class,"none");

	int i;
	if (strcmp(class,"business")==0)
	{
		
		Queues[0].passengers=realloc(Queues[0].passengers,sizeof(MyPassenger)*(Queues[0].size+1));
		
		if (strcmp(priority,"diplomat")==0)
		{
			Queues[0].size++;
			int split_index=GetSplitIndex(Queues[0],"diplomat");

			for ( i = Queues[0].size-2; i >=split_index ; i--)
			{
					Queues[0].passengers[i+1]=Queues[0].passengers[i];
			}
			
			Queues[0].passengers[split_index]=*passenger;
			
			fprintf(out, "queue %s %s %s %d\n",passenger->flight_id,passenger->name,passenger->class,Queues[0].size);

		}else if(strcmp(priority,"")==0){

			Queues[0].size++;
			Queues[0].passengers[Queues[0].size-1]=*passenger;
			fprintf(out, "queue %s %s %s %d\n",passenger->flight_id,passenger->name,passenger->class,Queues[0].size);

		}else{
			passenger->error=1;
			fprintf(out, "error\n" );
		}
		
		
	}else 	if (strcmp(class,"economy")==0)
	{
		Queues[1].passengers=realloc(Queues[1].passengers,sizeof(MyPassenger)*(Queues[1].size+1));
		if (strcmp(priority,"veteran")==0)
		{	
			Queues[1].size++;
			int split_index=GetSplitIndex(Queues[1],"veteran");

			for ( i = Queues[1].size-2; i >=split_index ; i--)
			{
					Queues[1].passengers[i+1]=Queues[1].passengers[i];
			}
			
			Queues[1].passengers[split_index]=*passenger;


			fprintf(out, "queue %s %s %s %d\n",passenger->flight_id,passenger->name,passenger->class,Queues[1].size);

		}else if(strcmp(priority,"")==0){
			Queues[1].size++;
			Queues[1].passengers[Queues[1].size-1]=*passenger;
			fprintf(out, "queue %s %s %s %d\n",passenger->flight_id,passenger->name,passenger->class,Queues[1].size);
		}else{

			passenger->error=1;
			fprintf(out, "error\n");
		}
		
	}else 	if (strcmp(class,"standard")==0)
	{
		if (strcmp(priority,"")==0)
		{
			Queues[2].size++;
			Queues[2].passengers=realloc(Queues[2].passengers,sizeof(MyPassenger)*Queues[2].size);
			Queues[2].passengers[Queues[2].size-1]=*passenger;
			fprintf(out, "queue %s %s %s %d\n",passenger->flight_id,passenger->name,passenger->class,Queues[2].size);
		}else {
			passenger->error=1;
			fprintf(out, "error\n" );
		}

	}
	return passenger;
}

///İf Flight created Push in to into Stack ,otherwise update seat number
void Push(char *key2,char *key3,char *key4,MyStack  *Stack,FILE *out){

	MyFlight *selected_flight=GetFlight(Stack,key2);

	if (selected_flight==NULL)
	{
		selected_flight=CreateFlight(Stack->top,key2,key3,atoi(key4));
		Stack->top++;
		Stack->flights=realloc(Stack->flights,sizeof(MyFlight)*Stack->top);
		Stack->flights[Stack->top-1]=*selected_flight;
	}else{

		UpdateSeatNumber(selected_flight,key3,atoi(key4));
	}

	int b=selected_flight->seat_b+selected_flight->sold_b;
	int e=selected_flight->seat_e+selected_flight->sold_e;
	int s=selected_flight->seat_s+selected_flight->sold_s;
	fprintf(out, "addseats %s %d %d %d\n",selected_flight->id,b,e,s );
}

///Dequeue from business queue
void DeQueue_Business(MyFlight *selected_flight,MyQueue  Queues[],FILE *out){

	int i,j,sold=0;

	MyQueue *queue=&Queues[0];
	int size=queue->size;
	for ( i = 0; i < size; ++i)
	{
		
		if (selected_flight->seat_b  > 0)
		{
			

			queue->passengers[0].took_class=realloc(queue->passengers[0].took_class,sizeof("business\0"));
			strcpy(queue->passengers[0].took_class,"business");
			
			selected_flight->passengers_number_b++;
			selected_flight->passengers_b=realloc(selected_flight->passengers_b,sizeof(MyPassenger)*(selected_flight->passengers_number_b));
			selected_flight->passengers_b[selected_flight->passengers_number_b-1]=queue->passengers[0];
			//////////Delete 
			for ( j = 0; j < queue->size-1; ++j)
			{
				queue->passengers[j]=queue->passengers[j+1];
			}

			queue->size--;
			selected_flight->seat_b--;
			selected_flight->sold_b++;

		}
	}

	int count=0;
	for ( i = 0; i < queue->size; ++i)
	{
		Queues[2].size++;
		Queues[2].passengers=realloc(Queues[2].passengers,sizeof(MyPassenger)*Queues[2].size);
		Queues[2].passengers[Queues[2].size-1]=queue->passengers[i];
		count++;
	}
	queue->size-=count;

}
///Dequeue from economy queue
void DeQueue_Economy(MyFlight *selected_flight,MyQueue  Queues[],FILE *out){

	int i,j,sold=0;

	MyQueue *queue=&Queues[1];
	int size=queue->size;
	for ( i = 0; i < size; ++i)
	{
		
		if (selected_flight->seat_e  > 0)
		{

			queue->passengers[0].took_class=realloc(queue->passengers[0].took_class,sizeof("economy\0"));
			strcpy(queue->passengers[0].took_class,"economy");

			selected_flight->passengers_number_e++;
			selected_flight->passengers_e=realloc(selected_flight->passengers_e,sizeof(MyPassenger)*(selected_flight->passengers_number_e));
			selected_flight->passengers_e[selected_flight->passengers_number_e-1]=queue->passengers[0];
			//////////Delete 
			for ( j = 0; j < queue->size-1; ++j)
			{
				queue->passengers[j]=queue->passengers[j+1];
			}

			queue->size--;
			selected_flight->seat_e--;
			selected_flight->sold_e++;
		}
	}

	int count=0;
	for ( i = 0; i < queue->size; ++i)
	{
		Queues[2].size++;
		Queues[2].passengers=realloc(Queues[2].passengers,sizeof(MyPassenger)*Queues[2].size);
		Queues[2].passengers[Queues[2].size-1]=queue->passengers[i];
		count++;
	}

	queue->size-=count;
}
///Dequeue from standart queue
void DeQueue_Standart(MyFlight *selected_flight,MyQueue  Queues[],FILE *out){

		int i,j,sold=0;

		MyQueue *queue=&Queues[2];
		int size=queue->size;
		for ( i = 0; i < size; ++i)
		{
			
			if (selected_flight->seat_s  > 0)
			{

				queue->passengers[0].took_class=realloc(queue->passengers[0].took_class,sizeof("standard\0"));

				strcpy(queue->passengers[0].took_class,"standard");
				
				sold++;
				selected_flight->passengers_number_s++;
				selected_flight->passengers_s=realloc(selected_flight->passengers_s,sizeof(MyPassenger)*(selected_flight->passengers_number_s));
				selected_flight->passengers_s[selected_flight->passengers_number_s-1]=queue->passengers[0];
				
				for ( j = 0; j < queue->size-1; ++j)
				{
					queue->passengers[j]=queue->passengers[j+1];
				}

				int lastindex=queue->size-1;

				queue->size--;
				selected_flight->seat_s--;
				selected_flight->sold_s++;
			}
		}
}

//Get Passenger whinch name is giving 
MyPassenger *GetPassenger(MyQueue *AllPasssengers,char *name){


	int i;

	for ( i = 0; i < AllPasssengers->size; ++i)
	{
		
		if (strcmp(AllPasssengers->passengers[i].name,name)==0)
		{
			return &AllPasssengers->passengers[i];
		}
	}

	return NULL;

}

///After Sell  Over Reorganıze Queues
void Sell_OVER(MyFlight *selected_flight,MyQueue  Queues[]){


	MyQueue *standard_queue=&Queues[2];
	MyQueue *economy_queue=&Queues[1];
	MyQueue *business_queue=&Queues[0];

	int i;
	int count=0;

	for ( i = 0; i < standard_queue->size; ++i)
	{
		if (strcmp("business",standard_queue->passengers[i].class)==0)
		{
			business_queue->size++;
			business_queue->passengers=realloc(business_queue->passengers,sizeof(MyPassenger)*(business_queue->size));

			business_queue->passengers[business_queue->size-1]=standard_queue->passengers[i];
			count++;
		}else 	if (strcmp("economy",standard_queue->passengers[i].class)==0)
		{	

			economy_queue->size++;
			economy_queue->passengers=realloc(economy_queue->passengers,sizeof(MyPassenger)*(economy_queue->size));

			economy_queue->passengers[economy_queue->size-1]=standard_queue->passengers[i];
			count++;
		}
	}


	standard_queue->size-=count;



}

////Read Coommands
void Commands(char *key1,char *key2,char *key3,char *key4,char *key5,MyStack  *Stack, FILE *out,MyQueue  *AllPasssengers){


		int i,j;


		if (strcmp(key1,"addseat")==0)
		{

			MyFlight *selected_flight=GetFlight(Stack,key2);

			
			int closed=0;
			if (selected_flight!=NULL  )
			{
				
				
				closed=selected_flight->closed;
				
			}

			if (closed==0)
			{
				Push(key2,key3,key4,Stack,out);
			}else{
				fprintf(out, "error\n");
			}
			


		}else if (strcmp(key1,"enqueue")==0)
		{


			MyFlight *selected_flight=GetFlight(Stack,key2);

			if (selected_flight->closed==0)
			{

			
				MyPassenger  *passenger=Enqueue(key2,key3,key4,key5,selected_flight->Queues,out);


				AllPasssengers->size++;
				AllPasssengers->passengers=realloc(AllPasssengers->passengers,sizeof(MyPassenger)*(AllPasssengers->size));
				AllPasssengers->passengers[AllPasssengers->size-1]=*passenger;

			}else{
				fprintf(out, "error\n");
			}

		}else if (strcmp(key1,"sell")==0)
		{
			
			MyFlight *selected_flight=GetFlight(Stack,key2);

			if (selected_flight->closed==0)
			{
				DeQueue_Business(selected_flight,selected_flight->Queues,out);
				DeQueue_Economy(selected_flight,selected_flight->Queues,out);
			
				DeQueue_Standart(selected_flight,selected_flight->Queues,out);

				Sell_OVER(selected_flight,selected_flight->Queues);

			
				fprintf(out, "sold %s %d %d %d\n", key2,selected_flight->sold_b,selected_flight->sold_e,selected_flight->sold_s);
			}else{
				fprintf(out, "error\n");
			}
			
		}else if (strcmp(key1,"close")==0)
		{

			MyFlight *selected_flight=GetFlight(Stack,key2);

			if (selected_flight!=NULL)
			{
				selected_flight->closed=1;
				int inqueue_number=0;
				for ( i = 0; i < 3; ++i)
				{
					
					inqueue_number+=selected_flight->Queues[i].size;
				}


				int b=selected_flight->sold_b;
				int s=selected_flight->sold_s;
				int e=selected_flight->sold_e;
				int ticket=b+s+e;
				fprintf(out, "closed %s %d %d\n" ,selected_flight->id,ticket,inqueue_number);
				for ( i = 0; i < 3; ++i)
				{
					
					for ( j = 0; j < selected_flight->Queues[i].size; ++j)
					{
						fprintf(out, "waiting %s\n",selected_flight->Queues[i].passengers[j].name );
					}
					
					
				}
			}else{

				fprintf(out, "error\n");
			}


		}else if (strcmp(key1,"report")==0)
		{

			MyFlight *selected_flight=GetFlight(Stack,key2);


			if (selected_flight!=NULL)
			{
				fprintf(out, "report %s\n" ,selected_flight->id);
				fprintf(out, "business %d\n" ,selected_flight->passengers_number_b);
				for (int i = 0; i < selected_flight->passengers_number_b; ++i)
				{
					fprintf(out, "%s\n",selected_flight->passengers_b[i].name );
				}
				fprintf(out, "economy %d\n" ,selected_flight->passengers_number_e);
				for (int i = 0; i < selected_flight->passengers_number_e; ++i)
				{
					fprintf(out, "%s\n",selected_flight->passengers_e[i].name );
				}
				fprintf(out, "standard %d\n" ,selected_flight->passengers_number_s);
				for (int i = 0; i < selected_flight->passengers_number_s; ++i)
				{
					fprintf(out, "%s\n",selected_flight->passengers_s[i].name );
				}
				fprintf(out, "end of report %s\n",selected_flight->id );
			}else{
				fprintf(out, "error\n" );
			}
			


		}else if (strcmp(key1,"info")==0)
		{


			MyPassenger *passenger=GetPassenger(AllPasssengers,key2);

			if (passenger!=NULL)
			{
				if (passenger->error)
				{
					fprintf(out, "error\n");
				}else{
					fprintf(out, "info %s %s %s %s\n", passenger->name,passenger->flight_id,passenger->class,passenger->took_class);

				}
			}else{

				fprintf(out, "error\n");
			}

		}

}
///Read File
void ReadFile(const char  *inputfilename,const char *outputfilename){


	FILE *out=fopen(outputfilename,"w");

	int filesize=SizeofFile(inputfilename);
	char *filedata=(char*)malloc(sizeof(char)*filesize);
	filedata[filesize-1]='\n';
	int i,j,wordsize=0,numberofword=0;

	MyQueue *AllPasssengers=(MyQueue*)malloc(sizeof(MyQueue));

	AllPasssengers->size=0;
	AllPasssengers->passengers=(MyPassenger*)malloc(sizeof(MyPassenger));

	char letter;

	MyStack  *Stack=(MyStack*)malloc(sizeof(MyStack)); 
	Stack->top=0;
	Stack->flights=(MyFlight*)malloc(sizeof(MyFlight));

	char  *key=(char*)malloc(sizeof(char));

	char *key1=(char*)malloc(sizeof(char));
	char *key2=(char*)malloc(sizeof(char));
	char *key3=(char*)malloc(sizeof(char));
	char *key4=(char*)malloc(sizeof(char));
	char *key5=(char*)malloc(sizeof(char));



	int sizeofkey=0,flight_number=0;
	for (int i = 0; i < filesize; ++i)
	{


		key=realloc(key,sizeof(char)*sizeofkey+sizeof(char));
		key[sizeofkey++]=filedata[i];
		
		if (filedata[i]==' ')
		{
			numberofword++;
			key[sizeofkey-1]='\0';
			switch(numberofword){


				case 1:
					key1=realloc(key1,sizeof(key));
					strcpy(key1,key);
					break;
				case 2:
					key2=realloc(key2,sizeof(key));
					strcpy(key2,key);
					break;
				case 3:
					key3=realloc(key3,sizeof(key));
					strcpy(key3,key);
					break;
				case 4:
					key4=realloc(key4,sizeof(key));
					strcpy(key4,key);
					break;
				case 5:
					key5=realloc(key5,sizeof(key));
					strcpy(key5,key);
					break;
			}
			sizeofkey=0;
		
		}else if (filedata[i]=='\n')
		{

			numberofword++;

			key[sizeofkey-2]='\0';

			switch(numberofword){
				case 1:
					key1=realloc(key1,sizeof(key));
					strcpy(key1,key);
					break;
				case 2:
					key2=realloc(key2,sizeof(key));
					strcpy(key2,key);
					break;
				case 3:
					key3=realloc(key3,sizeof(key));
					strcpy(key3,key);
					break;
				case 4:
					key4=realloc(key4,sizeof(key));
					strcpy(key4,key);
					break;
				case 5:
					key5=realloc(key5,sizeof(key));
					strcpy(key5,key);
					break;
			}
			numberofword=0;
			sizeofkey=0;


			Commands(key1,key2,key3,key4,key5,Stack,out,AllPasssengers);

			memset(key1, 0, sizeof(key1));
			memset(key2, 0, sizeof(key2));
			memset(key3, 0, sizeof(key3));
			memset(key4, 0, sizeof(key4));
			memset(key5, 0, sizeof(key5));

		}

	}


	fclose(out);
	free(filedata);

	/*


	char  *flight_id;
	char *name;
	char *class;
	char *priority;
	char  *took_class;*/

	for ( i = 0; i < AllPasssengers->size; ++i)
	{
		
		free(AllPasssengers->passengers[i].flight_id);
		free(AllPasssengers->passengers[i].name);
		free(AllPasssengers->passengers[i].priority);
		free(AllPasssengers->passengers[i].took_class);
	}
	free(AllPasssengers->passengers);

	for ( i = 0; i < Stack->top; ++i)
	{
		free(Stack->flights[i].id);

		for ( j = 0; j < Stack->flights[i].passengers_number_b; ++j)
		{
			free(Stack->flights[i].passengers_b[j].flight_id);
			free(Stack->flights[i].passengers_b[j].name);
			free(Stack->flights[i].passengers_b[j].class);
			free(Stack->flights[i].passengers_b[j].priority);
			free(Stack->flights[i].passengers_b[j].took_class);
		}
		free(Stack->flights[i].passengers_b);

		for ( j = 0; j < Stack->flights[i].passengers_number_s; ++j)
		{
			free(Stack->flights[i].passengers_s[j].flight_id);
			free(Stack->flights[i].passengers_s[j].name);
			free(Stack->flights[i].passengers_s[j].class);
			free(Stack->flights[i].passengers_s[j].priority);
			free(Stack->flights[i].passengers_s[j].took_class);
		}
		free(Stack->flights[i].passengers_s);


		for ( j = 0; j < Stack->flights[i].passengers_number_e; ++j)
		{
			free(Stack->flights[i].passengers_e[j].flight_id);
			free(Stack->flights[i].passengers_e[j].name);
			free(Stack->flights[i].passengers_e[j].class);
			free(Stack->flights[i].passengers_e[j].priority);
			free(Stack->flights[i].passengers_e[j].took_class);
		}
		free(Stack->flights[i].passengers_e);
		
	}

	free(Stack);

	free(key);free(key1);free(key2);free(key3);free(key4);free(key5);

}

int main(int argc, char const *argv[])
{
	ReadFile(argv[1],argv[2]);
	return 0;
}