HEADERS = assignment.h
OBJECTS = assignment.o

default: assignment

%.o: %.c $(HEADERS)
	gcc -c $< -o $@

assignment: $(OBJECTS)
	gcc $(OBJECTS) -o $@

clean:
	-rm -f $(OBJECTS)
	-rm -f assignment
