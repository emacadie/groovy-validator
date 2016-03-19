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

It's clean, and has no getters and setters. But what I do not like is there is no validation for your data. What if you want your String to be between 10 and 20 characters? What if you want your int field to be more than 100? And what's to stop some dingo from trying to create a book object with less than 0 pages?

So I made some annotations that can do some validation for you.   

```groovy
package info.shelfunit.properties.sample
 
import validation.ValidInt
import validation.ValidString
 
class Book {

    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    def pages
    @ValidString( minLength = 5, maxLength = 20, regEx = /^.*?[Gg]roovy.*$/  )
    String title
    int year
}
```

For POGOs, if a numeric field is declared as "def", it will become null if the argument does not meet the validation constraints. If it is declared as a primitive, it will be set to 0 if the argument does not meet the validation constraints.

This project can also validate fields in immutable objects. In addition to using the annotations for the fields, you annotate the class with ImmutableValidator:

```groovy
package info.shelfunit.properties.sample.immutable
 
import validation.ImmutableValidator
import validation.ValidInt
import validation.ValidLong
import validation.ValidString
 
@ImmutableValidator
class ImmutableObject002 {
    @ValidString( minLength = 5, maxLength = 10 )
    String firstString
    @ValidInt( minValue = 10, maxValue = 100 )
    int firstInt
    @ValidLong( maxValue = 100L, divisorSet = [ 5L, 7L ] )
    long firstLong
}
```

To process the annotations, put your properties in a Map, and add a boolean called "validation" and set it to true (since I couldn't overload the Map constructor, I added a boolean):

```groovy
def validatingImObject = new ImmutableObject002( 
    [ firstString: "Hi Again", firstInt: 11, firstLong: 22L ], true )
```

Adding the "throwEx" will throw an exception if the arguments do not meet the validation constraints. It is optional and is set to false by default. If an exception is thrown, it will print out the value and the constraints.   

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

If "throwException" is true for an immutable object and an exception is thrown, then the object will not be created.

This library can also handle final fields in mutable objects.
```groovy
import groovy.transform.ToString
import validation.ValidInt
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class Car {
    @ValidInt( minValue = 10, throwEx = false )
    int miles
    @ValidInt( minValue = 1990 )
    final int year
}
```

As with immutable validation, you need to use a map in the constructor to validate a final field.
```groovy
def car = new Car( [ miles: 50, year: 2007 ], true, true )
```

Right now it only handles String, double, float, int and long. For String, it checks the string is checked against a minimum ("minLength") and maximum ("maxLength") length, and against a regular expression ("regEx"). For integers and longs, the field is checked against minimum ("minValue") and maximum ("maxValue") values, and a set of divisors ("divisorSet"). For double and float, the field is checked against minimum ("minValue") and maximum ("maxValue") values. There are defaults for all of these.  

To use this project: 
Run 
```
gradle distZip
```
and use build/libs/groovy-validator.jar in your project.  




