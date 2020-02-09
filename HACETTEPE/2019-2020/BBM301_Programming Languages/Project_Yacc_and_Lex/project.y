%{
#include <stdio.h>

extern char *yytext;
extern int line_count;

int yylex(void);
void yyerror(const char *);

%}



%token WHILE_LOOP FOR_LOOP CONTINUE BREAK PRINT
%token L_BRACKET R_BRACKET LC_BRACKET RC_BRACKET COMMENT
%token AND OR NOT
%token GREAT_OR_EQ LESS_OR_EQ GREATER LESS_THAN IS_EQUAL
%token TRUE FALSE
%token EQUAL_SIGN ADDITION_SIGN SUBTRACTION_SIGN MULTIPLY DIVIDE MOD
%token COMMA COLON DOT SEMI_COLON
%token L_PARANTS R_PARANTS
%token ADDITION_SIGN_EQUALS SUBTRACTION_SIGN_EQUALS MULTIPLY_EQUALS DIVIDE_EQUALS MOD_EQUALS
%token IF ELSE ELSE_IF SWITCH CASE DEFAULT
%token ARRAY
%token RETURN VARIABLE
%token NUMBER STRING
%token GRAPH CROSSROAD ROAD GPSDATA OBJECT3D
%token SEARCHLOCATION GETROADSPEED GETLOCATION SHOWTARGET SHOWONMAP
%token USER INT FLOAT STR

%start program;



%%

program
	: user_functions
	| program user_functions

	;


user_functions
	: VARIABLE L_PARANTS params R_PARANTS function_block
	
	; 



function_block
	: LC_BRACKET statements RC_BRACKET		
	;


params
	:
	| VARIABLE		
	| params COMMA VARIABLE 
        	
	;


statements
	: stmt SEMI_COLON
	| statements stmt SEMI_COLON		
	;


stmt
	: assignment_stmt
	| control_stmt
    | loop_stmt
	| function_stmt
	| return_stmt
	| print_stmt
	| array_declaration
	| variable_declaration
	| func_declarations
	| object_declarations
	;

func_declarations
	: showonMap 
	| searchLocation
	| getRoadSpeed
	| getLocation
	| showTarget
	;


variable_declaration
	: number_declaration
	| string_declaration
	;

object_declarations
	: graph_declaration
	| crossroad_declaration
	| road_declaration
	| GpsData_declaration
	| Object3D_declaration
	| user_Declaration
	;

string_declaration
	: STR VARIABLE EQUAL_SIGN STRING
	| STR VARIABLE EQUAL_SIGN VARIABLE
	| STR VARIABLE
	;


number_declaration
	: datatype VARIABLE
	| datatype VARIABLE EQUAL_SIGN NUMBER
	| datatype VARIABLE EQUAL_SIGN VARIABLE

	;

datatype
	: FLOAT|INT
	;

user_Declaration
	: USER VARIABLE EQUAL_SIGN USER L_PARANTS VARIABLE R_PARANTS
	| USER VARIABLE EQUAL_SIGN USER L_PARANTS STRING R_PARANTS
	;

searchLocation
	: SEARCHLOCATION L_PARANTS VARIABLE R_PARANTS
	| SEARCHLOCATION L_PARANTS STRING R_PARANTS
	;

getLocation
	: GETLOCATION L_PARANTS VARIABLE R_PARANTS
	| GETLOCATION L_PARANTS STRING R_PARANTS
	;

showTarget
	: SHOWTARGET L_PARANTS VARIABLE R_PARANTS
	| SHOWTARGET L_PARANTS STRING R_PARANTS
	;

getRoadSpeed
	: GETROADSPEED L_PARANTS VARIABLE R_PARANTS
	| GETROADSPEED L_PARANTS STRING R_PARANTS

showonMap
	: SHOWONMAP L_PARANTS NUMBER COMMA NUMBER R_PARANTS
	| SHOWONMAP L_PARANTS VARIABLE COMMA VARIABLE R_PARANTS
	;

Object3D_declaration
	: OBJECT3D VARIABLE EQUAL_SIGN OBJECT3D L_PARANTS NUMBER COMMA NUMBER COMMA NUMBER R_PARANTS
	| OBJECT3D VARIABLE EQUAL_SIGN OBJECT3D L_PARANTS VARIABLE COMMA VARIABLE COMMA VARIABLE R_PARANTS
	;

