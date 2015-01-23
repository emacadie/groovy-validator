package info.shelfunit.properties.nonmutable

import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

class SecondImmutableExSample {

    // @StringAnnotation( minLength = 5, maxLength = 200 )
    String firstString
    // @StringAnnotation( minLength = 5, maxLength = 20 )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
    
    def String toString() {
        "firstString : ${firstString}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
   
} // SecondImmutableSample 

