#include <stdio.h>
#include <stdlib.h>
#include <string.h>



typedef struct Vector 
{ 
   	char *name; 
   	int *data;
   	int length;
   	struct	Vector  *next;

}  MyVector;

typedef struct Matrix 
{ 
   	char *name; 
   	int **data;
   	int row;
   	int col;
   	struct	Matrix  *next;
}  MyMatrix;



MyVector  *GetVector(char *name,MyVector *VectorHead);
MyMatrix  *GetMatrix(char  *name,MyMatrix *MatrixHead);
MyVector *  AddVectorCollection(MyVector *vector,MyVector *VectorHead);
MyMatrix * AddMatrixCollection(MyMatrix *matrix,MyMatrix *MatrixHead);
MyVector * CreateVector(MyVector *vector,MyVector *VectorHead);
void PrintVector(MyVector *vector,FILE *result_file);
MyMatrix * CreateMatrix(MyMatrix *matrix,MyMatrix *MatrixHead);
char *AllocateString(char *string);
void PrintMAtrix(MyMatrix *matrix,FILE *result_file);
MyVector * CreateVector_fromFile(MyVector *vector ,char alldata[],MyVector *VectorHead);
MyMatrix *  CreateMatrix_fromFile(MyMatrix *matrix,FILE *file,char folderpath[],MyMatrix *MatrixHead);
void AllocateNewMemoryforMatrix(MyMatrix *matrix,int row,int col);
int NumberInRow(int index,MyMatrix *matrix,char mode[]);
int NumberInCol(int index,MyMatrix *matrix,char mode[]);
MyVector * Read_veczeros(FILE *commad_file,FILE *result_file,MyVector *VectorHead);
MyMatrix * Read_matzeros(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead);
MyVector * Read_vecread(FILE *commad_file,FILE *result_file,const char folder[],MyVector *VectorHead);
MyMatrix * Read_matread(FILE *commad_file,FILE *result_file,const char folder[],MyMatrix *MatrixHead);
MyMatrix * Read_vecstack(FILE *commad_file,FILE *result_file,MyVector *VectorHead,MyMatrix *MatrixHead);
MyMatrix * Read_matstack(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead);
MyMatrix * Read_mvstack(FILE *commad_file,FILE *result_file,MyVector *VectorHead,MyMatrix *MatrixHead);
MyMatrix * Read_pad(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead);
MyMatrix * Read_padval(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead);
MyVector * Read_vecslice(FILE *commad_file,FILE *result_file,MyVector *VectorHead);
MyVector * Read_matsliceRowandCol(FILE *commad_file,FILE *result_file,char word[],MyVector *VectorHead,MyMatrix *MatrixHead);
MyMatrix * Read_matslice(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead);
void Math(FILE *commad_file,FILE *result_file,char word[],MyMatrix *MatrixHead);





