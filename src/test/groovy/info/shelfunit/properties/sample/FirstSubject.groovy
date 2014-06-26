package info.shelfunit.properties.sample

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.IntAnnotation

class FirstSubject {
    
    static { 
        AnnotationProcessor.process( FirstSubject.class ) 
    }
    
    @IntAnnotation( minValue = 30, maxValue = 400 )
    def firstNum // this will let it be null
    int secondNum
}


