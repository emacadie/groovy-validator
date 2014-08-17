package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.IntAnnotation

class IntegerRunner {
    
    static { 
        AnnotationProcessor.process( IntegerRunner.class ) 
    }
    
    @IntAnnotation( minValue = 0, maxValue = 1000 )
    def numAsDef
    @IntAnnotation( minValue = 100, maxValue = 1000 )
    int numAsInt

}

