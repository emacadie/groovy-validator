package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.AstImmutableConstructor
import groovy.transform.TupleConstructor

@TupleConstructor
@ToString( includeNames = true )
// @AstImmutableConstructor
class Car {
    @IntAnnotation( minValue = 0 )
    int miles
    @IntAnnotation( minValue = 1990 )
    final int year
}

