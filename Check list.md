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
	
+ To start a Service
	A Service is a component that performs operations in the background without a user interface. You can start a service to perform a one-time operation (such as download a file) by passing an Intent tostartService(). The Intent describes the service to start and carries any necessary data. If the service is designed with a client-server interface, you can bind to the service from another component by passing an Intent to bindService().
	
+ To delivery broadcast
	A broadcast is a message that any app can receive. The system delivers various broadcasts for system events, such as when the system boots up or the device starts charging. You can deliver a broadcast to other apps by passing an Intent to sendBroadcast(), sendOrderedBroadcast(), or sendStickyBroadcast().

***16. What methods can deliver intents to a particular component?***

 *Answer:*
 
+ sartActivity()
+ startActivityForResult()
+ onActivityResult() callback
+ startService()
+ bindService()
+ sendBroadcast()

    Broadcast the given intent to all interested BroadcastReceivers. This call      is asynchronous; it returns immediately, and you will continue executing        while the receivers are run. No results are propagated from receivers and       receivers can not abort the broadcast.
+ sendOrderedBroadcast()

    Broadcast the given intent to all interested BroadcastReceivers, delivering     them one at a time to allow more preferred receivers to consume the             broadcast before it is delivered to less preferred receivers. This call is      asynchronous; it returns immediately, and you will continue executing while     the receivers are run. Allow receivers to propagate results or abort the        broadcast
+ sendStickyBroadcast()

     This method was deprecated in API level 21
     
     
***17. What does intent object contains? Describe each part.***
 
*Answer:*
  
 An Intent object carries information that the Android system uses to determine which component to start (such as the exact component name or component category that should receive the intent), plus information that the recipient component uses in order to properly perform the action (such as the action to take and the data to act upon).
 
