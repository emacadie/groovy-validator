package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.ImmutableValidator 

@ImmutableValidator
class ImmutableLongDivisor {
    
    @LongAnnotation( minValue = 10L , divisor = [ 5L ] )
    long longWithDiv
    
    @LongAnnotation( divisor = [ 7L ] )
    long longWithDiv002
    
    @LongAnnotation( maxValue = 40L, divisor = [ 3L, 4L ] )
    long longWithDivArray
    
}

