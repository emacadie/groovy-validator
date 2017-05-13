package info.shelfunit.properties.nonmutable

import validation.ValidInt
import validation.ValidString

class SecondMutableSample {

    @ValidString( minLength = 5, maxLength = 200, throwEx = false )
    String firstString
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String secondString
    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    int firstInt
    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    int secondInt
    
    def String toString() {
        "firstString : ${firstString}, secondString: ${secondString}, firstInt: ${firstInt}, secondInt: ${secondInt}"
    }
   
} // SecondMutableSample 

