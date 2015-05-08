package info.shelfunit.properties.sample

import validation.LongAnnotation

class LongRunner {
    
    @LongAnnotation( minValue = 0L, maxValue = 1000L, throwEx = false )
    def firstNum
    @LongAnnotation( maxValue = 1000L, throwEx = false )
    def secondNum
    @LongAnnotation( minValue = 10L, throwEx = false )
    def thirdNum
}

