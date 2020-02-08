#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>

typedef struct vector {

   	int  *array;
    
    char *name;

    int size;

} vector_t;


typedef struct matrix {


	int **array;

    int row;

    int column;
    
    char *name;

} matrix_t;
void Write_VectorData(vector_t *vector,FILE *out){


	
	int i;
	for ( i = 0; i < vector->size; ++i)
	{	
		fprintf(out,"%d ",vector->array[i]);
		
	}
	fprintf(out,"\n");
	
}

void Write_MatrixData(matrix_t *matrix,FILE *out){


	int i,j;

    for (i = 0; i <  matrix->row; i++){

    	for (j = 0; j < matrix->column; j++) {
        	
        	fprintf(out,"%d ", matrix->array[i][j]);
     	}
		fprintf(out,"\n");
     
    }

}
vector_t   *CreateVector(FILE *out,char *name,int size){
		

	vector_t  *vector=vector=malloc(sizeof(vector_t));
	vector->size=size;
	vector->array = (int*)malloc(vector->size * sizeof(int)); 
	vector->name=(char*)malloc(sizeof(char)+sizeof(name));
	strcpy(vector->name,name);

	int i;
	for ( i = 0; i < vector->size; ++i)
	{	
		
		vector->array[i]=0;
	}

	fprintf(out,"created vector %s %d \n",vector->name,vector->size );
	return vector;
}

matrix_t * CreateMatrix(FILE *out,char *name,int row,int column){
		

	matrix_t  *matrix=malloc(sizeof(matrix_t));
	matrix->row=row;
	matrix->column=column;
	matrix->name=malloc(sizeof(char)+sizeof(name));
	strcpy(matrix->name,name);

	 int i,j;

	matrix->array = (int **)malloc(sizeof(int *)*matrix->row); 
    for (i=0; i<matrix->row; ++i){
    	
    	*(matrix->array+i) = (int *)malloc(sizeof(int)*matrix->column);  
    }
     for (i = 0; i <  matrix->row; i++){

    	for (j = 0; j < matrix->column; j++) {
        	matrix->array[i][j] =0;
        	
     	}
     	
    }


    fprintf(out,"created matrix %s %d %d \n",matrix->name,matrix->row,matrix->column );

    return matrix;
}


vector_t  *CreateVectorFromFile(FILE *out,char *filename,const char *foldername){


	char *path;

	path=(char*)malloc(sizeof(foldername)+sizeof(filename)+sizeof(char));

	strcpy(path,foldername);
	strcat(path,"/");
	strcat(path,filename);
	strcat(path,"\0");
	

	int x=strlen(path);
	path[x-1]='\0';
	x=strlen(filename);
	filename[x-1]='\0';
	FILE  *file;

	int value;

	int size=0;
	if((file = fopen(path, "r")) == NULL) {
        printf("No such %s file\n", filename);
        exit(1);
    }
    
	while(fscanf(file, "%d", &value) != EOF){  

			size++;
	}

   
   	vector_t  *vector=vector=malloc(sizeof(vector_t));
	vector->size=size;
	vector->array = (int*)malloc(vector->size * sizeof(int)); 
	vector->name=(char*)malloc(sizeof(char)+sizeof(filename));
	strcpy(vector->name,filename);
  
	fclose(file);
	int i=0;
	if((file = fopen(path, "r")) == NULL) {
        printf("No such %s file\n", filename);
        exit(1);
    }

	while(fscanf(file, "%d", &value) != EOF){  

			vector->array[i++]=value;

	}
	fprintf(out,"read vector %s %d \n",vector->name,vector->size );
	return vector;
	

}

