package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.LongAnnotation

class LongDivisor {
    
    @LongAnnotation( minValue = 10L, divisor = [ 5L ] )
    long longWithDiv
    
    @LongAnnotation( divisor = [ 7L ] )
    long longWithDiv002
    
    @LongAnnotation( maxValue = 40L, divisor = [ 3L, 4L ] )
    long longWithDivArray
    
    @LongAnnotation( maxValue = 40L, divisor = [ 0L ] )
    long longWithZeroDiv
    
}

