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

It's clean, and has no getters and setters. But what I do not like is there is no validation for your data. What if you want your String to be between 10 and 20 characters? What if you want your int field to be more than 100? 

So I made some annotations that can do some validation for you.   

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

You can process the annotations by calling the static process method on info.shelfunit.properties.annotations.AnnotationProcessor, sending your POGO class as an argument:

```groovy
AnnotationProcessor.process( Book.class )
```

For POGOs, if a numeric field is declared as "def", it will become null if the argument does not meet the validation constraints. If it is declared as a primitive, it will be set to 0 if the argument does not meet the validation constraints.

This project can also handle immutable objects. Instead of using AnnotationProcessor.process(), you annotate the class with  ImmutableValidator:

```groovy
package info.shelfunit.properties.sample.immutable
 
import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
 
@ImmutableValidator
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
def validatingImObject = new ImmutableObject002( 
    [ firstString: "Hi Again", firstInt: 11, firstLong: 22L ], true )
```

You can add another boolean called "throwException" to throw an exception if the arguments do not meet the validation constraints. It is optional and is set to false by default. If an exception is thrown, it will print out the value and the constraints.   

You can use it for POGOs with the AnnotationProcessor class like this:

```groovy
AnnotationProcessor.process( Book, true )
```

You might get a message like this:
```
"Hey" is a String with a length outside the range of 5 and 10 or does not match the regular expression ".*"
```

You can also use it with immutable objects annotated with the ImmutableValidator annotation. This would be a second boolean after the Map with your properties, since the first boolean controls validation:

```groovy
def thirdImObject = new ImmutableObject002( 
[ firstString: "Hi Once Again", firstInt: 1234567, firstLong: 222L ], 
true, true )
```

In that case, you get a message with a line for each field. So you might get a message like this:

```
Groovy validation exception: 
"eeeeeeeeeee" is a String with a length outside the range of 5 to 10 characters or does not match the regular expression ".*" 
1234567 is an integer outside the range 10 to 100 or it is not divisible by anything in the set [1] 
222 is a long outside the range 0 to 100 or it is not divisible by anything in the set [5, 7] 
```

If "thowException" is true for a POGO, the field will either retains its pre-existing value (if it had one) or be set to null. If "throwException" is true for an immutable object, the object will not be created.

Right now it only handles String, double, float, int and long. For String, it checks the string is checked against a minimum ("minLength") and maximum ("maxLength") length, and against a regular expression ("regEx"). For integers and longs, the field is checked against minimum ("minValue") and maximum ("maxValue") values, and a set of divisors ("divisorSet"). For double and float, the field is checked against minimum ("minValue") and maximum ("maxValue") values. There are defaults for all of these.  

AnnotationProcessor can handle mutable fields in POGOs, and ImmutableValidator can handle fields in immutable objects. So far this project cannot handle final fields in POGOs. You can put a final field in a POGO, you just cannot use these annotations for that field. You also need to add a constructor for the final field.

To use this project: 
Run 
```
gradle distZip
```
and use build/libs/groovy-validator.jar in your project.



