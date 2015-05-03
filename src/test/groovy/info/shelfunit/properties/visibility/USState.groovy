package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class USState {
    
    @Hidden
    String name
    
    String capitalCity
    
    USState( argName, argCapCity ) {
        name = argName
        capitalCity = argCapCity
    }
    
}

