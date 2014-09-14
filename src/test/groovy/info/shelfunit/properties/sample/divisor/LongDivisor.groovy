package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.LongAnnotation

class LongDivisor {
    
    @LongAnnotation( minValue = 10L, divisorSet = [ 5L ] )
    long longWithDiv
    
    @LongAnnotation( divisorSet = [ 7L ] )
    long longWithDiv002
    
    @LongAnnotation( maxValue = 40L, divisorSet = [ 3L, 4L ] )
    long longWithDivArray
    
    @LongAnnotation( maxValue = 40L, divisorSet = [ 0L ] )
    long longWithZeroDiv
}

