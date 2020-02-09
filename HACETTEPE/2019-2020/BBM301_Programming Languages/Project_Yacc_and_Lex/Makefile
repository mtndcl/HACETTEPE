all: compile run

prelex: project.y
	bison -y -d project.y

lex: prelex project.l
	flex project.l

yacc: lex project.y
	bison -y -v project.y

compile: prelex lex yacc
	gcc -o mam.exe lex.yy.c y.tab.c -lfl -lm

run:
	./mam.exe < test.txt