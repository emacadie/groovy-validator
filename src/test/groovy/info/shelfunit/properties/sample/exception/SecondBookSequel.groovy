package info.shelfunit.properties.sample.exception

import validation.ValidInt
import validation.ValidString
import groovy.transform.ToString

@ToString( includeNames = true )
class SecondBookSequel {
    @ValidInt( minValue = 0 )
    int pages
    @ValidString( minLength = 5, maxLength = 20 )
    String title
    @ValidInt( minValue = 1990 )
    int year
}

