package info.shelfunit.properties.sample.divisor

import validation.LongAnnotation

class LongDivisor {
    
    @LongAnnotation( minValue = 10L, divisorSet = [ 5L ], throwEx = false )
    long longWithDiv
    
    @LongAnnotation( divisorSet = [ 7L ], throwEx = false )
    long longWithDiv002
    
    @LongAnnotation( maxValue = 40L, divisorSet = [ 3L, 4L ], throwEx = false )
    long longWithDivArray
    
    @LongAnnotation( maxValue = 40L, divisorSet = [ 0L ], throwEx = false )
    long longWithZeroDiv
}

