package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable

@Immutable
@AstImmutableConstructor
class ImmutableObject001 {
    @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
}