matrix_t *CreateMatrixFromFile(FILE *out,char *filename,const char *foldername){

	char *path;

	path=(char*)malloc(sizeof(foldername)+sizeof(filename)+sizeof(char));

	strcpy(path,foldername);
	strcat(path,"/");
	strcat(path,filename);
	strcat(path,"\0");
	int x=strlen(path);
	path[x-1]='\0';
	x=strlen(filename);
	filename[x-1]='\0';
	FILE  *add=fopen(path,"a");
	fprintf(add, "\n");
	fclose(add);
	FILE  *file=fopen(path,"r");
	
	
    char ch;
    int row=0,column=0;
    int data,number=0,stop=0;
	

    int value;
    int read=0;
    int keynumber=0;
    int count=0;
  	while(fscanf(file, "%d%c", &value,&ch) != EOF){  

			
		if (ch==' ')
		{
		
		}else{

			row++;
			
		}
		
		count++;
	}

 	column=count/row;

   	matrix_t  *matrix=malloc(sizeof(matrix_t));
	matrix->row=row;
	matrix->column=column;
	matrix->name=malloc(sizeof(char)+sizeof(filename));
	strcpy(matrix->name,filename);

	int i,j;

	matrix->array = (int **)malloc(sizeof(int *)*matrix->row); 
    for (i=0; i<matrix->row; ++i){
    	
    	*(matrix->array+i) = (int *)malloc(sizeof(int)*matrix->column);  
    }

    
    fclose(file);

    file=fopen(path,"r");
   
    i=0;j=0;
  	while(fscanf(file, "%d", &data) != EOF){  

			matrix->array[i][j]=data;
			j++;
			if (j==matrix->column)
			{
			    i++;
			    j=0;
			}    		
    }
 	
  	fclose(file);


    fprintf(out,"read matrix %s %d %d \n",matrix->name,matrix->row,matrix->column );
    return matrix;

}


vector_t  *FinVector(char *name,vector_t  *vectors,int number_of_vector){

	int i;

	for ( i = 0; i < number_of_vector; ++i)
	{
		if (strcmp(vectors[i].name,name)==0)
		{
			
			return &vectors[i];
		}
	}

	return NULL;
}

matrix_t  *FinMatrix(char *name,matrix_t  *matrices,int number_of_matrix){

	int i;

	for ( i = 0; i < number_of_matrix; ++i)	
	{


		if (strcmp(matrices[i].name,name)==0)
		{
			
			return &matrices[i];
		}
	}

	return NULL;
}

matrix_t  *ConcatenationVectorToMatrix(FILE *out,vector_t  *v1,vector_t *v2, char *direction,char *name){


	matrix_t  *matrix=malloc(sizeof(matrix_t));
	matrix->name=malloc(sizeof(char)+sizeof(name));
	strcpy(matrix->name,name);


	int i,j;
	if (strcmp("row",direction)==0)
	{
	
	
		int column=v1->size;
		if (column >v2->size)
		{
			column=v2->size;
		}
		matrix->row=2;
		matrix->column=column;



		matrix->array = (int **)malloc(sizeof(int *)*matrix->row); 
	    for (i=0; i<matrix->row; ++i){
	    	
	    	*(matrix->array+i) = (int *)malloc(sizeof(int)*matrix->column);  
	    }

	    for ( i = 0; i < matrix->column; ++i)
	    {
	    	matrix->array[0][i]=v1->array[i];
	    	matrix->array[1][i]=v2->array[i];
	    }
	}else if (strcmp("column",direction)==0)
	{
		int row=v1->size;
		if (row >v2->size)
		{
			row=v2->size;
		}
		matrix->row=row;
		matrix->column=2;

		matrix->array = (int **)malloc(sizeof(int *)*matrix->row); 
	    for (i=0; i<matrix->row; ++i){
	    	
	    	*(matrix->array+i) = (int *)malloc(sizeof(int)*matrix->column);  
	    }

	    for ( i = 0; i < matrix->row; ++i)
	    {
	    	matrix->array[i][0]=v1->array[i];
	    	matrix->array[i][1]=v2->array[i];
	    }
		
	}else {


		return NULL;
	}
	fprintf(out,"vectors concatenated  %s %d %d\n", matrix->name,matrix->row,matrix->column);
	return matrix;


}


int ConcatenationMatrixToMatrix(FILE *out,matrix_t *m1,matrix_t *m2,char *direction){

	int column=m1->column;
	int row=m1->row;
	int i,j;
	direction[1]='\0';
	if (strcmp("d",direction)==0  && m1->column==m2->column)
	{	
		row+=m2->row;

		m1->array = (int **)realloc(m1->array,sizeof(int**)*row); 

		for ( i = m1->row; i <row; ++i)
		{		

			m1->array[i] = (int *)malloc(sizeof(int)*m1->column); 

		}

		for ( i = m1->row; i < row; ++i)
		{	
			
			for ( j = 0; j < m1->column; ++j)
			{
				m1->array[i][j]=m2->array[i-m1->row][j];
			}
		}

		m1->row=row;
	}else if (strcmp("r",direction)==0  && m1->row==m2->row)
	{		
		column+=m2->column;


		for ( i = 0; i < m1->row; ++i)
		{
			*(m1->array+i) = (int *)realloc(*(m1->array+i), sizeof(int)*column);

		}

		for ( i = 0; i < m1->row; ++i)
		{	
			
			for ( j = m1->column; j < column; ++j)
			{
				m1->array[i][j]=m2->array[i][j-m1->column];
			}
		}

		m1->column=column;

		
	}else{
		fprintf(out,"error \n");
		return 1;
	}

	fprintf(out,"matrices concatenated %s %d %d \n",m1->name,m1->row,m1->column);

	return 0;
}


