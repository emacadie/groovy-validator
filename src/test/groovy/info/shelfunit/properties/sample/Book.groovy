package info.shelfunit.properties.sample

<<<<<<< HEAD
// import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.StringAnnotation
=======
import validation.AnnotationProcessor
import validation.StringAnnotation
>>>>>>> working
import groovy.transform.ToString


@ToString( includeNames = true )
class Book {
    
    static { 
        // AnnotationProcessor.process( Book ) 
    }
    
    int pages
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String title
    int year
}

