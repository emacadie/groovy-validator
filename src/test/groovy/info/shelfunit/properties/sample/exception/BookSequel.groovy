package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class BookSequel {

    @IntAnnotation( minValue = 0 )
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    @IntAnnotation( minValue = 1990 )
    int year
}

