package info.shelfunit.properties.sample.divisor

import validation.ValidInt
import validation.ImmutableValidator 

@ImmutableValidator
class ImmutableIntDivisor {
    
    @ValidInt( minValue = 10 , divisorSet = [ 5 ] )
    int intWithDiv
    
    @ValidInt( divisorSet = [ 7 ] )
    int intWithDiv002
    
    @ValidInt( maxValue = 40, divisorSet = [ 3, 4 ] )
    int intWithDivArray
    
    @ValidInt( maxValue = 40, divisorSet = [ 0 ] )
    int intWithZeroDiv
}

