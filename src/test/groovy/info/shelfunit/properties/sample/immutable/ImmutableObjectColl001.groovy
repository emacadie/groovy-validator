package info.shelfunit.properties.sample.immutable

import validation.ImmutableValidator
import validation.IntAnnotation
import validation.StringAnnotation

@ImmutableValidator
class ImmutableObjectColl001 {
    // @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
}

