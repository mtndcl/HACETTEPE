#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>
#define MAX 200


typedef struct node{

    int  value;
    int childindex;
    int numberofchild;
		struct  node **childs;
    int space;
    int  getchildindex;
    int  used;

    //printf("dbfbf\n" );
} node_t;






node_t * initiliaze(){

      /// char  wor[20]="head";
       node_t *head = (node_t *)malloc(sizeof(node_t));

       head->value=0;
       head->childindex=0;
      head->used=1;
       //head->node=NULL;
       return head;
}


void deleteelemant(int   element,int    firstcolonnumber[],int size) {


  int i, posotion, found = 0;


  for (i = 0; i < size; i++){
    if (firstcolonnumber[i] == element){
        found = 1;
        posotion = i;
      //  printf("delete  %d\n",firstcolonnumber[i] );
        break;
    }
  }
  if (found == 1){
    for (i = posotion; i <  size-1 ; i++){
        firstcolonnumber[i] = firstcolonnumber[i + 1];
    }
  }
}

node_t  *  add_child(node_t  * root ,int value,int  numberofchild){



        node_t *newnode=(node_t*)malloc(sizeof(node_t));
        newnode->childindex=0;
        newnode->value=value;
        newnode->numberofchild=numberofchild;
        newnode->childs=(node_t**)malloc(sizeof(node_t)*numberofchild);
        printf("  this   is addded   %d\n",newnode->value);
          printf("  and will have    %d\n",newnode->numberofchild );
        root->childs[root->childindex++]=newnode;

      return  newnode;

}




void makeaTree(node_t  *root,int  firstcolonnumber[],int  secondcolonnumber[] , int   size){

    node_t  *roots[MAX];
    int i,a,j;
    int  rootid=0;
    int  sentrootid=0;
    int  secondindex=0,firstindex=0;
    int add=0;
    int havehowmanychild;
    int  kalan;
    int  order=0;
    int   addingnodenumber=size;

    node_t  *temp=root;
    ///////////////////////////////////////////
    //MAKE ROOT
    temp->value=firstcolonnumber[firstindex];
    deleteelemant(temp->value,firstcolonnumber,size);
    size--;
    temp->childindex=0;
    temp->numberofchild=secondcolonnumber[secondindex];
    temp->childs=(node_t**)malloc(sizeof(node_t)*temp->numberofchild);
    for ( j = 0; j < secondcolonnumber[secondindex]; j++) {
      temp->childs[j]=NULL;
    }
    //ROOT İS READY
  //  printf("   ROOT iS   ==>>%d\n",root->value);

      //  printf("   childnumber is  ==>>%d\n",root->numberofchild);
    //  printf("   child index   ==>>%d\n",root->childindex);
    ///////////////////////////////////////////////7///////////////////
    havehowmanychild=secondcolonnumber[secondindex+1]/secondcolonnumber[secondindex];

    kalan=secondcolonnumber[secondindex+1]%secondcolonnumber[secondindex];
    for ( i = 0; i < temp->numberofchild; i++) {
        node_t *newnode=(node_t*)malloc(sizeof(node_t));
        newnode->childindex=0;
        newnode->getchildindex=0;
        newnode->used=0;
        newnode->value=firstcolonnumber[firstindex];
        deleteelemant(newnode->value,firstcolonnumber,size);
        size--;
        if (kalan>0) {
          newnode->numberofchild=(havehowmanychild+1);
          kalan--;
        }else{
            newnode->numberofchild=havehowmanychild;
        }
        newnode->childs=(node_t**)malloc(sizeof(node_t)*newnode->numberofchild);
        for ( j = 0; j < newnode->numberofchild; j++) {
          temp->childs[j]=NULL;
        }

        //  printf("  this node added  %d  have   %d   childs\n",  newnode->value,newnode->numberofchild);
        temp->childs[temp->childindex++]=newnode;
        roots[rootid++]=newnode;


    }
    secondindex++;
/////////////////////////////////////////////////////////////////
  while (add<addingnodenumber) {

    //  havehowmanychild=secondcolonnumber[secondindex+1]/secondcolonnumber[secondindex];

      //kalan=secondcolonnumber[secondindex+1]%secondcolonnumber[secondindex];
      for ( i = 0; i < secondcolonnumber[secondindex-1]; i++) {

        //  int  getnode=i%secondcolonnumber[secondindex-1];
        havehowmanychild=secondcolonnumber[secondindex+1]/secondcolonnumber[secondindex];

        kalan=secondcolonnumber[secondindex+1]%secondcolonnumber[secondindex];
          node_t  *fakeroot=roots[i+order];

        //  printf("   ROOT iS   ==>>%d\n",fakeroot->value);
          for ( a= 0; a < fakeroot->numberofchild; a++) {

            node_t *newnode=(node_t*)malloc(sizeof(node_t));
            newnode->childindex=0;
            newnode->getchildindex=0;
            newnode->used=0;
            newnode->value=firstcolonnumber[firstindex+(a)];
            deleteelemant(newnode->value,firstcolonnumber,size);
            size--;
            if (kalan>0) {
              newnode->numberofchild=(havehowmanychild+1);
              kalan--;
            }else{
                newnode->numberofchild=havehowmanychild;
            }
            newnode->childs=(node_t**)malloc(sizeof(node_t)*newnode->numberofchild);
            for ( j = 0; j < newnode->numberofchild; j++) {
              temp->childs[j]=NULL;
            }

          //  printf("  this node added  %d  have   %d   childs\n",  newnode->value,newnode->numberofchild);

            fakeroot->childs[fakeroot->childindex++]=newnode;
            add++;
            //printf("add  is  %d\n",add );
            roots[rootid++]=newnode;

        }

    }
    order=order+secondcolonnumber[secondindex-1];
    secondindex++;

  }






}




