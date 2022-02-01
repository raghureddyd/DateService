# Instrument Expiration Date Service

__Description__

This is a program written Java. It validates whether given date or period is a working day and if not calculates the next valid business date.

__Build__

Please make sure __Java 17__ is installed.

To build the executable Jar, run the following maven command in the project root directory:
$ mvnw clean install

__Run__

To run the program, navigate to /target and issue the following command $ java -jar DateService-1.0-jar-with-dependencies.jar

__Note:__
 
No validation of inputs is done. Assuming they are valid as per requirements.

 Please follow dd.MM.yyyy format always. Ex: 01.02.2022 not 1.02.2022

 Although requirements states one holiday(01.08.2022), the application has been tested for 2022 and 2023.
