package info.shelfunit.properties.sample.exception

import validation.AnnotationProcessor
import validation.LongAnnotation

class LongExRunner {
    
    static { 
        AnnotationProcessor.process( LongExRunner, true ) 
    }
    
    @LongAnnotation( minValue = 0L, maxValue = 1000L )
    def firstNum
    @LongAnnotation( maxValue = 1000L )
    def secondNum
    @LongAnnotation( minValue = 2147483647L ) // was 10
    def thirdNum
}