//Concatenation Matrix and Vector
int ConcatenationMatrixandVector(FILE *out,matrix_t  *matrix,vector_t *vector,char *where){

	int i;
	where[1]='\0';
	if (strcmp(where,"d")==0)
	{
		if (matrix->column==vector->size)
		{
			int row_size=matrix->row+1;
	

			//A = (int **)realloc(A, sizeof(int **)*M);
			matrix->array = (int **)realloc(matrix->array,sizeof(int**)*row_size); 

			for ( i = matrix->row; i < row_size; ++i)
			{
				matrix->array[i] = (int *)malloc(sizeof(int)*matrix->column);    
			}

			for ( i = 0; i < matrix->column; ++i)
			{
				matrix->array[row_size-1][i]=vector->array[i];
			}

			matrix->row=row_size;
		}
	}else if (strcmp(where,"r")==0){

		if (matrix->row==vector->size)
		{
			int column_size=matrix->column+1;
	

		

			for ( i = 0; i < column_size; ++i)
			{
				*(matrix->array+i) = (int *)realloc(*(matrix->array+i), sizeof(int)*column_size);
			}

			for ( i = 0; i < matrix->row; ++i)
			{
				matrix->array[i][column_size-1]=vector->array[i];
			}

			matrix->column=column_size;
		}
	}else{

		fprintf(out,"error\n");
		return 1;
	}
	fprintf(out,"matrix and vector concatenated %s %d %d\n",matrix->name,matrix->row,matrix->column);
	
	return 0;
}
///Find max value in this row index
int FindMaxROW(matrix_t *matrix,int rowindex){


	int max=INT_MIN;
	int i;
	for ( i = 0; i < matrix->column; ++i)
	{
		if (matrix->array[rowindex][i]>max)
		{
			max=matrix->array[rowindex][i];
		}
	}
	return max;

}
//Find min value of this row
int  FindMinROW(matrix_t *matrix,int rowindex){


	int min=INT_MAX;
	int i;
	for ( i = 0; i < matrix->column; ++i)
	{
		if (matrix->array[rowindex][i]<min)
		{
			min=matrix->array[rowindex][i];
		}
	}
	return min;

}
///Find maximun vale in this column
int FindMaxCOl(matrix_t *matrix,int colindex){
	int max=INT_MIN;
	int i;
	for ( i = 0; i < matrix->row; ++i)
	{
		if (matrix->array[i][colindex]>max)
		{
			max=matrix->array[i][colindex];
		}
	}
	return max;

}
///find min value in this colummn
int FindMinCOl(matrix_t *matrix,int colindex){
	int min=INT_MAX;
	int i;
	for ( i = 0; i < matrix->row; ++i)
	{
		if (matrix->array[i][colindex]<min)
		{
			min=matrix->array[i][colindex];
		}
	}
	return min;

}
void PadMatrix(FILE *out,matrix_t  *matrix,int x,int y,char *mode){

	int i,j;

	int s=strlen(mode);
	mode[s-1]='\0';
	int column_size=matrix->column+y;
	int row_size=matrix->row+x;

	matrix->array = (int **)realloc(matrix->array,sizeof(int**)*row_size); 

	int min=INT_MAX;
	int max=INT_MIN;
	for ( i = 0; i < row_size ; ++i)
	{		
			if (i<matrix->row)
			{
				*(matrix->array+i) = (int *)realloc(*(matrix->array+i), sizeof(int)*column_size);

			}else{
				*(matrix->array+i) = (int *)malloc(sizeof(int)*column_size);  
			}
	}
	
	for ( i = 0; i < row_size; ++i)
	{	
		
		
		for ( j =  matrix->column; j < column_size; ++j)
		{	
			if (strcmp(mode,"maximum")==0)
			{
				matrix->array[i][j]=FindMaxROW(matrix,i);
			}else{
				matrix->array[i][j]=FindMinROW(matrix,i);
			}
			
		}

	}
	for ( i = matrix->row; i < row_size; ++i)
	{	
		
		
		for ( j =  0; j < column_size; ++j)
		{	
			if (strcmp(mode,"maximum")==0)
			{
				matrix->array[i][j]=FindMaxCOl(matrix,j);
			}else{
				matrix->array[i][j]=FindMinCOl(matrix,j);
			}
			
		}

	}

	matrix->row=row_size;
	matrix->column=column_size;
	fprintf(out,"matrix paded %s %d %d\n",matrix->name,matrix->row,matrix->column);

}

