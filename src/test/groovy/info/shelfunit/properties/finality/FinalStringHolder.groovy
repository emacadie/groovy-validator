package info.shelfunit.properties.finality

import validation.ValidString
import validation.ConvenientFinalFieldValidator

@ConvenientFinalFieldValidator
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