int main(int argc, char const *argv[])
{
	

	char *word;
	
	word=AllocateString(word);
	FILE *result_file=fopen(argv[3],"w");
	FILE *commad_file=fopen(argv[2],"r");


	MyVector  *VectorHead=NULL;

	MyMatrix  *MatrixHead=NULL;

	if (commad_file==NULL)
	{
		fprintf(result_file,"error\n");
	}

    while(fscanf(commad_file, "%s", word) != EOF){  

    	if (strcmp(word,"veczeros")==0)
    	{
    		VectorHead=Read_veczeros(commad_file,result_file,VectorHead);
    	}else if (strcmp(word,"matzeros")==0)
    	{
    		MatrixHead=Read_matzeros(commad_file,result_file,MatrixHead);
    	}else if (strcmp(word,"vecread")==0)
    	{
    		VectorHead=Read_vecread(commad_file,result_file,argv[1],VectorHead);
    	}else if (strcmp(word,"matread")==0)
    	{

    		MatrixHead=Read_matread(commad_file,result_file,argv[1],MatrixHead);
    	}else if (strcmp(word,"vecstack")==0)
    	{
    		MatrixHead=Read_vecstack(commad_file,result_file,VectorHead,MatrixHead);
    	}else if (strcmp(word,"matstack")==0)
    	{
    		MatrixHead=Read_matstack(commad_file,result_file,MatrixHead);
    	}else if (strcmp(word,"mvstack")==0)
    	{
    		MatrixHead=Read_mvstack(commad_file,result_file,VectorHead,MatrixHead);
    	}else if (strcmp(word,"pad")==0)
    	{
    		MatrixHead=Read_pad(commad_file,result_file,MatrixHead);
    	}else if (strcmp(word,"padval")==0)
    	{
    		MatrixHead=Read_padval(commad_file,result_file,MatrixHead);
    	}else if (strcmp(word,"vecslice")==0)
    	{
    		VectorHead=Read_vecslice(commad_file,result_file,VectorHead);
    	}else if (strcmp(word,"matslicecol")==0 ||  strcmp(word,"matslicerow")==0)
    	{
    		VectorHead=Read_matsliceRowandCol(commad_file,result_file,word,VectorHead,MatrixHead);
    	}else if (strcmp(word,"matslice")==0)
    	{
    		MatrixHead=Read_matslice(commad_file,result_file,MatrixHead);
    	}else if (strcmp(word,"add")==0  ||  strcmp(word,"multiply")==0 || strcmp(word,"subtract")==0 )
    	{
    		Math(commad_file,result_file,word,MatrixHead);
    	}else{

    		char *alldata;
    		alldata=AllocateString(alldata);
    		fscanf(commad_file, "%[^\n]\n", alldata);
    		if (strlen(alldata)>0)
    		{
    			fprintf(result_file,"error\n");
    		}
    		
    	}

    	 
    }

	fclose(result_file);
	fclose(commad_file);
	
	///////Make free Memory again
	MyVector *temp;
	MyMatrix *temp0;
	for (temp=VectorHead;temp!=NULL;temp=temp->next)
	{	

		free(temp->name);
		free(temp->data);
		free(temp);
	}
	int i,j;
	for (temp0=MatrixHead;temp0!=NULL;temp0=temp0->next)
	{
		
		free(temp0->name);
		

		for (i = 0; i < temp0->row; ++i)
		{	
			
			free(temp0->data[i]);
			
		}
		free(temp0->data);
		free(temp);
	}

	return 0;
}
///Add vector to collection
MyVector *  AddVectorCollection(MyVector *vector,MyVector *VectorHead){
	vector->next=NULL;
	if (VectorHead==NULL)
	{
		VectorHead=vector;
		
	}else{
		MyVector *temp=VectorHead;
		while(temp->next!=NULL){
			temp=temp->next;
		}
		
		temp->next=vector;
	}

	return VectorHead;

}
///Add matrix to collection
MyMatrix * AddMatrixCollection(MyMatrix *matrix,MyMatrix *MatrixHead){
	matrix->next=NULL;
	if (MatrixHead==NULL)
    {
    	MatrixHead=matrix;
    	
    }else{
    	MyMatrix *temp=MatrixHead;
		while(temp->next!=NULL){
			temp=temp->next;
		}
		
		temp->next=matrix;
    }

    return MatrixHead;
}
///Create init vector 
MyVector * CreateVector(MyVector *vector,MyVector *VectorHead){

	int i;
	vector->data= (int*)malloc(vector->length * sizeof(int));
	for (i = 0; i < vector->length; ++i)
	{
		vector->data[i]=0;
	}
	
	return AddVectorCollection(vector,VectorHead);

}
///print vector data to file
void PrintVector(MyVector *vector,FILE *result_file){

	int i;
	
	for (i = 0; i < vector->length; ++i)
	{
		fprintf(result_file,"%d ", vector->data[i]);
	}
	fprintf(result_file,"\n");
}

