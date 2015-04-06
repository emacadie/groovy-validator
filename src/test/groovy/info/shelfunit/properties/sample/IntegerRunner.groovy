package info.shelfunit.properties.sample

import validation.AnnotationProcessor
import validation.IntAnnotation

class IntegerRunner {
    
    static { 
        AnnotationProcessor.process( IntegerRunner.class ) 
    }
    
    @IntAnnotation( minValue = 0, maxValue = 1000 )
    def numAsDef
    @IntAnnotation( minValue = 100, maxValue = 1000 )
    int numAsInt

}

