#include <stdlib.h>
#include <stdio.h>
#include <time.h> 
#include <string.h>


#include "assignment.h"


#define OP_FILE "op"
#define SET_OP_FILE "set_op"
#define REL_OP_FILE "rel_op"
#define PRE_OP "pre_op"
#define VAR_FILE "var"


#define file_open(arg) \
	fopen(arg, "r")

int expr_counter = 0;
int cond_counter = 0;


void read_from_file(char *filePath, char **target){

	FILE *inp = file_open(filePath);

	int i = 0;

	char str[25];

	while(i < MAX_VAR && fscanf(inp, "%s", str) != EOF){
		
		target[i] = (char *)malloc(strlen(str) * sizeof(char));
		strcpy(target[i], str);
		i++;
	}

}

void initialize_variables(){

	read_from_file(OP_FILE, ops);
	read_from_file(SET_OP_FILE, set_ops);
	read_from_file(REL_OP_FILE, rel_ops);
	read_from_file(PRE_OP, pre_ops);
	read_from_file(VAR_FILE, vals);
}

int generate_random(int max) {
	return (rand() % (max - 0 + 1)) + 0;
}


node2_t* select_expr_type1() { // One child

	node2_t *node = (node2_t *)malloc(sizeof(node2_t));
	node->type = 1;
	strcpy(node->val, "<expr>");

	return node;

}
node3_t* select_expr_type2() { // Two child

	node3_t *node = (node3_t *)malloc(sizeof(node3_t));
	node->type = 2;
	strcpy(node->val, "<expr>");

	return node;
}
node4_t *select_expr_type3() { // Three child

	node4_t *node = (node4_t *)malloc(sizeof(node4_t));
	node->type = 3;
	strcpy(node->val, "<expr>");

	return node;
}


void select_cond_child(node4_t* node){


	strcpy(node->val, "<cond>");
	node->child2 = (node2_t *)malloc(sizeof(node2_t));

	int r; 

	if (SET_MAX)
		if(cond_counter > MAX_COND)
			r = 0;
		else
			r = generate_random(1);
	else
		r = generate_random(1);
	
	if(r){ // <cond><set-op><cond>

		cond_counter++;

		node->child1 = (node4_t *)malloc(sizeof(node4_t));
		select_cond_child(node->child1);

		node->child3 = (node4_t *)malloc(sizeof(node4_t));
		select_cond_child(node->child3);

		select_set_op(node->child2); // SET-OP

	} else { // <expr><rel-op><expr>

		select_rel_op(node->child2); // REL-OP

		int res;

		if (SET_MAX)
			if(expr_counter > MAX_EXPR)
				res = 0;
			else
				res = generate_random(2);
		else
			res = generate_random(2);


		if(res == 2){
			expr_counter++;

			node->child1 = select_expr_type3();
			select_expr_child_3(node->child1);
		}
		else if(res == 1){
			expr_counter++;
			node->child1 = select_expr_type2();
			select_expr_child_2(node->child1);
		}
		else{
			node->child1 = select_expr_type1();
			select_expr_child_1(node->child1);
		}

		if (SET_MAX)
			if(expr_counter > MAX_EXPR)
				res = 0;
			else
				res = generate_random(2);
		else
			res = generate_random(2);

		if(res == 2){

			expr_counter++;
			node->child3 = select_expr_type3();
			select_expr_child_3(node->child3);
		}
		else if(res == 1){
			expr_counter++;
			node->child3 = select_expr_type2();
			select_expr_child_2(node->child3);
		}
		else{
			node->child3 = select_expr_type1();
			select_expr_child_1(node->child3);
		}

		
	}
}

void select_expr_child_1(node2_t *node) { // <var>


	node->child1 = (node2_t *)malloc(sizeof(node2_t));
	strcpy(((node2_t *)node->child1)->val, "<var>");

	((node2_t *)node->child1)->child1 = (node1_t *)malloc(sizeof(node1_t));

	strcpy(((node1_t *)((node2_t *)node->child1)->child1)->val, &vals[generate_random(1)][0]);

}

