# archidoc
Documenting Software Architecture Plugin.
Visualizing code of your application.
It generates full diagram of your classes in .dot(graphviz) file.

Plugin is based on work - Classgraph (https://github.com/classgraph/classgraph).
You can open the generated dot file in a vector editor.
You can convert then the model into The Standard for exchange of architecture models from The Open Group

Plugin uses all jars in runtimeClasspath configuration
and also main source set build folder (there for the task depends on 'build' task)
Scans both project and subprojects

Plugin avialable at https://plugins.gradle.org/u/wilmerkrisp

Project for testing https://github.com/wilmerkrisp/archidoc-test

<h2>for using plugin in gradle</h2>

1) configure plugin in build.gradle, select whrere to put file and your programm packages for analysis.

plugins { <br>
    id 'life.expert.archidoc' version '1.0.9'  <br>
    }<br>
<br>
project.tasks.getByName("archidoc").dependsOn 'build'
<br>
archidoc { <br>
    file  "$buildDir/architecture/classdiagram.dot" <br>
    packages = ['com.my'] <br>
    enableAllInfo()  <br>
}<br>

2) run task archidoc

./gradlew arhidoc


<h2>Also plugin options avialable:</h2>
<br>
multiProject()          //if you want analyze also gradle subprojects, please build all subprojects because the task doesnot depend on subprojects build-tasks
<br>
Also Classgraph options avialable:

    verbose()           // print all log messages
    enableAllInfo()    //  all information about classes
    enableFieldInfo()
    enableMethodInfo()
    ignoreFieldVisibility()
    ignoreMethodVisibility()
    enableClassInfo()
    enableAnnotationInfo()
    ignoreClassVisibility()


<h2>Configuration for multiproject build:</h2>

project.tasks.getByName("archidoc").dependsOn 'build',**':subproject:build'** <br>
archidoc {<br>
    file "$buildDir/architecture/classdiagram.dot"<br>
    packages = ['com.my', 'org.your']<br>
    **multiProject()**<br>
    enableAllInfo()<br>
    verbose()<br>
}<br>
<br>


<h2>how to instantly get .PNG file</h2>

plugins {<br>
    id "life.expert.archidoc" version "1.0.9"<br>
    id "com.simonharrer.graphviz" version "0.0.1"<br> // thanks to https://github.com/simonharrer/gradle-graphviz-plugin
}

archidoc {<br>
    //Sorry. For "graphviz plugin" No configuration possible. <br>
    //It just converts your **src/main/graphviz/**.dot to build/graphviz/.png.<br>
    file "${project.projectDir}/src/main/graphviz/classdiagram.dot"<br>
    packages = ['org' ]<br>
    enableAllInfo()<br>
}<br>

graphviz.dependsOn(archidoc)<br>


![howtouse](howtouse.png)

![resultimagexample](resultimagexample.png)


<h2> what about really architecture  </h2>
Programming is an art.
What does a framework look like?
Below is a hierarchical class diagram for the google Truth framework (about 600 items!).
Obtained by using the archidoc plug-in. OmniGraffle was used to convert dot->png.

<img src="ntruth600_hierarh_plus.png"  width="948">