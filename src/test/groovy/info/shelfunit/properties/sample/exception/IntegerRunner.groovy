package info.shelfunit.properties.sample.exception

// import validation.AnnotationProcessor
import validation.IntAnnotation

class IntegerRunner {
    
    static { 
        // AnnotationProcessor.process( IntegerRunner, true ) 
    }
    
    @IntAnnotation( minValue = 0, maxValue = 1000 )
    def numAsDef
    @IntAnnotation( minValue = 100, maxValue = 1000 )
    int numAsInt

}

