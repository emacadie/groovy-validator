package info.shelfunit.properties.annotations

import groovy.transform.AnnotationCollector
import groovy.transform.Immutable
import groovy.transform.ToString


@ToString( includeNames = true )
@Immutable
@AstImmutableConstructor
@AnnotationCollector
public @interface ImmutableValidator {}

