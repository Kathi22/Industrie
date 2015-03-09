This is the distribution of Prosys OPC UA Java SDK.

See the LICENSE document for license information.

SYSTEM REQUIREMENTS

The Java stack requires Java SE 6 (Java Runtime Environment 1.6) or later. 
You also need to install JDK 6 (jdk1.6) or later to be able to compile your applications. 
The latest available version is usually recommended.
The downloads are available from 
http://www.oracle.com/technetwork/java/javase/downloads/index.html

INSTALL

Extract the SDK Package on your computer. 
Add the libraries in the 'lib'-directory to the Java classpath in your environment. You
can then use them in your applications. 
See below (DEPLOYMENT), which libraries you actually need to use and deploy. 

The samples in the package assume that you have installed JDK and set up the
JAVA_HOME environment variable to the JDK directory 
(so that the Java compiler is located at %JAVA_HOME%\bin\javac.exe (Windows) 
or $JAVA_HOME/bin/javac (Linux))  

If your setup works, you can run the samples using the batch or shell script 
in the samples directory.

INSTALL IN ECLIPSE

If you are running Eclipse, you can install the SDK in it as follows:

- Go to the Package Explorer on the left.
- Select 'New-Java Project'
- In Eclipse 4.x, uncheck 'Use default location' and find the directory
  where you extracted the package for the 'Location'
- In Eclipse 3.x, select 'Create project from existing source' and find the directory
  where you extracted the package
- Press Finish

=> You should have the project in your workspace.

Note that Eclipse adds all libraries that are found to the Build Path by default. 
See below (DEPLOYMENT), which libraries you actually need to use and deploy. 

To use the SDK in your own projects, you can simply copy all the .jar:s from the 
lib directory and add them to your project's Build Path. They should appear in your
project's Referenced Libraries, after that. 

JAVADOC INSTALL 

The Javadoc of the SDK is zipped in the doc-directory. You can install it to Eclipse 
as zipped or extracted.

Find the SDK.jar from your project's Referenced Libraries (see above) and open
Properties for it. There you can locate the javadoc and link it for the SDK library.

You can do the same with the Opc.Ua.Stack.jar.

TUTORIALS

The tutorial documents in the doc-directory help you getting started with the SDK
development.

SOURCE INSTALL 

If you have the source version of the SDK, you can find it zipped in the src directory.

If you did not unzip the source before you installed it to Eclipse, 
you can install the source as follows:

Unzip the SDK and Stack source packages in the src directory.

In Eclipse, define that src folder is a source folder:

- In Package Explorer select 'src' folder
- From the popup menu (right click), select 'Build Path->Use as Source Folder'
- You may also need to refresh the Package Explorer to see the extracted source files: Press F5.

To test that everything works, go to the samples-folder in Eclipse and 
locate the client/SampleConsoleClient and/or server/SampleConsoleServer. 
Find 'Run As-Java Application' from the popup menu to start it up. 

DEPLOYMENT

When you deploy your applications, you will also need to deploy the respective libraries. 
But you won't necessarily need them all.

Prosys*              SDK classes             Necessary for all applications
Opc.Ua.Stack*.jar    UA communication        Necessary for all applications
slf4j-api-*.jar      SLF4J Logging Facade	   Necessary for all applications
slf4j-log4j12-*.jar  SLF4J-to-Log4J bridge   If you wish to use Log4J version 1.2 for logging (see below for more)
log4j-1.2.17.jar     Apache Log4J            If you wish to use Log4J version 1.2 for logging
http-*.jar           HTTP Core components    Necessary for all applications that support HTTPS
commons-logging*.jar Commons Logging         Necessary for all client applications that support HTTPS
bc*.jar              Bouncy Castle security  Optional, but recommend in Java SE for UA security support: 
                                             if not included SunJCE libraries, will be used. 
                                             Note that the SunJCE based security have not been tested as much as BC.
sc*.jar              Spongy Castle security  Necessary for Android applications to support UA security

It is also recommended that the SDK classes are bundled in your own application, instead of deploying it
as is. Obfuscation techniques are also recommended to guard it against illegal usage.

Also note the respective licenses for each library, as mentioned in LICENSE.txt

LOGGING LIBRARY SELECTION

The SDK in (since version 2.1) using SLF4J logging facade for message logging. This enables each application
to use any specific logging library, since SLF4J is just used to direct logging to a selected library.

slf4j-api must always be included in the application build path.

The actual library selection is done by picking one of the SLF4J bridges and the actual library. By default,
the SDK is shipped with Log4J version 1.2 - and therefore the slf4j-log4j12 bridge is also included. For other
logging alternatives, see http://slf4j.org

ANDROID DEVELOPMENT

The SDK can be used for Android application development as well as for normal Java development. The libraries 
that are usable on Android are

Prosys*  
Opc.Ua.Stack*.jar
slf4j-api-*.jar
slf4j-android-*.jar
sc*.jar

It is not possible to use HTTPS on Android due to library conflicts in HTTP Core components.

CODE GENERATION

The SDK contains a code generator, which can be used to create Java classes corresponding to
various OPC UA type definitions. The code generator is in the codegen directory. See the Readme.md in there
for more details on how to use it. 

Copyright (c) 2010-2015
Prosys PMS Ltd
http://www.prosysopc.com
uajava-support@prosysopc.com
