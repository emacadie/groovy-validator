package info.shelfunit.properties.sample.exception

import validation.AnnotationProcessor
import validation.StringAnnotation
import groovy.transform.ToString


@ToString( includeNames = true )
class SecondBook {
    
    static { 
        // AnnotationProcessor.process( SecondBook, true ) 
    }
    
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    int year
}

