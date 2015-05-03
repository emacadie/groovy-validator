package info.shelfunit.properties.sample

import validation.AnnotationProcessor
import validation.StringAnnotation

import groovy.transform.ToString

@ToString( includeNames = true )
class Book {
    
    static {
        println "In the static block"
        // AnnotationProcessor.process( Book ) 
    }
    
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    int year
}