GpsData_declaration
	: GPSDATA VARIABLE EQUAL_SIGN GPSDATA L_PARANTS VARIABLE R_PARANTS
	| GPSDATA VARIABLE EQUAL_SIGN GPSDATA L_PARANTS STRING R_PARANTS
	;

road_declaration
	: ROAD VARIABLE EQUAL_SIGN ROAD L_PARANTS R_PARANTS

	;

graph_declaration
	: GRAPH VARIABLE EQUAL_SIGN GRAPH L_PARANTS NUMBER COMMA NUMBER R_PARANTS
	| GRAPH VARIABLE EQUAL_SIGN GRAPH L_PARANTS VARIABLE COMMA VARIABLE R_PARANTS
	;

crossroad_declaration
	: CROSSROAD VARIABLE EQUAL_SIGN CROSSROAD L_PARANTS VARIABLE R_PARANTS
	| CROSSROAD VARIABLE EQUAL_SIGN CROSSROAD L_PARANTS STRING R_PARANTS
	;

assignment_stmt
	: VARIABLE EQUAL_SIGN right_assignment 
	| VARIABLE shortcut_operations right_assignment
	| 
	;

print_stmt
	: PRINT VARIABLE

operations
	: ADDITION_SIGN
	| SUBTRACTION_SIGN
	| MULTIPLY
	| DIVIDE 
	| MOD	
	;

shortcut_operations
	: ADDITION_SIGN_EQUALS 
	| SUBTRACTION_SIGN_EQUALS 
	| MULTIPLY_EQUALS 
	| DIVIDE_EQUALS 
	| MOD_EQUALS
	;


right_assignment
	: VARIABLE
	| VARIABLE operations variable_list
	| STRING
	| NUMBER
	| STRING operations variable_list
	| NUMBER operations variable_list
	;

variable_list
	: VARIABLE
	| VARIABLE operations right_assignment
	| STRING
	| NUMBER
	| STRING operations right_assignment
	| NUMBER operations right_assignment
	;


control_stmt
	: if_stmt
	| switch_stmt
	;


if_stmt
	: IF L_PARANTS boolean_expression R_PARANTS function_block
	| if_stmt ELSE_IF L_PARANTS boolean_expression R_PARANTS function_block
	| if_stmt ELSE function_block
	;


switch_stmt
	: SWITCH L_PARANTS VARIABLE R_PARANTS function_block
	| SWITCH L_PARANTS VARIABLE R_PARANTS LC_BRACKET switch_cases RC_BRACKET
	;


switch_cases
	: CASE boolean_expression COLON statements
	| CASE boolean_expression COLON statements BREAK
	| CASE boolean_expression COLON statements switch_cases
	| CASE boolean_expression COLON statements BREAK switch_cases
	| default
	;


default
	: DEFAULT COLON statements BREAK
	| DEFAULT COLON statements 
	;


loop_stmt
	: while_loop
	| forLoop
	;

loop_block 
	: LC_BRACKET statements RC_BRACKET
	;

while_loop
	: WHILE_LOOP L_PARANTS boolean_expression R_PARANTS loop_block
	;


forLoop
	: FOR_LOOP L_PARANTS VARIABLE SEMI_COLON boolean_expression SEMI_COLON VARIABLE shortcut_operations right_assignment R_PARANTS loop_block
	;



function_stmt
	: VARIABLE L_PARANTS params_list R_PARANTS  
	;


params_list
	: empty
	| right_assignment
	| right_assignment COMMA params_list
	;


return_stmt
	: RETURN right_assignment
	;


boolean_expression
	: TRUE
	| FALSE
	| VARIABLE
	| logical_expression
	| NUMBER
	| STRING
	;


logical_expression
	: boolean_expression boolean_check boolean_expression
	| boolean_expression relational_check boolean_expression
	;


boolean_check
	: AND
	| OR
	;


relational_check
	: LESS_THAN
	| LESS_OR_EQ
	| GREATER
	| GREAT_OR_EQ
	| IS_EQUAL
	;

array_declaration
	: ARRAY VARIABLE EQUAL_SIGN ARRAY L_PARANTS R_PARANTS
	;




empty
	: 
	;

%%

void yyerror(const char *s){
	fprintf(stderr," Error Occured  <%s> in line : %d\n",s,line_count);
}
int main(){

	if( yyparse() )
		printf("\n Error is found during parsing process.\n\n");
         else
               printf("Parsing completed.SUCCESSFULLY!\n\n");
	
	
	return 0;
}
