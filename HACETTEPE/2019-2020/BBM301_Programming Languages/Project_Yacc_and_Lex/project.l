digit [0-9]
integer [-+]?[0-9]+
float [0-9]+"."[0-9]+
string \"(\\.|[^"\\])*\"
alphabetic [A-Za-z]
upperCase [A-Z]
alphanumeric ({alphabetic}|{digit})
variable {alphabetic}{alphanumeric}*
operations "+"|"-"|"*"|"/"|"%"
number {float}|{integer}|{digit}


%{


#include <stdio.h>
#include <string.h>
#include "y.tab.h"
int line_count=0;


%}


%%




"while" {return (WHILE_LOOP);}
"for" {return (FOR_LOOP);}
"continue" {return (CONTINUE);}
"break" {return(BREAK);}
"print" {return(PRINT);}


"if" { return(IF); }
"else" { return(ELSE); }
"else if" { return(ELSE_IF); }
"switch" { return(SWITCH); }
"case" { return(CASE); }
"default" { return(DEFAULT); }


"int" { return(INT); }
"str" { return(STR); }
"float" { return(FLOAT); }

"Array" {return(ARRAY);}


"User" {return(USER);}

"CrossRoad" {return(CROSSROAD);}
"Road" {return(ROAD);}
"GpsData" {return(GPSDATA);}
"ShowOnMap" {return(SHOWONMAP);}
"SearchLocation" {return(SEARCHLOCATION);}
"GetRoadSpeed" {return(GETROADSPEED);}
"GetLocation" {return(GETLOCATION);}
"ShowTarget" {return(SHOWTARGET);}
"Object3D" {return (OBJECT3D);}
"Graph" {return(GRAPH);}



"TRUE"          {return(TRUE);}
"FALSE"         {return(FALSE);}


"return" {return(RETURN);}



"="  { return(EQUAL_SIGN); }
"+"  { return(ADDITION_SIGN); }
"-"  { return(SUBTRACTION_SIGN); }
"*"  { return(MULTIPLY); }
"/"  { return(DIVIDE); }
"%"  { return(MOD); }

"+="  { return(ADDITION_SIGN_EQUALS); }
"-="  { return(SUBTRACTION_SIGN_EQUALS); }
"*="  { return(MULTIPLY_EQUALS); }
"/="  { return(DIVIDE_EQUALS); }
"%="  { return(MOD_EQUALS); }

   
"."	{ return(DOT); }
";"	{ return(SEMI_COLON); }
","	{ return(COMMA); }
":"	{ return(COLON); }
"("	{ return(L_PARANTS); } 
")"	{ return(R_PARANTS); }
"["	{ return(L_BRACKET); } 
"]"	{ return(R_BRACKET); }
"{"	{ return(LC_BRACKET); } 
"}"	{ return(RC_BRACKET); }





"&&" { return(AND); }
"||" { return(OR); }
"!" { return(NOT); }



"==" { return(IS_EQUAL); }
">=" { return(GREAT_OR_EQ); }
"<=" { return(LESS_OR_EQ); }
">" { return(GREATER); }
"<" { return(LESS_THAN); }




{variable} {return(VARIABLE);}
{number} {return (NUMBER);}
{string} {return (STRING);}


[\t]	    { ; }
[\r\n]	    {line_count++;}
[ \t\v\f]   { }


%%




int yywrap(){
	return 1;
}
