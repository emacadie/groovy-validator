package info.shelfunit.properties.sample.immutable.exception

import validation.ImmutableValidator
import validation.DoubleAnnotation
import validation.FloatAnnotation
import validation.IntAnnotation
import validation.LongAnnotation
import validation.StringAnnotation

@ImmutableValidator
class ImmutableExObjectColl002 {
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

