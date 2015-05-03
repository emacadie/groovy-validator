package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class SecondUSState {
    @Hidden
    String name
    
    @Hidden
    String abbrev
    /*
    def setName( arg ) {
        println "No setter for you"
    }
    */
    
    String capitalCity
    
    SecondUSState( argName, argCapCity, argAbbrev ) {
        name = argName
        capitalCity = argCapCity
        abbrev = argAbbrev
    }
    
}

