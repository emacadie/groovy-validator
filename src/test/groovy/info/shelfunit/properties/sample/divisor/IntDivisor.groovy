package info.shelfunit.properties.sample.divisor

import validation.ValidInt
import groovy.transform.ToString

@ToString( includeNames = true )
class IntDivisor {
    
    @ValidInt( minValue = 10, divisorSet = [ 5 ], throwEx = false )
    int intWithDiv
    
    @ValidInt( divisorSet = [ 7 ], throwEx = false )
    int intWithDiv002
    
    @ValidInt( maxValue = 40, divisorSet = [ 3, 4 ], throwEx = false )
    int intWithDivArray
    
    @ValidInt( maxValue = 40, divisorSet = [ 0 ], throwEx = false )
    int intWithZeroDiv
    
}

