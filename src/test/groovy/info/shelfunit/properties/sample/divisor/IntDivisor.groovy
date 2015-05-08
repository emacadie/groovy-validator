package info.shelfunit.properties.sample.divisor

import validation.IntAnnotation
import groovy.transform.ToString

@ToString( includeNames = true )
class IntDivisor {
    
    @IntAnnotation( minValue = 10, divisorSet = [ 5 ], throwEx = false )
    int intWithDiv
    
    @IntAnnotation( divisorSet = [ 7 ], throwEx = false )
    int intWithDiv002
    
    @IntAnnotation( maxValue = 40, divisorSet = [ 3, 4 ], throwEx = false )
    int intWithDivArray
    
    @IntAnnotation( maxValue = 40, divisorSet = [ 0 ], throwEx = false )
    int intWithZeroDiv
    
}

