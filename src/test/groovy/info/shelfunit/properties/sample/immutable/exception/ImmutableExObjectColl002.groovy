package info.shelfunit.properties.sample.immutable.exception

import validation.ImmutableValidator
import validation.ValidDouble
import validation.ValidFloat
import validation.ValidInt
import validation.ValidLong
import validation.ValidString

@ImmutableValidator
class ImmutableExObjectColl002 {
    @ValidString( minLength = 5, maxLength = 10 )
    String firstString
    @ValidString( maxLength = 15 )
    String secondString
    @ValidDouble( minValue = 10d, maxValue = 100d )
    double firstDouble
    @ValidFloat( minValue = 10f, maxValue = 100f )
    float firstFloat
    @ValidInt( minValue = 10, maxValue = 100 )
    int firstInt
    @ValidLong( maxValue = 100L )
    long firstLong
}