///pad matrix then set giving value to new places
void PadValMatrix(FILE *out,matrix_t  *thematrix,int x,int y,int value){

	int i,j;
	int row_size=thematrix->row+x;
	int column_size=thematrix->column+y;

	//A = (int **)realloc(A, sizeof(int **)*M);
	thematrix->array = (int **)realloc(thematrix->array,sizeof(int**)*row_size); 
    for (i=0; i<thematrix->row +x; i++){
    	
    	//*(A+i) = (int *)realloc(*(A+i), sizeof(int)*M);
    	if (i<thematrix->row)
    	{
    		*(thematrix->array+i) = (int *)realloc(*(thematrix->array+i), sizeof(int)*column_size);
    	}else{
    		thematrix->array[i] = (int *)malloc(sizeof(int)*column_size);    
    	}
    	
    }


    for ( i = thematrix->row; i < row_size; ++i)
    {
    	for ( j = 0; j < column_size; ++j)
    	{
    		thematrix->array[i][j]=value;
    	}
    }
    for ( i = thematrix->column; i < column_size; ++i)
    {
    	for ( j = 0; j < row_size; ++j)
    	{
    		thematrix->array[j][i]=value;
    	}
    }

    thematrix->row=row_size;
    thematrix->column=column_size;

	fprintf(out,"matrix paded %s %d %d\n",thematrix->name,thematrix->row,thematrix->column );
   
}

///take vector from vector
vector_t * TakeVectorFromVector(FILE *out,vector_t *mainvector,int start,int stop,char *name){


	int i;
	

	int x=strlen(name);
	name[x-1]='\0';
	
	vector_t  *vector=vector=malloc(sizeof(vector_t));
	
	
	vector->name=(char*)malloc(sizeof(char)+sizeof(name));
	strcpy(vector->name,name);

	vector->size=stop-start;
	if (stop>mainvector->size)
	{
		stop=vector->size;
		vector->size=stop-start;
	}
	vector->array = (int*)malloc(vector->size * sizeof(int)); 
	
	

	for ( i = start; i < stop; ++i)
	{	
		vector->array[i-start]=mainvector->array[i];

		//printf("-------  : %d\n",&mainvector->array[i] );
		
	}
	fprintf(out,"vector sliced %s %d\n",vector->name,vector->size );

	return vector;

}

vector_t *  TakeVectorFromMatrixColumn(FILE *out,matrix_t  *matrix,int index ,int start ,int stop,char *name){


	int i;

	int x=strlen(name);
	name[x-1]='\0';
	
	vector_t  *vector=vector=malloc(sizeof(vector_t));
	
	
	vector->name=(char*)malloc(sizeof(char)+sizeof(name));
	strcpy(vector->name,name);

	int  size=stop-start;
	vector->size=size;

	vector->array = (int*)malloc(vector->size * sizeof(int)); 

	for (i = start; i < stop; ++i)
	{

		vector->array[i-start]=matrix->array[i][index];
	}

	fprintf(out,"vector sliced %s %d\n",vector->name,vector->size);

	return vector;

}

vector_t * TakeVectorFromMatrixRow(FILE *out,matrix_t  *matrix,int index ,int start ,int stop,char *name){

	int i;
	int x=strlen(name);
	name[x-1]='\0';
		
	vector_t  *vector=vector=malloc(sizeof(vector_t));
	
	
	vector->name=(char*)malloc(sizeof(char)+sizeof(name));
	strcpy(vector->name,name);

	
	
	int  size=stop-start;
	vector->size=size;

	vector->array = (int*)malloc(vector->size * sizeof(int)); 

	for (i = start; i < stop; ++i)
	{

		vector->array[i-start]=matrix->array[index][i];
	}

	fprintf(out,"vector sliced %s %d\n",vector->name,vector->size);

	return vector;
	
}

