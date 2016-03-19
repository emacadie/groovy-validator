package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.ValidString
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalStringHolder {
    @ValidString( minLength = 5, maxLength = 20, regEx = /^.*ee.*$/, throwEx = true )
    def firstDefString
    @ValidString( minLength = 5, maxLength = 20, regEx = /^.*?oo.*$/ )
    final def finalDefString
    @ValidString( minLength = 5, maxLength = 10, throwEx = true )
    String firstRealString
    @ValidString( minLength = 5, maxLength = 30, regEx = /^.*?aa.*$/ )
    final String finalRealString
    
    String someOtherString
    def anotherObject
    
}

