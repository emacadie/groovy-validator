package info.shelfunit.properties.nonmutable

import validation.AnnotationProcessor
import validation.IntAnnotation
import validation.StringAnnotation
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class FirstImmutableSample {
    /*
    static { 
        AnnotationProcessor.process( FirstImmutableSample.class ) 
    }
    */
    @StringAnnotation( minLength = 5, maxLength = 200 )
    String first // String
    // @StringAnnotation( minLength = 5, maxLength = 20 )
    // String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    def secondInt
    
    def String toString() {
        // "firstString : ${first}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
   
} // FirstImmutableSample 


