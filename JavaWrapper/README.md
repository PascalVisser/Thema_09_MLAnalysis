# JavaWrapper stroke prediction

This command line application is designed to classifier unknown instances and predict if a given person has a higher chance of stroke events or not.

------------------------------------------------------------------------


## Arguments

Short name | Long name | Description |  Example
--- | --- | --- | --- |
-i | -infile | Parse instances from arff file and classify them | -i./testdata/testdata.arff
-o | -output | Writes the result to csv, arff file or prints to command line depending on the wishes of the user | -o./testdata/"outputname.extension"


## Example usage

Required version Java 16+ SDK.

```
java -jar src\main\java\WekaBaggingRunner.java -i./testdata/testdata.arff -o./testdata/"outputname.extension"
```

If help is required, run the program without argument. then a help menu will be printed.


## File information

The folder src/main/java/nl/bioinf contains the java files CLhandler.java and WekaBaggingRunner.java

- CLhandler.java is the argument parser java file, not executable
- WekaBaggingRunner.java is the classifying java file, executable.

The folder scr/main/resources contains the model file. This is the file that the WekaBaggingRunner uses as input for a model. 

The folder testdata has a file with unknown instances. The WEkaBaggingRunner file can uses this file as input. 

build.gradle contains dependencies and pointers to the main class. 

## Contact

p.visser@st.hanze.nl
