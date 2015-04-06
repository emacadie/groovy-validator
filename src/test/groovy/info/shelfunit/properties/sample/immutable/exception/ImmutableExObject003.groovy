package info.shelfunit.properties.sample.immutable.exception

import validation.AstImmutableConstructor
import validation.DoubleAnnotation
import validation.FloatAnnotation
import validation.IntAnnotation
import validation.LongAnnotation
import validation.StringAnnotation
import groovy.transform.Immutable
import groovy.transform.ToString

@ToString( includeNames = true )
@Immutable
@AstImmutableConstructor
class ImmutableExObject003 {
    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @StringAnnotation( maxLength = 15 )
    String secondString
    @DoubleAnnotation( minValue = 10d, maxValue = 100d )
    double firstDouble
    @FloatAnnotation( minValue = 10f, maxValue = 100f )
    float firstFloat
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
    @LongAnnotation( maxValue = 100L )
    long firstLong
}

