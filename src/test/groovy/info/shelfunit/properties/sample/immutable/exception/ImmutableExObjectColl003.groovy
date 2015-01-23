package info.shelfunit.properties.sample.immutable.exception

import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.DoubleAnnotation
import info.shelfunit.properties.annotations.FloatAnnotation
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.LongAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class ImmutableExObjectColl003 {
    // @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    // @StringAnnotation( maxLength = 15 )
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

