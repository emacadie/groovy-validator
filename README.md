This project has a few annotations that validate fields in POGOs, sort of like Grails constraints.   

I will attempt to make some annotations for properties in Groovy.    

gradle runGroovy -PmainClass=info.shelfunit.properties.sample.SampleRunner    

Java 7 API:  
http://docs.oracle.com/javase/7/docs/api/  
http://hibernate.org/validator/documentation/getting-started/  

http://www.journaldev.com/721/java-annotations-tutorial-with-custom-annotation-example-and-parsing-using-reflection   
http://tutorials.jenkov.com/java-reflection/annotations.html    

It looks like OVal does something similar, but it is a lot bigger and does more stuff:   
http://oval.sourceforge.net/    

This is intended to be pretty small    

This works with the setters. I cannot get it to work with map-based constructors.

gradle -Dtest.single=FirstImmutableSampleTest test -info   
gradle -Dtest.single=SecondImmutableSampleTest test -info  
gradle -Dtest.single=BookTest test -info    
gradle -Dtest.single=ImmutableObject001Test test -info    
gradle -Dtest.single=ImmutableObject002Test test -info    
gradle -Dtest.single=BuilderTest001 test -info    

