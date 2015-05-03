package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class SecondUSState {
    @Hidden
    String name
    
    String capitalCity
    
    SecondUSState( argName, argCapCity ) {
        name = argName
        capitalCity = argCapCity
    }
    
}

