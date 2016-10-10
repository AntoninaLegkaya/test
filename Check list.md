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


***7. How to make localization on in your Android project?***
 
  *Answer:*
  
For creating localization we can use mechanism of dynamic  selection of desired resource. We can create specific directory structure in the project. This directory tree will be include resources for specific languages, regions andhardware configurations.
We can  select specify alternative values, create own directory structure within the res directory by using a hyphen (-). For example, we want to create additional string resources for French, French Canadian region and for the Russian language. Then it will be looking in the project directory structure as follows:

      Project/
              res/ values/
		                      strings.xml
		             values-fr/
		                      strings.xml
	              	values-fr-rCA/
		                      strings.xml
                  values-ru/
		                      strings.xml


  
***8. Which methods can be used to start Activity?***
  
   *Answer:*
   
The startActivity(Intent) method is used to start a new activity, which will be placed at the top of the activity stack. It takes a single argument, an Intent, which describes the activity to be executed.
Sometimes we want to get a result back from an activity when it ends. To do this, we call the startActivityForResult(Intent, int) version with a second integer parameter identifying the call. The result will come back through our onActivityResult(int, int, Intent) method.

***9. What the difference between startActivity and startActivityForResult?***

  *Answer:*
  
StartActivity(Intent) and startActivityForResult(Intent, int) used to start new Activity and do some operation, different in that  at second  method we started activity and return result. At the end of follow-up operation, it returns the result in the Intent object in an induced onActivityResult () method.

***10. Name all callbacks of the Activity life-cycle.***

  *Answer:*
  
Activity can be one of four states:
Active или Running – activity in the foreground, has focus and user can interact with it;
Paused- it lost focus, but is  still visible to the user, above is another activity,which is transparent or not cover all screen, suspended activity  completely “living” (it state is saved), but can be destroy by system if memory shortage ;
Stopped- another activity is fully covered screen. It does not visible to the user and will  be destroyed by system, when will be needed memory.
If an activity is paused or stopped, the system can drop the activity from memory by either asking it to finish, or simply killing its process. When it is displayed again to the user, it must be completely restarted and restored to its previous state.
Moving from state to state, Activity notify the calling the following methods:

  void onCreate()
  
  void onStart()
  
  void onRestart()
  
  void onResume()
  
  void onPause()
  
  void onStop()
  
  void onDestroy()
  
***11. What state of the activity corresponds to the each callback?***

  *Answer:*
  
There are three key loops we may be interested in monitoring within our activity:

+ Activity lifecycle begin from call method onCreate(), where will do all setup of "global" state in onCreate(), and release all remaining resources in onDestroy().
+ The visible lifetime of an activity happens between a call to onStart() until a corresponding call to onStop(). During this time the user can see the activity on-screen, though it may not be in the foreground and interacting with the user. The onStart() and onStop() methods can be called multiple times, as the activity becomes visible and hidden to the user.
+ The foreground lifetime of an activity happens between a call to onResume() until a corresponding call to onPause(). During this time the activity is in front of all other activities and interacting with the user. An activity can frequently go between the resumed and paused states- for example method onPause() call when device goes to sleep or when call other activity,method onResume() - when an activity result is delivered

+ onCreate()

              It called once when creating activity. It must be done the initial configuration - create views, bind data to lists, etc. Behind him, always call OnStart () method.
              
   + onRestart()

                Called after your activity has been stopped, prior to it being started again. Always followed by onStart()
   +  onStart()
    
                Called when the activity is becoming visible to the user. Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
       + onResume()
       
                            Called when the activity will start interacting with the user. At this  point your activity is at the top of the activity stack, with user input going to it. Always followed by onPause().
         
        + onPause()
        
 			                 Called when the system is about to start resuming a previous activity. This is typically used to commit unsaved changes to persistent data, stop animations and other things that may be consuming CPU, etc. Implementations of this method must be very quick because the next activity will not be resumed until this method returns. Followed by either onResume() if the activity returns back to  the front, or onStop() if it becomes invisible to the user.
 	+ onStop()
 	
		        	Called when the activity is no longer visible to the user, because another activity has been resumed and is covering this one. This may happen either because a new activity is being started, an existing one is being brought in front of this one, or this one is being destroyed.
              Followed by either onRestart() if this activity is coming back to interact with the user, or onDestroy() if this activity is going away.
+ onDestroy()

              The final call you receive before your activity is destroyed. This can happen either because the activity is finishing (someone called finish() on it, or because the system is temporarily destroying this instance of the activity to save space. 

Three methods OnPause(), OnStop() and OnDestroy() when system can kills process but only onPause() is guaranteed to be called before destruction process. Therefore it is necessary to use OnPause () method to save the modified data .

***12. Callback onCreate(Bundle) has an incoming parameter. What is it used for?***

 *Answer:*
	
Before making the operation accessible to the destruction, the system calls onSaveInstanceState () method. The system transmits this method Bundle object, we can save information about the status (typically non-persistent, dynamic data) of operations in the form of pairs of "name-value" through the use of techniques such as putString () and putInt (). Then, if the system ends the process of your application and the user returns to your operation, the system re-generates and transmits an operation Bundle object in both methods: onCreate () and onRestoreInstanceState (). Using any of these methods can be derived from the object information stored Bundle of operation and restore its state. If this information is missing, the Bundle object transmitted with null (this happens when the operation is created for the first time).

***13. Which method is typically used to commit unsaved changes to persistent data?***

 *Answer:*
  
The system can kills process in three methods OnPause(), OnStop() and OnDestroy() but only onPause() is guaranteed to be called before destruction process. Therefore it is necessary to use OnPause () method to save the modified data . If our  data is so big that we have to storage it for several seconds, we may open a background Service (e.g. IntentService) to save.

***14. Imagine, the system destroyed an activity in order to recover memory. Which callback method of an activity class allows you to save information about the state of your activity?***

 *Answer:*
	
When the system destroys an activity in order to recover memory, the Activity object is destroyed, so the system cannot simply resume it with its state intact. Instead, the system must recreate the Activity object if the user navigates back to it. Yet, the user is unaware that the system destroyed the activity and recreated it and, thus, probably expects the activity to be exactly as it was. In this situation, you can ensure that important information about the activity state is preserved by implementing an additional callback method that allows you to save information about the state of your activity: onSaveInstanceState().

***15. What is Intent?***

 *Answer:*
 
An Intent is a messaging object you can use to request an action from another app component.
Intents facilitate communication between components. There are three use-cases:

	+ To start an Activity
	
      	You can start a new instance of an Activity by passing an Intent to startActivity(). The Intent describes the activity to start and carries any necessary data.If you want to receive a result from the activity when it finishes, call startActivityForResult(). Your activity receives the result as a separate Intent object in your activity's onActivityResult() callback. 
	
	+To start a Service
	
	A Service is a component that performs operations in the background without a user interface. You can start a service to perform a one-time operation (such as download a file) by passing an Intent tostartService(). The Intent describes the service to start and carries any necessary data. If the service is designed with a client-server interface, you can bind to the service from another component by passing an Intent to bindService().
	
	+ To delivery broadcast
	
	A broadcast is a message that any app can receive. The system delivers various broadcasts for system events, such as when the system boots up or the device starts charging. You can deliver a broadcast to other apps by passing an Intent to sendBroadcast(), sendOrderedBroadcast(), or sendStickyBroadcast().

	
