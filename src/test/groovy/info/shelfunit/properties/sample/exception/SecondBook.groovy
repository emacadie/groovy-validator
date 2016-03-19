package info.shelfunit.properties.sample.exception

import validation.ValidString
import groovy.transform.ToString

@ToString( includeNames = true )
class SecondBook {
    
    int pages
    @ValidString( minLength = 5, maxLength = 20 )
    String title
    int year
}

