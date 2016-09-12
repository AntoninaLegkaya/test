***1. What is the suffix of Android application package file?***
  
   *Answer:*
   
All android application compile and packaged in one archive file with expansion
 .APK. This archive includes all code application (.dex files- compiled code for DavlikVM, resources, .manifest file). .APK is compressed archive for execution DavlikVM. It dosen’t encrypted, it’s  one of subset ZIP file format.
 
 ***2. What is Dalvik?***
 
   *Answer:* 
 
Register oriented virtual machine. VM for execute program which are written on Java language. But this VM use byte-code proprietary format: after compile java code (with help javace) special tool dx from Android SDK converts class files (.classes) into file  proprietary format .dex which included in package application (.apk). From version 4.4 was created alternative Android Runtime (from Android 5.0  completely replaced Davlik).
ART uses JIT- compilation (during application execution ), ART compiles the application during its installation. For backward compatibility ART uses the same byte - code that the Dalvik.

***3. Does each process have its own virtual machine? What consequences of this?*** 

   *Answer:* 
  
 Each Android application installed on your device operates in its own
 " sandbox "
Android operating system is a multi-user Linux system , in which each application is selected by the user 
By default the system assigns each application a unique Linux user ID ( the ID is only used by the system and the application is not known ) ; the system sets the permissions for all files in the application, so that access to them was restricted to a user with the ID assigned to the application ;
Each process has its own virtual machine ( VM ) , so application code runs in isolation from other applications
Each application runs in its own Linux process by default. Android starts the process when you want to run any application component , and then completes the process when it is no longer needed or when the system needs to free up memory for other applications .
Thus the Android system implements the principle of least privilege . That is, each default application has access to only those components that it needs to work , and to any other . This formed only a secure environment in which the application does not have access to 
unlawful areas system.

However, applications have options of their data to other applications and access to system services :
the two applications can be assigned to one Linux user ID . In this case, each of them will be able to access the files of other applications . To conserve system resources, you can also make an application with the same user ID carried out in a single process using a single Linux and VM ( the application must also be signed by a single certificate ) ;
the app may request permission to access the data of the device , such as a user's contacts , SMS- messages , plug-in memory (SD- card ) card, camera , Bluetooth, and others. All the permission should be granted an application during installation .


***4. Name different types of application components.***

   *Answer:* 
  
 Activities, Services, Broadcast receivers, Content providers and Intents.
 
***5. Where does the manifest file must be located?***

   *Answer:* 
  
In the root folder of each application should be AndroidManifest.xml file.

***6. What information can be included in the manifest file?***

   *Answer:* 
  
+ it specifies the Java package name for the application. This package name is a unique application ID .
+ it describes all application components- Activities, Services, Broadcast receivers и Content providers, it contains the names of the classes that implement each component , and publishes their capabilities ( indicating , for example , what kind of messages they can take Intent). On the basis of those declarations the Android system can determine what components make up the application and can be run under any conditions .
+ it determines the application components will be placed in public processes.
He declares which permissions should be given to the application so that it can gain access to the protected parts of the API- interface and interact with other applications.
+ it also announces permission required for interaction with the components of the application.
+ it contains a list of classes Instrumentation, which provide profile information and other information, when executed applications. These advertisements  are present in the manifest file only during development and debugging an application and are removed before it, publication .
He announces a minimum level of Android API- interface , which requires an application .
+ it contains list of libraries which should be linked application.

