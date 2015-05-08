package info.shelfunit.properties.sample.exception

import validation.StringAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class SecondBook {
    
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    int year
}