///take matrix from matrix 
matrix_t * TakeMatrixFromMatrix(FILE *out,matrix_t *mainmatrix,int x1,int x2,int y1,int y2,char *name){



	int i,j;
	int x=strlen(name);
	name[x-1]='\0';

	matrix_t  *newmatrix=malloc(sizeof(matrix_t));

	newmatrix->name=malloc(sizeof(char)+sizeof(name));
	strcpy(newmatrix->name,name);

	newmatrix->row=y2-y1;
	newmatrix->column=x2-x1;
	newmatrix->array = (int **)malloc(sizeof(int *)*newmatrix->row); 
    for (i=0; i<newmatrix->row; ++i){
    	
    	*(newmatrix->array+i) = (int *)malloc(sizeof(int)*newmatrix->column);  
    }

	
	for ( i = y1; i < y2; ++i)
    {
    	for ( j = x1; j < x2; ++j)
    	{
    	
    		newmatrix->array[i-y1][j-x1]=mainmatrix->array[i][j];
    	}
    }
    
    fprintf(out,"matrix sliced %s %d %d\n", newmatrix->name,newmatrix->row,newmatrix->column);
    
    return newmatrix;
   

}

int AddMatrix(FILE *out,matrix_t *matrix1,matrix_t *matrix2){

	
	int j,i;

	
	if (matrix1->row!=matrix2->row  || matrix1->column!=matrix2->column )
	{
		fprintf(out, "error\n");
		return  1;
	}

	for ( i = 0; i < matrix1->row; ++i)
	{
		for (j = 0; j < matrix1->column; ++j)
		{
			matrix1->array[i][j]=matrix1->array[i][j]+matrix2->array[i][j];
		}
	}

	fprintf(out,"add %s %s\n", matrix1->name,matrix2->name);

	return 0;
}

