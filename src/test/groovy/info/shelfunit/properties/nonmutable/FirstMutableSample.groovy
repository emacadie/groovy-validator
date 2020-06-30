package info.shelfunit.properties.nonmutable

import validation.ValidInt
import validation.ValidString
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class FirstMutableSample {

    @ValidString( minLength = 5, maxLength = 200 , throwEx = false )
    String firstString
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String secondString
    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    int firstInt
    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    def secondInt
    
    def String toString() {
        // "firstString : ${first}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
    
    static { 
        info.shelfunit.properties.annotations.StaticAnnotationProcessor.process( FirstMutableSample.class ) 
    }
   
} // FirstMutableSample 


