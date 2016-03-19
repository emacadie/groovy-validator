package info.shelfunit.properties.sample

import validation.ValidInt
import validation.ValidString
import groovy.transform.ToString

@ToString( includeNames = true )
class BookSequel {
    
    @ValidInt( minValue = 0 )
    int pages
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String title
    @ValidInt( minValue = 1990 )
    int year
}

