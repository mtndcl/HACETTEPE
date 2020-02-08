#define MAX 15
#define STRING_MAX 10
#define NUMBER_OF_OPS

#define MAX_EXPR 10000
#define MAX_COND 5000

#define SET_MAX 1

#define MAX_VAR 100


typedef struct node1 {
	int type;
	char val[MAX];
} node1_t ;

typedef struct node2 {
	int type;
	char val[MAX];
	void *child1;	
} node2_t ;

typedef struct node3 {
	int type;
	char val[MAX];
	void *child1;
	void *child2;
} node3_t ;

typedef struct node4 {
	int type;
	char val[MAX];
	void *child1;
	void *child2;
	void *child3;
} node4_t ;

//char pre_ops[NUMBER_OF_OPS][STRING_MAX] = {"sin", "cos", "ceil", "floor", "sqrt", "abs"};
//char set_ops[2][3] = {"&&", "||"};
//char ops[4][2] = {"+", "-", "/", "*"};
//char rel_ops[6][3] = {"<", ">", "==", "!=", "<=", ">="};
//char vals[2][2] = {"1", "0"};

void read_from_file(char *filePath, char **target);
void initialize_variables();

char *pre_ops[MAX_VAR];
char *set_ops[MAX_VAR];
char *ops[MAX_VAR];
char *rel_ops[MAX_VAR];
char *vals[MAX_VAR];


int generate_random(int max);


void select_cond_child(node4_t* node);

void select_set_op(node2_t *node);
void select_rel_op(node2_t *node);
void select_op(node2_t *node);

void select_expr_child_1(node2_t * node);
void select_expr_child_2(node3_t * node);
void select_expr_child_3(node4_t * node);

node2_t* select_expr_type1();
node3_t* select_expr_type2();
node4_t* select_expr_type3();

void printTreeElements(void *node);
void printTree(void *node);

