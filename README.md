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

You can process the annotations by calling the static process method on info.shelfunit.properties.annotations.AnnotationProcessor:

```groovy
AnnotationProcessor.process( Book )
```

This project can also handle immutable objects. Instead of using AnnotationProcessor.process(), you annotate the class with  AstImmutableConstructor:

```groovy
package info.shelfunit.properties.sample.immutable
 
import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString
 
@ToString( includeNames = true )
@AstImmutableConstructor
@Immutable
class ImmutableObject002 {
    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
    @LongAnnotation( maxValue = 100L )
    long firstLong
}
```

To process the annotations, put your properties in a Map, and add a boolean called "validation" and set it to true:

```groovy
def validatingImObject = new ImmutableObject002( [ firstString: "Hi Again", firstInt: 11, firstLong: 22L ], true )
```