///create init matrix
MyMatrix * CreateMatrix(MyMatrix *matrix,MyMatrix *MatrixHead){

	int i,j;

	matrix->data = (int **)malloc(sizeof(int *)*matrix->row); 
    for (i=0; i<matrix->row; ++i){
    	
    	*(matrix->data+i) = (int *)malloc(sizeof(int)*matrix->col);  
    }

    
    for (i = 0; i <matrix->row; ++i)
    {
    	for (j = 0; j < matrix->col; ++j)
    		{
    			matrix->data[i][j]=0;
    		}	
    }

    return AddMatrixCollection(matrix,MatrixHead);

}
///print matrix data to result file
void PrintMAtrix(MyMatrix *matrix,FILE *result_file){

	int i,j;
	for (i = 0; i < matrix->row; ++i)
	{
		for (j = 0; j < matrix->col; ++j)
		{
				fprintf(result_file,"%d ", matrix->data[i][j]);
		}
		fprintf(result_file,"\n");	
	}
	
}
///Read file then create vector froö ımformatiom comıng fron file
MyVector * CreateVector_fromFile(MyVector *vector ,char alldata[],MyVector *VectorHead){



	char *clonedata;
	clonedata=AllocateString(clonedata);
	strcpy(clonedata ,alldata);
	
	int length=0,i=0;

	char *token = strtok(alldata, " "); 
    
    while (token != NULL) 
    { 
       
        token = strtok(NULL, " ");
        length++; 
    }
    vector->length=length;

    VectorHead=CreateVector(vector,VectorHead);
    
    token = strtok(clonedata, " ");
   
    while (token != NULL) 
    { 
    	vector->data[i++]=atoi(token);
      	token = strtok(NULL, " ");
       
    }

    token = strtok(vector->name, ".");

    strcpy(vector->name,token);

    return VectorHead;
}
///creata matrix about ınformation from file
MyMatrix * CreateMatrix_fromFile(MyMatrix *matrix,FILE *file,char folderpath[],MyMatrix *MatrixHead){

	char *alldata;

	alldata=AllocateString(alldata);
	int row=0,col=0;
	while(fscanf(file, "%[^\n]\n", alldata)!=EOF){

		char *token = strtok(alldata, " "); 
    	row++;
    	col=0;
   		while (token != NULL) 
    	{ 	
    		
       		col++;
        	token = strtok(NULL, " ");
        	 
    	}
		
	}
	matrix->row=row;
	matrix->col=col;
	MatrixHead=CreateMatrix(matrix,MatrixHead);

	fclose(file);
	file=fopen(folderpath,"r");
	row=0;col=0;
	char *token ;
	while(fscanf(file, "%[^\n]\n", alldata)!=EOF){

		token = strtok(alldata, " "); 
    	
    	col=0;
   		while (token != NULL) 
    	{ 	
    		
       		matrix->data[row][col]=atoi(token);
        	token = strtok(NULL, " ");
        	col++;
        	 
    	}
    	row++;
		
	}

	token = strtok(matrix->name, ".");

    strcpy(matrix->name,token);
	

    return MatrixHead;
}
///Get vector froö collection by name
 MyVector  *GetVector(char *name,MyVector *VectorHead){

 	MyVector *temp;
 	for (temp=VectorHead;temp!=NULL;temp=temp->next)
 	{
 		if (strcmp(temp->name,name)==0)
 		{
 			return temp;
 			
 		}
 	}

 	return NULL;
 }
 ///get matrix froö collection by name
