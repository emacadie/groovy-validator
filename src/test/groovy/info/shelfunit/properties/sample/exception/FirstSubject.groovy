package info.shelfunit.properties.sample.exception

import validation.AnnotationProcessor
import validation.IntAnnotation

class FirstSubject {
    
    static { 
        // AnnotationProcessor.process( FirstSubject, true  ) 
    }
    
    @IntAnnotation( minValue = 30, maxValue = 400 )
    def firstNum // this will let it be null
    int secondNum
}


