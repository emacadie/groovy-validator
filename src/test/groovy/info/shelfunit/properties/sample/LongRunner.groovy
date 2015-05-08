package info.shelfunit.properties.sample

// import validation.AnnotationProcessor
import validation.LongAnnotation

class LongRunner {
    
    static { 
        // AnnotationProcessor.process( LongRunner.class ) 
    }
    
    @LongAnnotation( minValue = 0L, maxValue = 1000L, throwEx = false )
    def firstNum
    @LongAnnotation( maxValue = 1000L, throwEx = false )
    def secondNum
    @LongAnnotation( minValue = 10L, throwEx = false )
    def thirdNum
}