void select_expr_child_2(node3_t *node) { // <pre-op>(<expr>)


	node->child1 = (node2_t *)malloc(sizeof(node2_t));
	strcpy(((node2_t *)node->child1)->val, "<pre-op>");

	((node2_t *)node->child1)->child1 = (node1_t *)malloc(sizeof(node1_t));

	strcpy(((node1_t *)((node2_t *)node->child1)->child1)->val, &pre_ops[generate_random(5)][0]);

	int res;

	if (SET_MAX)
		if(expr_counter > MAX_EXPR)
			res = 0;
		else
			res = generate_random(2);
	else
		res = generate_random(2);

	if(res == 2){
		
		expr_counter++;
		node->child2 = select_expr_type3();
		select_expr_child_3(node->child2);
	}
	else if(res == 1){
		expr_counter++;
		node->child2 = select_expr_type2();
		select_expr_child_2(node->child2);
	}
	else{
		node->child2 = select_expr_type1();
		select_expr_child_1(node->child2);
	}

}

void select_expr_child_3(node4_t *node) { // (<expr><op><expr>)

	int res;


	if (SET_MAX)
		if(expr_counter > MAX_EXPR)
			res = 0;
		else
			res = generate_random(2);
	else
		res = generate_random(2);

	if(res == 2){
		node->child1 = select_expr_type3();
		select_expr_child_3(node->child1);
	}
	else if(res){
		node->child1 = select_expr_type2();
		select_expr_child_2(node->child1);
	}
	else{
		node->child1 = select_expr_type1();
		select_expr_child_1(node->child1);
	}

		
	if (SET_MAX)
		if(expr_counter > MAX_EXPR)
			res = 0;
		else
			res = generate_random(2);
	else
		res = generate_random(2);

	if(res == 2){
		node->child3 = select_expr_type3();
		select_expr_child_3(node->child3);
	}
	else if(res){
		node->child3 = select_expr_type2();
		select_expr_child_2(node->child3);
	}
	else{
		node->child3 = select_expr_type1();
		select_expr_child_1(node->child3);
	}


	node->child2 = (node2_t *)malloc(sizeof(node2_t));
	select_op((node2_t*)node->child2);
}



void select_set_op(node2_t *node){
	strcpy(node->val, "<set-op>");
	node->child1 = (node1_t *)malloc(sizeof(node1_t));
	strcpy(((node1_t *)node->child1)->val, &set_ops[generate_random(1)][0]);
}


void select_rel_op(node2_t *node){
	strcpy(node->val, "<rel-op>");
	node->child1 = (node1_t *)malloc(sizeof(node1_t));
	strcpy(((node1_t *)node->child1)->val, &rel_ops[generate_random(5)][0]);
}

void select_op(node2_t *node){
	strcpy(node->val, "<op>");
	node->child1 = (node1_t *)malloc(sizeof(node1_t));
	strcpy(((node1_t *)node->child1)->val, &ops[generate_random(3)][0]);
}


void printTreeElements(void *node){

	node1_t *elem = (node1_t *)node;

	if(!strcmp(elem->val, "<alg>")){

		printf("if (");
		printTreeElements((void *)(((node2_t *)node)->child1));
		printf(") {}");

	}
	else if (!strcmp(elem->val, "<cond>")) {

		printf("(");
		printTreeElements((void *)(((node4_t *)node)->child1));

		printTreeElements((void *)(((node4_t *)node)->child2));

		printTreeElements((void *)(((node4_t *)node)->child3));
		printf(")");

	}
	else if (!strcmp(elem->val, "<expr>")){

		
		if(elem->type == 1){
			printf("(");
			printTreeElements((void *)((node2_t *)node)->child1);
			printf(")");

		}
		else if(elem->type == 2){
			printf("(");
			printTreeElements((void *)((node3_t *)node)->child1);
			printf("(");
			printTreeElements((void *)((node3_t *)node)->child2);
			printf(")");
			printf(")");
		}
		else if(elem->type == 3){
			printf("(");
			printTreeElements((void *)((node4_t *)node)->child1);

			printTreeElements((void *)((node4_t *)node)->child2);

			printTreeElements((void *)((node4_t *)node)->child3);
			printf(")");	
		} 
	}
	else {
		printf("%s",((node1_t *)((node2_t *)node)->child1)->val);

	}
}



void main(){

	srand(time(0));

	initialize_variables();

	node2_t *alg = (node2_t *)malloc(sizeof(node2_t));
	strcpy(alg->val, "<alg>");
	

	node4_t *head = (node4_t *)malloc(sizeof(node4_t));
	alg->child1 = head;

	printf("Starting...\n");

	select_cond_child(head);

	printf("Printing...\n\n");

	printTreeElements(alg);

	printf("\n\nFinished...\n");
}
