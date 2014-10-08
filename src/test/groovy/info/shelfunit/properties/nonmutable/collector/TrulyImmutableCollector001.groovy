package info.shelfunit.properties.nonmutable.collector

import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class TrulyImmutableCollector001 {

    @StringAnnotation( minLength = 5, maxLength = 200 )
    String firstString
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


