package info.shelfunit.properties.visibility

import visibility.Hidden
import groovy.transform.ToString 

@ToString( includeNames = true, includeFields = true )
class USState {
    
    @Hidden
    String name
    
    String capitalCity
    
    @Hidden
    def abbrev
    
    USState( argName, argCapCity, argAbbrev ) {
        name = argName
        capitalCity = argCapCity
        abbrev = argAbbrev
    }
    
}

