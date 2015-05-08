package info.shelfunit.properties.nonmutable.exception

import validation.IntAnnotation
import validation.StringAnnotation

class FirstImmutableExSample {

    @StringAnnotation( minLength = 5, maxLength = 200, throwEx = false )
    String firstString
    @StringAnnotation( minLength = 5, maxLength = 20, throwEx = false )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400, throwEx = false )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400, throwEx = false )
    def secondInt
    
    def String toString() {
        "firstString : ${firstString}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
   
} // FirstImmutableSample 


