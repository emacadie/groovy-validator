package info.shelfunit.properties.sample.immutable.exception

import validation.AstImmutableConstructor
import validation.IntAnnotation
import validation.StringAnnotation
import groovy.transform.Immutable

@Immutable
@AstImmutableConstructor
class ImmutableExObject001 {
    // @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
}

