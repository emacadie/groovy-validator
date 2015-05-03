package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.DefaultStrategy
import validation.StringAnnotation

@ToString( includeNames = true )
@Builder( builderStrategy = DefaultStrategy )
class Message {
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false  )
    String from
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String to
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String subject
    @StringAnnotation( minLength = 5, maxLength = 20 ) // , throwEx = false )
    String body
}

