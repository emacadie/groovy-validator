package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.LongAnnotation

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

