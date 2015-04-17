package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class SecondUSState {
    
    String name
    def setName( arg ) {
        println "No setter for you"
    }
    
    String capitalCity
    
    SecondUSState( argName, argCapCity ) {
        name = argName
        capitalCity = argCapCity
    }
    
}

