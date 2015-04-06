package info.shelfunit.properties.sample.exception

import validator.IntAnnotation
import validator.StringAnnotation
import groovy.transform.ToString


@ToString( includeNames = true )
class SecondBookSequel {
    
    @IntAnnotation( minValue = 0 )
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    @IntAnnotation( minValue = 1990 )
    int year
}

