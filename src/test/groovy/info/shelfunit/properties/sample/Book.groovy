package info.shelfunit.properties.sample

import validation.ValidString
import groovy.transform.ToString

@ToString( includeNames = true )
class Book {
    
    int pages
    @ValidString( minLength = 5, maxLength = 20 )
    String title
    int year
}

