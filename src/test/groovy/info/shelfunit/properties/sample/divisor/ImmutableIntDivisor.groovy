package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.ImmutableValidator 

@ImmutableValidator
class ImmutableIntDivisor {
    
    @IntAnnotation( minValue = 10 , divisor = [ 5 ] )
    int intWithDiv
    
    @IntAnnotation( divisor = [ 7 ] )
    int intWithDiv002
    
    @IntAnnotation( maxValue = 40, divisor = [ 3, 4 ] )
    int intWithDivArray
    
    @IntAnnotation( maxValue = 40, divisor = [ 0 ] )
    int intWithZeroDiv
}

