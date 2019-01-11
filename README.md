# archidoc
Documenting Software Architecture Plugin.
Visualizing code of your application. 
It generates full diagram of your classes in .dot(graphviz) file.

Plugin is based on work - Classgraph (https://github.com/classgraph/classgraph). 
You can open the generated dot file in a vector editor. 
You can convert then the model into The Standard for exchange of architecture models from The Open Group

for using plugin in gradle

1) configure plugin in build.gradle

archidoc {
    file  "$buildDir/architecture/classdiagram.dot"
    packages ='life.expert'
    enableAllInfo()
}

2) run task archidoc

./gradlew arhidoc


also opions avialable:

    enableFieldInfo()
    enableMethodInfo()      
    ignoreFieldVisibility() 
    ignoreMethodVisibility()    
    enableClassInfo()  