MyMatrix  *GetMatrix(char  *name,MyMatrix *MatrixHead){

	MyMatrix *temp;
	for (temp=MatrixHead;temp!=NULL;temp=temp->next)
 	{
 		if (strcmp(temp->name,name)==0)
 		{
 			return temp;
 			
 		}
 	}
 	return NULL;
}
///reallocate memory for matrix
void AllocateNewMemoryforMatrix(MyMatrix *matrix,int row,int col){

		int i,j,k=1;

		if (row!=matrix->row)
		{
			matrix->data = (int **)realloc(matrix->data,sizeof(int**)*row); 
		}
		
		
		for ( i = 0; i < row ; ++i)
		{		
			if (i<matrix->row)
			{
				*(matrix->data+i) = (int *)realloc(*(matrix->data+i), sizeof(int)*col);

			}else{
				*(matrix->data+i) = (int *)malloc(sizeof(int)*col);  
			}
		}

}
///Get number maximun or minimun from row
int NumberInRow(int index,MyMatrix *matrix,char mode[]){

	int i;
	int value;
	if (strcmp(mode,"maximum")==0)
	{
		value=-1000;
		for ( i = 0; i < matrix->col; ++i)
		{
			if (value<matrix->data[index][i])
			{
				value=matrix->data[index][i];
			}
		}
		return value;
	}else{
	
		value=1000;
		for ( i = 0; i < matrix->col; ++i)
		{
			if (value>matrix->data[index][i])
			{
				value=matrix->data[index][i];
			}
		}
		return value;
	}
	

	return 0;

}
//Allocate Memory for char
char *AllocateString(char *string){


		string=(char*)malloc(100*sizeof(char));

		return string;
}
///get maximun or minimun number from column
int NumberInCol(int index,MyMatrix *matrix,char mode[]){

	int i;
	int value;
	if (strcmp(mode,"maximum")==0)
	{
		value=-1000;
		for ( i = 0; i < matrix->row; ++i)
		{
			if (value<matrix->data[i][index])
			{
				value=matrix->data[i][index];
			}
		}
		return value;
	}else{
	
		value=1000;
		for ( i = 0; i < matrix->row; ++i)
		{
			if (value>matrix->data[i][index])
			{
				value=matrix->data[i][index];
			}
		}
		return value;
	}
	

	return 0;

}
///read vector information
MyVector * Read_veczeros(FILE *commad_file,FILE *result_file,MyVector *VectorHead){


	MyVector *vector=(MyVector*)malloc(sizeof(MyVector));
	vector->name=AllocateString(vector->name);

	fscanf(commad_file,"%s",vector->name);
	fscanf(commad_file,"%d",&vector->length);

	
	VectorHead= CreateVector(vector,VectorHead);

	fprintf(result_file,"created vector %s %d\n",vector->name,vector->length);
	PrintVector(vector,result_file);

	return VectorHead;
	
}
///read matrix data
MyMatrix * Read_matzeros(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead){





	MyMatrix *matrix=(MyMatrix*)malloc(sizeof(MyMatrix));
	matrix->name=AllocateString(matrix->name);
	fscanf(commad_file,"%s",matrix->name);
	fscanf(commad_file,"%d",&matrix->row);
	fscanf(commad_file,"%d",&matrix->col);

	MatrixHead=CreateMatrix(matrix,MatrixHead);

	fprintf(result_file,"created matrix %s %d %d\n",matrix->name,matrix->row,matrix->col );

	PrintMAtrix(matrix,result_file);

	return MatrixHead;
}
///read vector data
MyVector * Read_vecread(FILE *commad_file,FILE *result_file,const char folder[],MyVector *VectorHead){


	MyVector *vector=(MyVector*)malloc(sizeof(MyVector));
	vector->name=AllocateString(vector->name);
	char *alldata;
	alldata=AllocateString(alldata);
	char *foldername;
	foldername=AllocateString(foldername);
	char *folderpath;
	folderpath=AllocateString(folderpath);
	fscanf(commad_file,"%s",foldername);
	
	
	strcpy(folderpath,folder);
	strcat(folderpath,"/");
	strcat(folderpath,foldername);

	strcpy(vector->name,foldername);
	FILE *file=fopen(folderpath,"r");
	
	if (file==NULL)
	{

		fprintf(result_file,"error\n");
		return  VectorHead;
	}

	fscanf(file,"%[^\n]s",alldata);

	VectorHead= CreateVector_fromFile(vector,alldata,VectorHead);

	fprintf(result_file,"read vector %s %d\n",foldername,vector->length );
	
	PrintVector(vector,result_file);

	free(alldata);
	free(foldername);
	free(folderpath);

	return VectorHead;
}
//read matrix data
MyMatrix  *Read_matread(FILE *commad_file,FILE *result_file,const char folder[],MyMatrix *MatrixHead){


	MyMatrix *matrix=(MyMatrix*)malloc(sizeof(MyMatrix));
	matrix->name=AllocateString(matrix->name);
	
	char *foldername;
	foldername=AllocateString(foldername);
	char *folderpath;
	folderpath=AllocateString(folderpath);
	fscanf(commad_file,"%s",foldername);
	
	
	strcpy(folderpath,folder);
	strcat(folderpath,"/");
	strcat(folderpath,foldername);

	strcpy(matrix->name,foldername);
	FILE *file=fopen(folderpath,"r");
	
	if (file==NULL)
	{

		fprintf(result_file,"error\n");
		return  MatrixHead;
	}

	MatrixHead= CreateMatrix_fromFile(matrix,file,folderpath,MatrixHead);

	fprintf(result_file,"read matrix %s %d %d\n",foldername,matrix->row,matrix->col );

	PrintMAtrix(matrix,result_file);

	
	free(foldername);
	free(folderpath);

	return MatrixHead;
}
///connect to vector then export as matrix
MyMatrix * Read_vecstack(FILE *commad_file,FILE *result_file,MyVector *VectorHead,MyMatrix *MatrixHead){


	char *name1;
	name1=AllocateString(name1);
	char *name2;
	name2=AllocateString(name2);
	char *direction;
	direction=AllocateString(direction);
	char *matrixname;
	matrixname=AllocateString(matrixname);
	fscanf(commad_file,"%s",name1);
	fscanf(commad_file,"%s",name2);
	fscanf(commad_file,"%s",direction);
	fscanf(commad_file,"%s",matrixname);
	
	
	MyVector *vector1=GetVector(name1,VectorHead);
	MyVector *vector2=GetVector(name2,VectorHead);

	if (vector1==NULL ||  vector2==NULL)
	{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}

	int i;
	MyMatrix *matrix;
	if (strcmp(direction,"row")==0)
	{
		int col=vector1->length;
		if (col>vector2->length)
		{
			col=vector2->length;
		}
		matrix=(MyMatrix*)malloc(sizeof(MyMatrix));
		matrix->name=AllocateString(matrix->name);
		strcpy(matrix->name,matrixname);
		matrix->row=2;
		matrix->col=col;

		MatrixHead=CreateMatrix(matrix,MatrixHead);

		for ( i = 0; i < matrix->col; ++i)
		{
			
			matrix->data[0][i]=vector1->data[i];
			matrix->data[1][i]=vector2->data[i];
			
		}


	}else if (strcmp(direction,"column")==0)
	{
		int row=vector1->length;
		if (row>vector2->length)
		{
			row=vector2->length;
		}
		matrix=(MyMatrix*)malloc(sizeof(MyMatrix));
		matrix->name=AllocateString(matrix->name);
		strcpy(matrix->name,matrixname);
		matrix->row=row;
		matrix->col=2;

		MatrixHead=CreateMatrix(matrix,MatrixHead);

		for ( i = 0; i < matrix->row; ++i)
		{
			matrix->data[i][0]=vector1->data[i];
			matrix->data[i][1]=vector2->data[i];
			
		}
	}else{
		fprintf(result_file,"error\n");
		return MatrixHead;
	}

	fprintf(result_file,"vectors concatenated %s %d %d\n",matrix->name,matrix->row,matrix->col );

	PrintMAtrix(matrix,result_file);

	free(name1);
	free(name2);
	free(direction);
	free(matrixname);


	return MatrixHead;


}
///connect matrix overwrite first matrix
MyMatrix * Read_matstack(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead){

	char *name1;
	name1=AllocateString(name1);
	char *name2;
	name2=AllocateString(name2);
	char *where;
	where=AllocateString(where);

	fscanf(commad_file,"%s",name1);
	fscanf(commad_file,"%s",name2);
	fscanf(commad_file,"%s",where);

	MyMatrix *matrix1=GetMatrix(name1,MatrixHead);
	MyMatrix *matrix2=GetMatrix(name2,MatrixHead);

	if (matrix1==NULL ||  matrix2==NULL   )
	{	
			fprintf(result_file,"error\n");
			return  MatrixHead;
	}

	int row,col,i,j;
	if (strcmp(where,"d")==0  &&  matrix1->col==matrix2->col)
	{
		row=matrix1->row+matrix2->row;
		col=matrix2->col;
		if (col>matrix1->col)
		{
			col=matrix1->col;
		}
		AllocateNewMemoryforMatrix(matrix1,row,col);

		for (i =matrix1->row ; i <row; ++i)
		{
			for (j =0 ; j <col; ++j)
			{
				
				matrix1->data[i][j]=matrix2->data[i-matrix1->row][j];
			}
		}
		matrix1->row=row;
		matrix1->col=col;
		

		

	}else  if(strcmp(where,"r")==0 &&  matrix1->row==matrix2->row)
	{
		col=matrix1->col+matrix2->col;
		row=matrix2->row;
		if (row>matrix1->row)
		{
			row=matrix1->row;
		}
		AllocateNewMemoryforMatrix(matrix1,row,col);

		for (i =0; i <row; ++i)
		{
			for (j =matrix1->col ; j <col; ++j)
			{
				
				matrix1->data[i][j]=matrix2->data[i][j-matrix1->col];
			}
		}
		matrix1->row=row;
		matrix1->col=col;
		
	}else{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}

	
	fprintf(result_file,"matrices concatenated %s %d %d\n",matrix1->name,matrix1->row,matrix1->col);

	PrintMAtrix(matrix1,result_file);

	free(name1);
	free(name2);
	free(where);

	return MatrixHead;
}
///connet matrix and vector
MyMatrix * Read_mvstack(FILE *commad_file,FILE *result_file,MyVector *VectorHead,MyMatrix *MatrixHead){

	char *name1;
	name1=AllocateString(name1);
	char *name2;
	name2=AllocateString(name2);
	char *where;
	where=AllocateString(where);

	fscanf(commad_file,"%s",name1);
	fscanf(commad_file,"%s",name2);
	fscanf(commad_file,"%s",where);

	MyMatrix *matrix=GetMatrix(name1,MatrixHead);

	MyVector *vector=GetVector(name2,VectorHead);

	if (matrix==NULL ||  vector==NULL)
	{	
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}


	int row,col,i;
	if (strcmp(where,"r")==0)
	{
		col=matrix->col+1;
		row=matrix->row;
		if (vector->length<matrix->row)
		{
			fprintf(result_file,"error\n");
			return  MatrixHead;
		}
		AllocateNewMemoryforMatrix(matrix,row,col);

		for (i = 0; i < row; ++i)
		{
			matrix->data[i][matrix->col]=vector->data[i];
		}

	}else if (strcmp(where,"d")==0)
	{
		
		col=matrix->col;
		row=matrix->row+1;
		if (vector->length<matrix->col)
		{
			fprintf(result_file,"error\n");
			return  MatrixHead;
		}
		AllocateNewMemoryforMatrix(matrix,row,col);
		for (i = 0; i < col; ++i)
		{
			matrix->data[matrix->row][i]=vector->data[i];
		}

	}else{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}


	matrix->row=row;
	matrix->col=col;
	fprintf(result_file,"matrix and vector concatenated %s %d %d\n",matrix->name,matrix->row,matrix->col);

	PrintMAtrix(matrix,result_file);

	free(name1);
	free(name2);
	free(where);

	return MatrixHead;
}
//pad matrix 
MyMatrix * Read_pad(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead){

	char *name;
	name=AllocateString(name);
	int row,col;
	char *mode;
	mode=AllocateString(mode);

	fscanf(commad_file,"%s",name);
	fscanf(commad_file,"%d %d",&row,&col);
	fscanf(commad_file,"%s",mode);

	MyMatrix *matrix=GetMatrix(name,MatrixHead);

	if (matrix==NULL)
	{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}
	row=row+matrix->row;
	col=col+matrix->col;
	AllocateNewMemoryforMatrix(matrix,row,col);

	int i,j;
	int max=-100;
	for ( i =0; i < row; ++i)
	{	
		for ( j =matrix->col; j < col; ++j)
		{	

			matrix->data[i][j]=NumberInRow(i,matrix,mode);
		}
	}
	for ( i =matrix->row; i < row; ++i)
	{	
		for ( j =0; j < col; ++j)
		{	

			matrix->data[i][j]=NumberInCol(j,matrix,mode);
		}
	}

	matrix->row=row;
	matrix->col=col;
	fprintf(result_file,"matrix paded %s %d %d\n",matrix->name,matrix->row,matrix->col);

	PrintMAtrix(matrix,result_file);

	free(name);
	free(mode);

	return MatrixHead;

}
//pad matrix then put value in this new place
MyMatrix * Read_padval(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead){

	char *name;

	name=AllocateString(name);
	int row,col,value;
	

	fscanf(commad_file,"%s",name);
	fscanf(commad_file,"%d %d %d",&row,&col,&value);
	

	MyMatrix *matrix=GetMatrix(name,MatrixHead);

	if (matrix==NULL)
	{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}

	row=row+matrix->row;
	col=col+matrix->col;

	AllocateNewMemoryforMatrix(matrix,row,col);

	int i,j;
	for (i = matrix->row; i < row; ++i)
	{	
		for (j = 0; j < col; ++j)
		{
			matrix->data[i][j]=value;
		}
			
	}
	for (i = 0; i < row; ++i)
	{	
		for (j = matrix->col; j < col; ++j)
		{
			matrix->data[i][j]=value;
		}
			
	}

	matrix->row=row;
	matrix->col=col;
	fprintf(result_file,"matrix paded %s %d %d\n",matrix->name,matrix->row,matrix->col);

	PrintMAtrix(matrix,result_file);

	free(name);

	return MatrixHead;
}
///export vector from vector
 MyVector *  Read_vecslice(FILE *commad_file,FILE *result_file,MyVector *VectorHead){

	char *name;
	name=AllocateString(name);
	char  *newobjectname;
	newobjectname=AllocateString(newobjectname);
	int start,stop;

	fscanf(commad_file,"%s %d %d %s",name,&start,&stop,newobjectname);

	MyVector *vector=GetVector(name,VectorHead);

	if (vector==NULL)
	{
		fprintf(result_file,"error\n");
		return  VectorHead;
	}

	MyVector *newvector=(MyVector*)malloc(sizeof(MyVector));
	newvector->name=AllocateString(newvector->name);
	strcpy(newvector->name,newobjectname);

	if (start>=stop  ||  start<0 ||  stop<0)
	{
		fprintf(result_file,"error\n");
		return  VectorHead;
	}
	if (stop>vector->length)
	{
		stop=vector->length;
	}
	newvector->length=stop-start;
	VectorHead= CreateVector(newvector,VectorHead);

	int i;
	for ( i = 0; i < newvector->length; ++i)
	{
		newvector->data[i]=vector->data[i+start];
	}

	fprintf(result_file,"vector sliced %s %d\n",newvector->name,newvector->length);

	PrintVector(newvector,result_file);

	free(name);
	free(newobjectname);

	return VectorHead;
	
}
///export vector from matrix row and column
MyVector * Read_matsliceRowandCol(FILE *commad_file,FILE *result_file,char word[],MyVector *VectorHead,MyMatrix *MatrixHead){

	char *name;
	name=AllocateString(name);
	char  *newobjectname;
	newobjectname=AllocateString(newobjectname);
	int index,start,stop;

	fscanf(commad_file,"%s %d %d %d %s",name,&index,&start,&stop,newobjectname);


	MyMatrix *matrix=GetMatrix(name,MatrixHead);

	if (matrix==NULL)
	{
		fprintf(result_file,"error\n");
		return  VectorHead;
	}

	MyVector *newvector=(MyVector*)malloc(sizeof(MyVector));
	newvector->name=AllocateString(newvector->name);
	strcpy(newvector->name,newobjectname);
	if (start>=stop  ||  start<0 ||  stop<0)
	{
		fprintf(result_file,"error\n");
		return  VectorHead;
	}
	if (strcmp(word,"matslicecol")==0)
	{
		if (stop>matrix->row)
		{
			stop=matrix->row;
		}
		
	}else{
		if (stop>matrix->col)
		{
			stop=matrix->col;
		}
	}
	newvector->length=stop-start;
	VectorHead= CreateVector(newvector,VectorHead);
	int i;
	for ( i = 0; i < newvector->length; ++i)
	{
		
		if (strcmp(word,"matslicecol")==0)
		{
			newvector->data[i]=matrix->data[i+start][index];
		}else{
			newvector->data[i]=matrix->data[index][i+start];
		}
	}
	fprintf(result_file,"vector sliced %s %d\n",newvector->name,newvector->length);

	PrintVector(newvector,result_file);

	free(name);
	free(newobjectname);

	return VectorHead;

}
//export matrix from matrix
MyMatrix * Read_matslice(FILE *commad_file,FILE *result_file,MyMatrix *MatrixHead){

	char *name;
	name=AllocateString(name);
	char  *newobjectname;
	newobjectname=AllocateString(newobjectname);
	int start_x,stop_x,start_y,stop_y;

	fscanf(commad_file,"%s %d %d %d %d %s",name,&start_x,&stop_x,&start_y,&stop_y,newobjectname);

	MyMatrix *matrix=GetMatrix(name,MatrixHead);

	if (matrix==NULL  ||  stop_x<start_x ||  stop_y<start_y ||  start_y<0 ||  start_x<0 ||  stop_x<0 ||  stop_y<0)
	{
		fprintf(result_file,"error\n");
		return  MatrixHead;
	}

	
	MyMatrix *newmatrix=(MyMatrix*)malloc(sizeof(MyMatrix));
	newmatrix->name=AllocateString(newmatrix->name);
	strcpy(newmatrix->name,newobjectname);
	newmatrix->col=stop_x-start_x;
	newmatrix->row=stop_y-start_y;
	MatrixHead= CreateMatrix(newmatrix,MatrixHead);

	int i,j;
	for (i = 0; i < newmatrix->row; ++i)
	{
		for (j = 0; j < newmatrix->col; ++j)
		{
			newmatrix->data[i][j]=matrix->data[i+start_y][j+start_x];
		}
	}

	fprintf(result_file,"matrix sliced %s %d %d\n",newmatrix->name,newmatrix->row,newmatrix->col);

	PrintMAtrix(newmatrix,result_file);

	free(name);
	free(newobjectname);

	return MatrixHead;

}

