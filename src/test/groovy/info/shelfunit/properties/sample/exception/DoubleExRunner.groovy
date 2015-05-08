package info.shelfunit.properties.sample.exception

// import validation.AnnotationProcessor
import validation.DoubleAnnotation

class DoubleExRunner {
    
    static { 
        // AnnotationProcessor.process( DoubleExRunner, true ) 
    }
    
    @DoubleAnnotation( minValue = 0d, maxValue = 1000d )
    def firstNum
    @DoubleAnnotation( maxValue = 1000d )
    def secondNum
    @DoubleAnnotation( minValue = 10d )
    def thirdNum
    
}

