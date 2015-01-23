package info.shelfunit.properties.sample.immutable.exception

import info.shelfunit.properties.annotations.AstImmutableConstructor
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation
import groovy.transform.Immutable

@Immutable
@AstImmutableConstructor
class ImmutableExObject001 {
    // @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
}

