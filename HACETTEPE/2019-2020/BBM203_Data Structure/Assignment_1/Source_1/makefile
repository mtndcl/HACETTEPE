CC=gcc
CFLAGS=-I.


%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

matrixman: matrixman.o
	$(CC) -o matrixman matrixman.o
