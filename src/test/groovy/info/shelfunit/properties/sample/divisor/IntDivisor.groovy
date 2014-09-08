package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.IntAnnotation

class IntDivisor {
    
    @IntAnnotation( minValue = 10, divisor = 5 )
    int intWithDiv
    
    @IntAnnotation( divisor = 7 )
    int intWithDiv002
    
    @IntAnnotation( maxValue = 40, divisor = [ 3, 4 ] )
    int intWithDivArray
    
}

