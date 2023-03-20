# Solutions: Distributed Systems

### Compile via Terminal:

1. Navigate into a folder with the `.java` files

```
cd SheetX/ExerciseY/
ls
Main.java  File1.java  File2.java
```

2. Compile the `.java` files using `javac`. This results in the creation of the `.class` files (Bytecode).

```
javac Main.java File1.java File2.java
ls
Main.java  File1.java  File2.java
Main.class File1.class File2.class
```

3. Run the program with `java` and the name of the file containing the `main`-method

```
java Main
```

Arguments can be added after the name of the file. Those are stored in the parameter `String[] args` of the `main`-method.

```
java Main Argument1 Argument2
```

### Using an IDE

Instead of compiling the files via the terminal, an IDE like IntelliJ can be used. This is especially recommended for the exercises with dependencies that are managed via Maven.