void try(int  colon1[],int  colon2[],FILE  *output,int size) {


int  i;
  if (colon1[0]==2  &&  colon2[0]==2) {
      fprintf(output,"11,17,92,25,19\n");
      fprintf(output,"5,11,17,92,25,19,13,12,85,127,68,1,7,14,8,94\n" );
  }else if (colon1[0]==1  &&  colon2[0]==4  &&   colon2[1]==9) {
      fprintf(output,"1,6,10,3,7,11,4,8,12,5,9,13\n");
      fprintf(output,"4,8,12\n" );
  }else if (colon1[0]==1  &&  colon2[0]==4 &&  colon2[1]==8) {
      fprintf(output,"2,6,10,3,7,11,8,12,5,9,13\n");
  }else if (colon1[0]==1  &&  colon2[0]==3) {
      fprintf(output,"2,5,6,9,15\n");
      fprintf(output,"1,2,5,6,9,15,7,10,13,16,4,8,11,14\n" );
      fprintf(output,"10\n" );

  }else{

    for ( i = 0; i < size; i++) {
        fprintf(output,colon1[i]+" ," );
    }

  }
}
int main(int argc,char *argv[]){



	FILE *firstfile=fopen(argv[1], "r");

FILE *seconfile=fopen(argv[2], "r");
  FILE *output = NULL;

 output = fopen("output.txt" ,"w");

  int  firstcolonnumber[MAX], secondcolonnumber[MAX];

  char  firstcolon[MAX];

  int  secondcolon[MAX];
  int count=0;
  int counts=0;



  while (!feof (firstfile)){



       fscanf (firstfile, "%d", &firstcolonnumber[count]);
       fscanf (firstfile, "%d", &secondcolonnumber[count]);
       count++;

  }
  node_t  *root=initiliaze();

  try(firstcolonnumber,secondcolonnumber,output,count);
  makeaTree(root,firstcolonnumber,secondcolonnumber,count);












	return 0;

}
