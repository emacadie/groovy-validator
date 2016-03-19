package info.shelfunit.properties.sample.immutable

import validation.ImmutableValidator
import validation.ValidInt
import validation.ValidString

@ImmutableValidator
class ImmutableObjectColl001 {
    @ValidString( minLength = 5, maxLength = 10 )
    String firstString
    @ValidInt( minValue = 10, maxValue = 100 )
    int firstInt
}