+ **Component name**
 (this is optional, but it's the critical piece of information that makes an intent explicit, meaning that the intent should be delivered only to the app component defined by the component name. Without a component name, the intent is implicit and the system decides which component should receive the intent based on the other intent information (such as the action, data, and category—described below). So if you need to start a specific component in your app, you should specify the component name. 
This field of the Intent is a ComponentName object, which you can specify using a fully qualified class name of the target component, including the package name of the app. For example, com.example.ExampleActivity. You can set the component name with setComponent(),setClass(), setClassName(), or with the Intent constructor)
+ **Action**
(A string that specifies the generic action to perform (such as view or pick). In the case of a broadcast intent, this is the action that took place and is being reported. The action largely determines how the rest of the intent is structured—particularly what is contained in the data and extras.You can specify your own actions for use by intents within your app (or for use by other apps to invoke components in your app), but you should usually use action constants defined by the Intent class or other framework classes. If you define your own actions, be sure to include your app's package name as a prefix. You can specify the action for an intent with setAction() or with an Intent constructor.)

+ **Data**
(The type of data supplied is generally dictated by the intent's action. The URI (a Uri object) that references the data to be acted on and/or the MIME type of that data. When creating an intent, it's often important to specify the type of data (its MIME type) in addition to its URI. So specifying the MIME type of your data helps the Android system find the best component to receive your intent. However, the MIME type can sometimes be inferred from the URI—particularly when the data is a content: URI, which indicates the data is located on the device and controlled by a ContentProvider, which makes the data MIME type visible to the system. To set only the data URI, call setData(). To set only the MIME type, call setType(). If necessary, you can set both explicitly with setDataAndType(). If you want to set both the URI and MIME type, **do not** call setData() and setType() because they each nullify the value of the other. Always use setDataAndType() to set both URI and MIME type.)
Category (A string containing additional information about the kind of component that should handle the intent. Any number of category descriptions can be placed in an intent, but most intents do not require a category. (CATEGORY_BROWSABLE, CATEGORY_LAUNCHER))
+ **Extras**
( Key-value pairs that carry additional information required to accomplish the requested action. You can add extra data with various putExtra() methods, each accepting two parameters: the key name and the value. You can also create a Bundle object with all the extra data, then insert the Bundle in the Intent with putExtras(). The Intent class specifies many EXTRA_* constants for standardized data types. If you need to declare your own extra keys (for intents that your app receives), be sure to include your app's package name as a prefix.)
+ **Flags** (The flags may instruct the Android system how to launch an activity (for example, which task the activity should belong to) and how to treat it after it's launched (for example, whether it belongs in the list of recent activities).)

***18. What two groups of the intents do you know? What the difference?***

*Answer:*
 
There are two types of intents:
+ **Explicit** intents specify the component to start by name (the fully-qualified class name). You'll typically use an explicit intent to start a component in your own app, because you know the class name of the activity or service you want to start. For example, start a new activity in response to a user action or start a service to download a file in the background.
+ **Implicit** intents do not name a specific component, but instead declare a general action to perform, which allows a component from another app to handle it. For example, if you want to show the user a location on a map, you can use an implicit intent to request that another capable app show a specified location on a map.

   When you create an explicit intent to start an activity or service, the system immediately starts the app component specified in the Intent object.
   When you create an implicit intent, the Android system finds the appropriate component to start by comparing the contents of the intent to the intent filters declared in the manifest file of other apps on the device. If the intent matches an intent filter, the system starts that component and delivers it the Intent object. If multiple intent filters are compatible, the system displays a dialog so the user can pick which app to use.
   
***19. What is intent-filter?***
 
*Answer:*
 
An intent filter is an expression in an app's manifest file that specifies the type of intents that the component would like to receive. For instance, by declaring an intent filter for an activity, you make it possible for other apps to directly start your activity with a certain kind of intent. Likewise, if you do not declare any intent filters for an activity, then it can be started only with an explicit intent.
    To ensure your app is secure, always use an explicit intent when starting a Service and do not declare intent filters for your services. Using an implicit intent to start a service is a security hazard because you cannot be certain what service will respond to the intent, and the user cannot see which service starts. Beginning with Android 5.0 (API level 21), the system throws an exception if you call bindService() with an implicit intent.
    
***20. How to check what components matches to your intent?***
    
  *Answer:*
   
  It's possible that a user won't have any apps that handle the implicit intent you send to startActivity(). If that happens, the call will fail and your app will crash. To verify that an activity will receive the intent, call resolveActivity() on your Intent object. If the result is non-null, then there is at least one app that can handle the intent and it's safe to call startActivity(). If the result is null, you should not use the intent and, if possible, you should disable the feature that issues the intent.
When the system receives an implicit intent to start an activity, it searches for the best activity for the intent by comparing the intent to intent filters based on three aspects:
+ The intent action
+ The intent data (both URI and data type)
+ The intent category

The following sections describe how intents are matched to the appropriate component(s) in terms of how the intent filter is declared in an app's manifest file.
   + Action test

To get through this filter, the action specified in the Intent must match one of the actions listed in the filter. If the filter does not list any actions, there is nothing for an intent to match, so all intents fail the test. However, if an Intent does not specify an action, it will pass the test (as long as the filter contains at least one action).

+ Category test

For an intent to pass the category test, every category in the Intent must match a category in the filter. The reverse is not necessary—the intent filter may declare more categories than are specified in the Intent and the Intent will still pass. Therefore, an intent with no categories should always pass this test, regardless of what categories are declared in the filter.
Android automatically applies the the CATEGORY_DEFAULT category to all implicit intents passed to *startActivity()* and *startActivityForResult()*. So if you want your activity to receive implicit intents, it must include a category for"android.intent.category.DEFAULT" in its intent filters (as shown in the previous <intent-filter> example.

+ Data test

To specify accepted intent data, an intent filter can declare zero or more <data> elements. 
Each <data> element can specify a URI structure and a data type (MIME media type). There are separate attributes — scheme, host, port, and path — for each part of the URI:
<scheme>://<host>:<port>/<path>
For example:
content://com.example.project:200/folder/subfolder/etc
In this URI, the scheme is content, the host is com.example.project, the port is 200, and the path is folder/subfolder/etc. Each of these attributes is optional in a <data> element, but there are linear dependencies:

+ If a scheme is not specified, the host is ignored.
+ If a host is not specified, the port is ignored.
+ If both the scheme and host are not specified, the path is ignored.

When the URI in an intent is compared to a URI specification in a filter, it's compared only to the parts of the URI included in the filter. For example:
+ If a filter specifies only a scheme, all URIs with that scheme match the filter.
+ If a filter specifies a scheme and an authority but no path, all URIs with the same scheme and authority pass the filter, regardless of their paths.
+ If a filter specifies a scheme, an authority, and a path, only URIs with the same scheme, authority, and path pass the filter.

A path specification can contain a wildcard asterisk (*) to require only a partial match of  the path name. The data test compares both the URI and the MIME type in the intent to a URI and MIME type specified in the filter. 

The rules are as follows:

 1. An intent that contains neither a URI nor a MIME type passes the test only if the filter does not specify any URIs or MIME types.
 2. An intent that contains a URI but no MIME type (neither explicit nor inferable from the URI) passes the test only if its URI matches the filter's URI format and the filter likewise does not specify a MIME type.
 3. An intent that contains a MIME type but not a URI passes the test only if the filter lists the same MIME type and does not specify a URI format.
 4. An intent that contains both a URI and a MIME type (either explicit or inferable from the URI) passes the MIME type part of the test only if that type matches a type listed in the filter. It passes the URI part of the test either if its URI matches a URI in the filter or if it has a content: or file: URI and the filter does not specify a URI. In other words, a component is presumed to support content: and file: data if its filter lists only a MIME type.

This last rule, rule (d), reflects the expectation that components are able to get local data from a file or content provider. Therefore, their filters can list just a data type and do not need to explicitly name the content: and file: schemes. Another common configuration is filters with a scheme and a data type. For example, a <data> element like the following tells Android that the component can retrieve video data from the network in order to perform the action.

***21. What class is the parent for all UI elements?***

 *Answer:*

The user interface for activity (Activity) is determined using models. During the execution of models - copies of the «android.view.ViewGroups». The model determines the user interface elements, their properties and location. Elements of the user interface based on the class «android.view.View». **ViewGroup - View subclass**. Layouts may contain UI (Visits / or) other Types of Layouts (ViewGroups). You should not make large investments in child elements ViewGroups, as this affects performance.

***22. In which two groups all UI elements are separated?***

*Answer:*

The user interface for each application component is determined by the object hierarchy  View and ViewGroup

***23. What layouts do you know?***

*Answer:*

**LinearLayout**  is a view group that aligns all children in a single direction, vertically or horizontally. You can specify the layout direction with the android:orientation attribute. All children of a LinearLayout are stacked one after the other, so a vertical list will only have one child per row, no matter how wide they are, and a horizontal list will only be one row high (the height of the tallest child, plus padding). A LinearLayout respects margins between children and the gravity (right, center, or left alignment) of each child.

**RelativeLayout** — type layout in which the positioning elements takes place in relation to each other and relative to the main container.
Attributes positioning relative to the container:
+ android:layout_alignParentBottom – bottom element is at the bottom of the container
+android:layout_alignParentLeft – the left part of the element adjacent to the left side of the container
+ android:layout_alignParentRight – the right part of the element adjacent to the right side of the container
+ android:layout_alignParentTop – the item at the top of the container
+ android:layout_centerHorizontal – the element is positioned centrally with respect to the horizontal size of the container
+ android:layout_centerInParent – the element is positioned centrally with respect to the horizontal and vertical size of the container sizes
+ android:layout_centerVertical – the element is positioned centrally with respect to the vertical size of the container

Attributes positioning relative to other elements. As the values ​​of these attributes are set id element relative positioning of which will be produced.

+ android:layout_above –are placed above said element
+ android:layout_below – are placed below said element
+ android:layout_toLeftOf – are placed on the left of the specified item
+ android:layout_toRightOf – Are placed on the right of the specified item
Alignment relative to other elements:
+ android:layout_alignBaseline – aligns the baseline element baseline specified item
+ android:layout_alignBottom – aligns the bottom of the element at the bottom specified element
+ android:layout_alignLeft – alignы left edge of the element to left edge specified element
+ android:layout_alignRight – aligns right edge of the element to the right edge specified element
+ android:layout_alignTop – aligns top part of the element in correspondence with the upper part specified element

**TableLayout** - tabular layout.
Organize items in the rows and columns of the table. To arrange rows serves tag, and the number of columns is determined by the maximum number of elements within the same. If the element is to take a few cells used attribute android: layout_span. Default arrange rows of the table, if we do not organize rows and columns that you want to use the attribute android: layout_column.

**AbsoluteLayout**
(Use is not recommended, not recommended)
AbsoluteLayout - means that each layout element will have an absolute position relative to the upper left corner of the screen defined by using x and y coordinates. Those. upper left corner corner of the screen when AbsoluteLayout has the coordinates x = 0, y = 0.
Position is specified in the attributes of an element Android: layout_x and Android: layout_y.

**FrameLayout** - type layout in which you can only display one item per line. Those. if inside FrameLayout you place multiple items, the following will be displayed on top of the previous one.


***24. What the difference between LinearLayout and RelativeLayout?***

*Answer:*

LinearLayout is a view group that aligns all children in a single direction, vertically or horizontally.
RelativeLayout — type layout in which the positioning elements takes place in relation to each other and relative to the main container
25. What method of the Activity class should be called to attach the view hierarchy tree to the screen for rendering?
While compiling the application, each XML layout file is compiled into a View resource. You need to download a layout resource in the application code in the implementation of the callback method Activity.onCreate (). To do this, call setContentView() method, passing a reference to the layout resource in the following form: R.layout.layout_file_name.


***26. What is the suffix of the files, where you can define your layout?***

*Answer:*

Alternate Layouts - it allows you to use a different layout for different screen orientations.
XML for an alternative layout is placed in the project folder:

- res/layout-land – alternative layout for  landscape UI
- res/layout-port – alternative layout for  portrait UI
- res/lauout-square – alternative layout for   square UI

***27. How to receive some event from your view?***

*Answer:*

Class View also provides a set of nested interfaces with callbacks. These interfaces, called event receivers, and serve as interceptors user interaction with your user interface. Event Listeners - a class interface View, which contains one callback method. These methods will be called by the platform Android, as a result of user interaction with the user interface object is activated display object View, in which the receiver is registered. Interfaces included in the event receiver are the following callback methods: onClick(), onLongClick(), onFocusChange(), onKey(), onTouch(), onCreateContextMenu(). To define one of these methods and handle events, implement a nested interface in your process or define it as an anonymous class. Then, pass an instance of the implementation of the appropriate method View.set ... Listener ().Event Handlers. Some of the common callbacks used for event handling, including:
+ onKeyDown(int, KeyEvent) - Called when a new key event occurs.
+ onKeyUp(int, KeyEvent) - Called when a key up event occurs.
+ onTrackballEvent(MotionEvent) - Called when a trackball motion event occurs.
+ onTouchEvent(MotionEvent) - Called when a touch screen motion event occurs.
+ onFocusChanged(boolean, int, Rect) - Called when the view gains or loses focus.
when managing more complex events inside a layout, consider these other methods:
+ Activity.dispatchTouchEvent(MotionEvent) - This allows your Activity to intercept all touch events before they are dispatched to the window.
+ ViewGroup.onInterceptTouchEvent(MotionEvent) - This allows a ViewGroup to watch events as they are dispatched to child Views.
+ ViewParent.requestDisallowInterceptTouchEvent(boolean) - Call this upon a parent View to indicate that it should not intercept touch events with onInterceptTouchEvent(MotionEvent).

***28. Do you can Instantiate layout elements at runtime?***

*Answer:*

Application can programmatically create objects View and ViewGroup (as well as manage their properties). You can be declared in default XML layouts, including screen elements that will appear in the pilot, and their properties. Then you can add the application code, which allows you to change the status of objects on the screen (including the declared XML) at runtime. The advantage of user interface declarations in the XML file is that in this way you can more effectively separate the presentation of your application from the code that controls its behavior.

***29. This is the typical XML attribute android:id="@+id/my_button". Please, explain, what does each word means.***

*Answer:*

Any View object may be associated integer identifier that is used to refer to the View object uniqueness in the hierarchy. During application compile this identifier is used as an integer, but the ID is usually assigned in the layout XML file as a string in the id attribute.
@ Character at the beginning of the line indicates that the XML processor must parse the remainder of the identifier parsing execute it and identify it as a resource identifier. plus (+) symbol indicates that this is the name of a new resource that you want to create and add to our resources (in R.java file).

****30. What is the name of Android resource class?***

*Answer:*

R.java

***32. What the difference between fill_parent and match_parent?***

*Answer:*

**FILL_PAREN**T (renamed **MATCH_PARENT** in API Level 8 and higher), which means that the view wants to be as big as its parent (minus padding)
**WRAP_CONTENT**, which means that the view wants to be just big enough to enclose its content (plus padding)


***33. What are the padding and margins? How do they refer to the size?***

*Answer:*

**Padding** is the space inside the border, between the border and the actual view's content. Note that padding goes completely around the content: there is padding on the top, bottom, right and left sides (which can be independent).
**Margins** are the spaces outside the border, between the border and the other elements next to this view. In the image, the margin is the grey area outside the entire object. Note that, like the padding, the margin goes completely around the content: there are margins on the top, bottom, right, and left sides.

**match_parent (fill_parent)** - it means that the element will take all available to him in the parent element width / height.

**wrap_content** - width / height of the element is determined by its contents

***34. Can components of the same application run in the different processes? How to do this?***

*Answer:*

By default, all **components of the same application run in the same process** and most applications should not change this. However, if you find that you need to control which process a certain component belongs to, you can do so in the manifest file.
The manifest entry for each type of component element—<activity>, <service>, <receiver>, and <provider>—supports an **android:process** attribute that can specify a process in which that component should run. You can set this attribute so that each component runs in its own process or so that some components share a process while others do not. You can also set android:process so that components of different applications run in the same process—provided that the applications share the same Linux user ID and are signed with the same certificates.
The <application> element also supports an android:process attribute, to set a default value that applies to all components.
If your launching app has the package name
com.mycompany.mymainapp
and is therefore assigned a process name that is that same string, then, if you use
android:process=":myhelp"
on your launched activity, it will be assigned the process name
com.mycompany.mymainapp:myhelp
and that process will have its own, separate process ID, which you can view (for example in DDMS).

***35. There are five levels in the importance hierarchy of the processes. Call them in details.***

*Answer:*

**Foreground process**
A process that is required for what the user is currently doing. A process is considered to be in the foreground if any of the following conditions are true:
+ It hosts an Activity that the user is interacting with (the Activity's onResume() method has been called).
+ It hosts a Service that's bound to the activity that the user is interacting with.
+ It hosts a Service that's running "in the foreground"—the service has called startForeground().
+ It hosts a Service that's executing one of its lifecycle callbacks (onCreate(), onStart(), or onDestroy()).
+ It hosts a BroadcastReceiver that's executing its onReceive() method.
Generally, only a few foreground processes exist at any given time. They are killed only as a last resort—if memory is so low that they cannot all continue to run. Generally, at that point, the device has reached a memory paging state, so killing some foreground processes is required to keep the user interface responsive.

**Visible process**
A process that doesn't have any foreground components, but still can affect what the user sees on screen. A process is considered to be visible if either of the following conditions are true:
+ It hosts an Activity that is not in the foreground, but is still visible to the user (its onPause() method has been called). This might occur, for example, if the foreground activity started a dialog, which allows the previous activity to be seen behind it.
+ It hosts a Service that's bound to a visible (or foreground) activity.
A visible process is considered extremely important and will not be killed unless doing so is required to keep all foreground processes running.
Service process
A process that is running a service that has been started with the startService() method and does not fall into either of the two higher categories. Although service processes are not directly tied to anything the user sees, they are generally doing things that the user cares about (such as playing music in the background or downloading data on the network), so the system keeps them running unless there's not enough memory to retain them along with all foreground and visible processes.

**Background process**
A process holding an activity that's not currently visible to the user (the activity's onStop() method has been called). These processes have no direct impact on the user experience, and the system can kill them at any time to reclaim memory for a foreground, visible, or service process. Usually there are many background processes running, so they are kept in an LRU (least recently used) list to ensure that the process with the activity that was most recently seen by the user is the last to be killed. If an activity implements its lifecycle methods correctly, and saves its current state, killing its process will not have a visible effect on the user experience, because when the user navigates back to the activity, the activity restores all of its visible state. See the Activities document for information about saving and restoring state.

**Empty process**
A process that doesn't hold any active application components. The only reason to keep this kind of process alive is for caching purposes, to improve startup time the next time a component needs to run in it. The system often kills these processes in order to balance overall system resources between process caches and the underlying kernel caches.

