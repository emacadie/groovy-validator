package info.shelfunit.properties.sample.divisor

import validation.ValidLong

class LongDivisor {
    
    @ValidLong( minValue = 10L, divisorSet = [ 5L ], throwEx = false )
    long longWithDiv
    
    @ValidLong( divisorSet = [ 7L ], throwEx = false )
    long longWithDiv002
    
    @ValidLong( maxValue = 40L, divisorSet = [ 3L, 4L ], throwEx = false )
    long longWithDivArray
    
    @ValidLong( maxValue = 40L, divisorSet = [ 0L ], throwEx = false )
    long longWithZeroDiv
}

