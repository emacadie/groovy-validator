package info.shelfunit.properties.sample.divisor

import validation.ValidLong
import validation.ImmutableValidator 

@ImmutableValidator
class ImmutableLongDivisor {
    
    @ValidLong( minValue = 2147483647L , divisorSet = [ 5L ] ) // was 10L
    long longWithDiv
    
    @ValidLong( divisorSet = [ 7L ] )
    long longWithDiv002
    
    @ValidLong( maxValue = 40L, divisorSet = [ 3L, 4L ] )
    long longWithDivArray
    
    @ValidLong( maxValue = 40L, divisorSet = [ 0L ] )
    long longWithZeroDiv
    
}

