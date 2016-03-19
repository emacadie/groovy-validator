package info.shelfunit.properties.sample.immutable

import validation.AstImmutableConstructor
import validation.ValidInt
import validation.ValidString
import groovy.transform.Immutable

@Immutable
@AstImmutableConstructor
class ImmutableObject001 {
    @ValidString( minLength = 5, maxLength = 10 )
    String firstString
    @ValidInt( minValue = 10, maxValue = 100 )
    int firstInt
}