///math with matrices
void Math(FILE *commad_file,FILE *result_file,char word[],MyMatrix *MatrixHead){

	char *name1;
	name1=AllocateString(name1);
	char *name2;
	name2=AllocateString(name2);
	

	fscanf(commad_file,"%s %s",name1,name2);

	MyMatrix *matrix1=GetMatrix(name1,MatrixHead);
	MyMatrix *matrix2=GetMatrix(name2,MatrixHead);

	if (matrix1==NULL ||  matrix2==NULL  || matrix1->row!=matrix2->row  ||  matrix1->col!=matrix2->col)
	{
		fprintf(result_file,"error\n");
		return;
	}

	int i,j;
	
	for ( i = 0; i < matrix1->row; ++i)
	{
		for (j = 0; j < matrix1->col; ++j){

			if (strcmp(word,"add")==0)
			{
				matrix1->data[i][j]+=matrix2->data[i][j];
			}else if (strcmp(word,"subtract")==0)
			{
				matrix1->data[i][j]-=matrix2->data[i][j];
			}if (strcmp(word,"multiply")==0)
			{
				matrix1->data[i][j]*=matrix2->data[i][j];
			}
		
			
		}
	}
	
	fprintf(result_file,"%s %s %s\n",word,matrix1->name,matrix2->name);

	PrintMAtrix(matrix1,result_file);

	free(name1);
	free(name2);

}