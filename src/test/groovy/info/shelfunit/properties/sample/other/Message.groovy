package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.DefaultStrategy
import info.shelfunit.properties.annotations.StringAnnotation

@ToString( includeNames = true )
@Builder( builderStrategy = DefaultStrategy )
class Message {
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String from
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String to
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String subject
    @StringAnnotation( minLength = 5, maxLength = 20 )
    String body
}

