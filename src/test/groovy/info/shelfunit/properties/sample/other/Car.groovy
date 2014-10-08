package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import groovy.transform.TupleConstructor 
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.AstImmutableConstructor

@ToString( includeNames = true )
// @AstImmutableConstructor
@TupleConstructor
class Car {
    @IntAnnotation( minValue = 10 )
    int miles
    @IntAnnotation( minValue = 1990 )
    final int year
    
    Car( theYear ) { year = theYear }
}

