# CharacterViewer

Character viewer notes:

This document presents a brief overview of the architecture decisions made for the app. 

**BUILD:**

Gradle’s productFlavors are used to specify four different versions of the app:
* ‘simpsons’
* ‘simpsons-debug’
* ‘starwars’
* ‘starwars-debug’

This allowed me to specify all the differences (package name, app name, and API url) in the build.gradle file itself, while keeping the entire codebase the same. 

Getting a different version of the app is as simple as specifying the flavor at build time. The app will programmatically access package name, app name, and url from the build files. 

**LAYOUT AND APPEARANCE:**

*Switching between handsets and tablets*

The setup of the master/detail flow is based on Android’s recommended structure for dealing with both handsets and tablets:

![Diagram] (https://developer.android.com/images/fundamentals/fragments.png)

In this case MainActivity acts as the controller for both List and Detail Fragments (in a tablet view), or List (for a handset), both of which communicate with parent Activities via interfaces. That ensures independence for each fragment, and minimal code for the views to operate on different screen sizes. 

MainActivity’s layout is defined in both layout and layout-large folders, so Android will automatically inflate the one appropriate for the screen size. Each layout contains FrameLayouts to hold fragments; this choice allows the Fragment contents to be dynamic. 

This architecture allows maximum flexibility when dealing with screens of different sizes, while minimizing the number of files necessary. Since the requirements of the project were quite simple, I didn’t see any need to create a more complicated setup. 

*Toggling between list and grid*

ListFragment has two options for LayoutManagers: LinearLayoutManager and GridLayoutManager. Selecting one of the icons in MainActivity’s toolbar triggers a function in ListFragment to swap LayoutManagers, which reloads the view with the appropriate layout. 

Switching LayoutManagers was necessary to repopulate the item layouts in each view, as grids have the addition of an ImageView for profile pictures. 

This creates some overhead in return for control over the appearance of each item in select view modes. 

*Configuration changes*

I faced a problem during configuration change, as Android would destroy and recreate everything onscreen (parent Activity, fragment, as well as reloading all data and recreating the list), creating a lot of overhead. To prevent this, I specified configChanges = “screenSize|orientation|keyboardHidden” in the Manifest file. 

An alternative would be to override savedInstanceState() and onRestoreInstanceState() and programmatically save and recreate the view. However, since the portrait and landscape modes present the same information, the configChanges flag simply retains the same Activity and Fragment instance, with less work. 

*Animation*

The recyclerview adapter implements a delay animation on loading and scrolling - the items appear to glide up the screen dynamically on scroll. Supporting this animation is part of the reason why the minimum required API level was 21. 
 
*Design*

The app follows Material design themes for an updated and clean look. 

**NETWORK:**

I chose to use several of Square’s libraries to handle network operations instead of AsyncTasks and HttpRequests, which present problems for thread safety, among other things. 

* API functions: Retrofit2 was used to handle API requests, in conjunction with Gson to parse the JSON response automatically into POJO’s. Retrofit is fast, easily customizable, and abstracts away most concurrency and thread problems. It is also easy to use along with callbacks to update the UI thread.

* Caching: OkHttp3 was used to provide easy caching of data for offline access. By altering Retrofit request headers with OkHttp, the app checks for cache access first before making network requests, also reducing network overhead. 

* Image downloading: Picasso was used to download profile images, and caches the image on disk; it also allows easy placement of a placeholder image while the real one is downloading. 


**DATA:**

The Character data object specifies attributes for name, description and image URL, and is the primary object used to fill out individual views. The DataManager class holds the list of Characters that is used to populate the list and detail views, and is cached for offline access. 

Retrofit’s response parses the returned JSON into POJO’s, which is why the Model package contains classes for each object type returned. The response triggers a callback that parses main container object (APIResponse) to find and create individual Characters, which are then added to DataManager’s list. Completion of the list triggers another callback to update the UI thread with the data. 

Character class also contains a parser function to extract name and description from Result’s “text” field using a regular expression. 


**TESTS**

JUnit4 and Espresso are used for basic instrumentation and unit tests.
