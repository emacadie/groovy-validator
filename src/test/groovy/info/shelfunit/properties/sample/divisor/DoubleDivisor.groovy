package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.DoubleAnnotation

class DoubleDivisor {
    
    @DoubleAnnotation( minValue = 10.0d, divisor = 5.0d )
    double dWithDiv
    
    @DoubleAnnotation( divisor = [ 7.0d ] )
    double dWithDiv002
    
}
