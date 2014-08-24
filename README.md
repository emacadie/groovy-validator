This project has a few annotations that validate fields in POGOs, sort of like Grails constraints.   

I will attempt to make some annotations for properties in Groovy.    

Here is a POGO:  

```groovy
package info.shelfunit.properties.sample
 
class Book {
     
    int pages
    String title
    int year
}
```

It's clean, and has no getters and setters. But what I do not like is there is no validation for your data. So I made some annotations that can do some validation for you.   

```groovy
package info.shelfunit.properties.sample
 
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
 
class Book {
     
    @IntAnnotation( minValue = 30, maxValue = 400 )
    def pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    int year
}
```








