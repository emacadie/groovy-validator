package info.shelfunit.properties.sample.divisor

import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.ImmutableValidator 

@ImmutableValidator
class ImmutableLongDivisor {
    
    @LongAnnotation( minValue = 2147483647L , divisor = [ 5L ] ) // was 10L
    long longWithDiv
    
    @LongAnnotation( divisor = [ 7L ] )
    long longWithDiv002
    
    @LongAnnotation( maxValue = 40L, divisor = [ 3L, 4L ] )
    long longWithDivArray
    
    @LongAnnotation( maxValue = 40L, divisor = [ 0L ] )
    long longWithZeroDiv
    
}

