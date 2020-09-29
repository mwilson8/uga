README -- Author: Mitch Wilson

All classes for the program "Vehicles" are stored in the package edu.uga.cs1302.Vehicles

The main method is located in VehicleDriver.java 

To COMPILE: modifiy the javac command with the "-d classes" modifier in order to make the compiler "deposit" the compiled files into the "classes" directory. You must then tell the compiler the entire path of the source files: "src/edu/uga/cs1302/Vehicles/*.java" the '*' character is a wildcard and will allow compilation of all files with the .java file-type (aka all of your source files).

To EXECUTE: if your classpath does not include the dirictory "classes", amend the classpath using either the -cp or -classpath modifier on the java command. Include the fully qualified name: "edu.uga.cs1302.Vehicles" as the argument to the java command
