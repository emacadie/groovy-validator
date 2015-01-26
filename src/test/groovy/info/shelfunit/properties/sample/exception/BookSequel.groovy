package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.ToString
import info.shelfunit.properties.annotations.AnnotationProcessor


@ToString( includeNames = true )
class BookSequel {
    static {
        AnnotationProcessor.process( this, true )
    }
    @IntAnnotation( minValue = 0 )
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    @IntAnnotation( minValue = 1990 )
    int year
}

