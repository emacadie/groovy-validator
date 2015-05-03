package info.shelfunit.properties.nonmutable

import validation.AnnotationProcessor
import validation.IntAnnotation
import validation.StringAnnotation

class SecondImmutableSample {
    /*
    static { 
        AnnotationProcessor.process( FirstImmutableSample.class ) 
    }
    */
    @StringAnnotation( minLength = 5, maxLength = 200, throwEx = false )
    String firstString
    @StringAnnotation( minLength = 5, maxLength = 20, throwEx = false )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
    
    def String toString() {
        "firstString : ${firstString}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
   
} // SecondImmutableSample 