//Multipl two matrix each other
int MultiplyMatrix(FILE *out,matrix_t *matrix1,matrix_t *matrix2){


	int j,i;

	if (matrix1==NULL  || matrix2==NULL)
	{
		fprintf(out, "error\n");
		return  1;
	}
	if (matrix1->row!=matrix2->row  || matrix1->column!=matrix2->column )
	{
		fprintf(out, "error\n");
		return  1;
	}
	
	
	
   for ( i = 0; i < matrix1->row; ++i)
	{
		for (j = 0; j < matrix1->column; ++j)
		{
			matrix1->array[i][j]=matrix1->array[i][j]*matrix2->array[i][j];
		}
	}

	
	fprintf(out,"multiply %s %s\n", matrix1->name,matrix2->name);
	
	return 0;
}
///Subract two marix each other
int SubtractMatrix(FILE *out,matrix_t *matrix1,matrix_t *matrix2){


	int j,i;

	if (matrix1==NULL  || matrix2==NULL)
	{
		
		fprintf(out, "error\n");
		return 1;
	}
	if (matrix1->row!=matrix2->row  || matrix1->column!=matrix2->column )
	{
		
		fprintf(out, "error\n");
		return 1;
	}

	for ( i = 0; i < matrix1->row; ++i)
	{
		for (j = 0; j < matrix1->column; ++j)
		{
			matrix1->array[i][j]=matrix1->array[i][j]-matrix2->array[i][j];
		}
	}

	fprintf(out,"subtract %s %s\n", matrix1->name,matrix2->name);

	return 0;
}
int main(int argc, char const *argv[])
{

	char ch;
	FILE *write=fopen(argv[2], "a");;
 	
	
	fprintf(write, "\n" );
	
	fclose(write);
	FILE *filePointer;
    
   
    filePointer = fopen(argv[2], "r");
   	FILE   *out=NULL;
   	out=fopen(argv[3],"w");
    int read=0;

    vector_t *vectors = (vector_t *)malloc( sizeof(vector_t));
	matrix_t *matrices = (matrix_t *)malloc(sizeof(matrix_t));
	int number_of_vector=0;
	int number_of_matrix=0;
	int i;
    char *key;
    key=(char*)malloc(sizeof(char));
    char *key1;
    key1=(char*)malloc(sizeof(char));
    char *key2;
    key2=(char*)malloc(sizeof(char));
    char *key3;
    key3=(char*)malloc(sizeof(char));
    char *key4;
    key4=(char*)malloc(sizeof(char));
    char *key5;
    key5=(char*)malloc(sizeof(char));
    char *key6;
    key6=(char*)malloc(sizeof(char));
    char *key7;
    key7=(char*)malloc(sizeof(char));



    int keynumber=0;
    while ((ch = fgetc(filePointer)) != EOF)
    {
        


    	key=realloc(key,(read+1)*sizeof(char));
    	key[read]=ch;
    	read++;
    	if (ch==' ' ||  ch=='\n')
        {
        	key[read-1]='\0';

        	


        	if (keynumber==0)
        	{
        		key1=realloc(key1,(read+1)*sizeof(char));
        		strcpy(key1,key);
        	
		     		
        	}else if (keynumber==1)
        	{
        		key2=realloc(key2,(read+1)*sizeof(char));
        		strcpy(key2,key);
        	
        	}else if (keynumber==2)
        	{
        		key3=realloc(key3,(read+1)*sizeof(char));
        		strcpy(key3,key);
        		
        	}else if (keynumber==3)
        	{
        		key4=realloc(key4,(read+1)*sizeof(char));
        		strcpy(key4,key);
        	
        	}else if (keynumber==4)
        	{
        		key5=realloc(key5,(read+1)*sizeof(char));
        		strcpy(key5,key);
        		
        	}else if (keynumber==5)
        	{
        		key6=realloc(key6,(read+1)*sizeof(char));
        		strcpy(key6,key);
        		
        	}else if (keynumber==6)
        	{
        		key7=realloc(key7,(read+1)*sizeof(char));
        		strcpy(key7,key);
        		
        	}
        	keynumber++;
        	
        	read=0;

        	if (ch=='\n')
        	{	

        		
        		keynumber=0;

		     	if (strcmp("veczeros",key1)==0)
		     	{
		     		

		     		
		     		number_of_vector++;
		     		vectors = (vector_t *)realloc(vectors, number_of_vector * sizeof(vector_t));
		     		int size=atoi(key3);
		     		vector_t  *vector=   CreateVector(out,key2,size);

		     		vectors[number_of_vector-1]=*vector;
		     		
		     		Write_VectorData(vector,out);
		     	}else if (strcmp("matzeros",key1)==0)
		     	{
		     			
		     		
		     		number_of_matrix++;
		     		matrices = (matrix_t *)realloc(matrices, number_of_matrix * sizeof(matrix_t));
		     		int row=atoi(key3);
		     		int column=atoi(key4);
		     		matrix_t  *matrix=   CreateMatrix(out,key2,row,column);



		     		Write_MatrixData(matrix,out);

		     		char *token = strtok(matrix->name, "."); 
		     		strcpy(matrix->name,token);
		     		matrices[number_of_matrix-1]=*matrix;

		     		
		     	}else if (strcmp("vecread",key1)==0)
		     	{
		     			

		     			
		     		number_of_vector++;
		     		vectors = (vector_t *)realloc(vectors, number_of_vector * sizeof(vector_t));
		     		vector_t  *vector=  CreateVectorFromFile(out,key2,argv[1]);

		     		
		     		
		     		Write_VectorData(vector,out);

		     		
   					char *name =strtok (vector->name,".");
		     		
		     		strcpy(vector->name,name);

		     		vectors[number_of_vector-1]=*vector;

		     	}else if (strcmp("matread",key1)==0)
		     	{
		     		
		 			
		 			
		     		number_of_matrix++;
		     		matrices = (matrix_t *)realloc(matrices, number_of_matrix * sizeof(matrix_t));
		     		
		     		matrix_t  *matrix=   CreateMatrixFromFile(out,key2,argv[1]);


		     		Write_MatrixData(matrix,out);
		     		char *name =strtok (matrix->name,".");
		     		
		     		strcpy(matrix->name,name);

		     		
		     		matrices[number_of_matrix-1]=*matrix;
		     		

		     	}else if (strcmp("vecstack",key1)==0)
		     	{
		     		
		     		
		     		
		     		vector_t *v1=FinVector(key2,vectors,number_of_vector);
		     		vector_t *v2=FinVector(key3,vectors,number_of_vector);

		     		if (v1==NULL ||  v2 ==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{


		     			matrix_t  *matrix=  ConcatenationVectorToMatrix(out,v1,v2,key4,key5);
		     			if (matrix!=NULL)
		     			{
		     				number_of_matrix++;
		     				matrices = (matrix_t *)realloc(matrices, number_of_matrix * sizeof(matrix_t));
		     			
		     				matrices[number_of_matrix-1]=*matrix;
		     				Write_MatrixData(matrix,out);
		     			}else{

		     				fprintf(out,"error\n");
		     			}
		     			
		     		}
		     		
		     	}else if (strcmp("matstack",key1)==0)
		     	{
		     		

		     		
		     		
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);
		     		matrix_t  *m2=FinMatrix(key3,matrices,number_of_matrix);

		     		if (m1==NULL ||  m2==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int error=ConcatenationMatrixToMatrix(out,m1,m2,key4);

		     			if (!error)
		     			{
		     				Write_MatrixData(m1,out);
		     			}
		     			
		     		}
		     		

		     	}else if (strcmp("mvstack",key1)==0)
		     	{

		     		
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);
		     		vector_t *v1=FinVector(key3,vectors,number_of_vector);
		     		if (m1==NULL ||  v1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int error=ConcatenationMatrixandVector(out,m1,v1,key4);
		     			if (!error)
		     			{
		     				Write_MatrixData(m1,out);
		     			}
		     		}

		     	}else if (strcmp("pad",key1)==0)
		     	{
		     		
		     	
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);

		     		if (m1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{
		     			
		     			int x=atoi(key3);
		     			int y=atoi(key4);

		     			PadMatrix(out,m1,x,y,key5);
		     			Write_MatrixData(m1,out);

		     		}
		     	

		     	}else if (strcmp("padval",key1)==0)
		     	{

		     	
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);

		     		if (m1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int x=atoi(key3);
		     			int y=atoi(key4);
		     			int value=atoi(key5);
		     			PadValMatrix(out,m1,x,y,value);
		     			Write_MatrixData(m1,out);
		     			
		     		}
		     		
		     	}
		     	else if (strcmp("vecslice",key1)==0)
		     	{

		     			

		     			vector_t *v1=FinVector(key2,vectors,number_of_vector);

		     			if (v1==NULL)
		     			{
		     				fprintf(out,"error\n");
		     			}else{
		     				int start=atoi(key3);
		     				int stop=atoi(key4);
		     			

		     				
		     			
		     				number_of_vector++;
		     				
		     				vector_t  *vector=TakeVectorFromVector(out,v1,start,stop,key5);


							vectors = (vector_t *)realloc(vectors, number_of_vector * sizeof(vector_t));
		     				
		     				vectors[number_of_vector-1]=*vector;
		     				Write_VectorData(vector,out);
		     			}
		     			
		     	}else if (strcmp("matslicecol",key1)==0)
		     	{

		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);

		     		if (m1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int index=atoi(key3);
		     			int start=atoi(key4);
		     			int stop=atoi(key5);
		     			//printf("keys -%s-%s-%s-%s-%s-%s\n",key1,key2,key3, key4,key5,key6);
		     			vector_t *vector=TakeVectorFromMatrixColumn(out,m1,index,start,stop,key6);
		     			if (vector!=NULL)
		     			{

		     				number_of_vector++;
		     				vectors = (vector_t *)realloc(vectors, number_of_vector * sizeof(vector_t));
		     				vectors[number_of_vector-1]=*vector;
		     				Write_VectorData(vector,out);
		     			}
		     			
		     			
		     		}

		     		
		     	}else if (strcmp("matslicerow",key1)==0)
		     	{
		     		
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);

		     		if (m1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int index=atoi(key3);
		     			int start=atoi(key4);
		     			int stop=atoi(key5);
		     			//printf("keys -%s-%s-%s-%s-%s-%s\n",key1,key2,key3, key4,key5,key6);
		     			vector_t *vector=TakeVectorFromMatrixRow(out,m1,index,start,stop,key6);
		     			if (vector!=NULL)
		     			{

		     				number_of_vector++;
		     				vectors = (vector_t *)realloc(vectors, number_of_vector * sizeof(vector_t));
		     				vectors[number_of_vector-1]=*vector;
		     				Write_VectorData(vector,out);
		     			}
		     			
		     			
		     		}
		     	}

		     	else if (strcmp("matslice",key1)==0)
		     	{
		     		
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);

		     		if (m1==NULL)
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int x1=atoi(key3);
		     			int x2=atoi(key4);
		     			int y1=atoi(key5);
		     			int y2=atoi(key6);
		     			//printf("keys -%s-%s-%s-%s-%s-%s\n",key1,key2,key3, key4,key5,key6);
		     			matrix_t *matrix=TakeMatrixFromMatrix(out,m1,x1,x2,y1,y2,key7);
		     			if (matrix!=NULL)
		     			{

		     				number_of_matrix++;
		     				matrices = (matrix_t *)realloc(matrices, number_of_matrix * sizeof(matrix_t));
		     				matrices[number_of_matrix-1]=*matrix;
		     				Write_MatrixData(matrix,out);
		     			}
		     			
		     			
		     		}
		     	}else if (strcmp("add",key1)==0)
		     	{
		     			
		     		
		     		i=strlen(key3);
		     		key3[i-1]='\0'; 
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);
		     		matrix_t  *m2=FinMatrix(key3,matrices,number_of_matrix);

		     		if (m1==NULL )
		     		{
		     			fprintf(out,"error\n");
		     		}else if (m2==NULL )
		     		{
		     			fprintf(out,"error\n");
		     		}else{

		     			int error=AddMatrix(out,m1,m2);

		     			if (!error)
		     			{
		     				Write_MatrixData(m1,out);
		     			}
		     		}
		     		
		     	}else if (strcmp("multiply",key1)==0)
		     	{

		     			
		     		i=strlen(key3);
		     		key3[i-1]='\0'; 
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);
		     		matrix_t  *m2=FinMatrix(key3,matrices,number_of_matrix);



		     		if (m1==NULL || m2==NULL)
		     		{
		     			fprintf(out, "error\n");
		     		}else{

		     			int error=MultiplyMatrix(out,m1,m2);

		     			if (!error)
		     			{
		     				Write_MatrixData(m1,out);
		     			}
		     		}
		     		//MultiplyMatrix(inputfile,matrixes);
		     	}else if (strcmp("subtract",key1)==0)
		     	{

		     			
		     		i=strlen(key3);
		     		key3[i-1]='\0'; 
		     		matrix_t  *m1=FinMatrix(key2,matrices,number_of_matrix);
		     		matrix_t  *m2=FinMatrix(key3,matrices,number_of_matrix);

		     		if (m1==NULL || m2==NULL)
		     		{
		     			fprintf(out, "error\n");
		     		}else{

		     			int error=SubtractMatrix(out,m1,m2);

		     			if (!error)
		     			{
		     				Write_MatrixData(m1,out);
		     			}
		     		}
		     		
		     		//SubtractMatrix(inputfile,matrixes);
		     	}else{

		     		
		     			if (strlen(key1)>1)
		     			{
		     					fprintf(out, "error\n");
		     			}
		     		
		     		
		     		
		     	}
        	}
        }
    }

    int j;
    int k;
    
    /*

    printf("--------------------------------------------------------------------------------------print all vector \n");
    for ( i = 0; i < number_of_vector; ++i)
    {
    	printf("************** name : -%s- size : -%d- \n",vectors[i].name,vectors[i].size);
    	for (j = 0; j < vectors[i].size; ++j)
    	{
    		printf("%d ", vectors[i].array[j] );
    	}
    	printf("\n");
    }
       printf("print all matrices \n");
    for ( i = 0; i < number_of_matrix; ++i)
    {
    	printf("************** -%s-  -%d- -%d- \n",matrices[i].name,matrices[i].row,matrices[i].column);
    	for (j = 0; j < matrices[i].row; ++j)
    	{
    		for ( k = 0; k <  matrices[i].column; ++k)
    		{		
    			//printf(" j : %d  k :  %d\n", j,k);
    				printf("%d ",matrices[i].array[j][k]);
    		}
    		printf("\n");
    	}
    	
    }*/
    

    free(key);
    free(key1);
    free(key2);
    free(key3);

    fclose(filePointer);
    fclose(out);

	return 0;
}
