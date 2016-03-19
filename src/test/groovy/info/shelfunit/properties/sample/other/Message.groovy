package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.DefaultStrategy
import validation.ValidString

@ToString( includeNames = true )
@Builder( builderStrategy = DefaultStrategy )
class Message {
    @ValidString( minLength = 5, maxLength = 20, throwEx = false  )
    String from
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String to
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String subject
    @ValidString( minLength = 5, maxLength = 20, throwEx = false )
    String body
}

