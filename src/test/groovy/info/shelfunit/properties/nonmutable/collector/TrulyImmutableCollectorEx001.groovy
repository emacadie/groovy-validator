package info.shelfunit.properties.nonmutable.collector

import validation.ImmutableValidator
import validation.IntAnnotation
import validation.StringAnnotation

@ImmutableValidator
class TrulyImmutableCollectorEx001 {

    // @StringAnnotation( minLength = 5, maxLength = 200 )
    String firstString
    // @StringAnnotation( minLength = 5, maxLength = 20 )
    String secondString
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int secondInt
   
} // TrulyImmutable001


