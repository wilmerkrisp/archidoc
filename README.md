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

for using plugin in gradle

1) configure plugin in build.gradle, select whrere to put file and your programm packages for analysis.

plugins { <br>
    id 'life.expert.archidoc' version '1.0.0'  <br>
    }
    
archidoc { <br>
    file  "$buildDir/architecture/classdiagram.dot" <br>
    packages = ['com.my'] <br>
    enableAllInfo()  <br>
}

2) run task archidoc

./gradlew arhidoc


Also options avialable:

    enableFieldInfo()
    enableMethodInfo()      
    ignoreFieldVisibility() 
    ignoreMethodVisibility()    
    enableClassInfo()  

Plugin avialable at https://plugins.gradle.org/u/wilmerkrisp
