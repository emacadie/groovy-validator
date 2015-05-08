package info.shelfunit.properties.sample

// import validation.AnnotationProcessor
import validation.DoubleAnnotation

class DoubleRunner {
    
    static { 
        // AnnotationProcessor.process( DoubleRunner.class ) 
    }
    
    @DoubleAnnotation( minValue = 0d, maxValue = 1000d, throwEx = false )
    def firstNum
    @DoubleAnnotation( maxValue = 1000d, throwEx = false )
    def secondNum
    @DoubleAnnotation( minValue = 10d, throwEx = false )
    def thirdNum
    
}

