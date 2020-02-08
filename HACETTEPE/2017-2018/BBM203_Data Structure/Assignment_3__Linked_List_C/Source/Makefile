CC=gcc

CFLAGS=-c -Wall
all: Simon

Simon: main.c
	$(CC) main.c -lm
main.o: main.c
	$(CC) $(CFLAGS) main.c
clean:
	rm -rf *o Simon