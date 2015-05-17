package info.shelfunit.properties.finality

import groovy.transform.ToString
import validation.StringAnnotation
import validation.FinalFieldValidator

@ToString( includeNames = true )
@FinalFieldValidator
class FinalStringHolder {
    @StringAnnotation( minLength = 5, maxLength = 20, regEx = /^.*ee.*$/, throwEx = true )
    def firstDefString
    @StringAnnotation( minLength = 5, maxLength = 20, regEx = /^.*?oo.*$/ )
    final def finalDefString
    @StringAnnotation( minLength = 5, maxLength = 10, throwEx = true )
    String firstRealString
    @StringAnnotation( minLength = 5, maxLength = 30, regEx = /^.*?aa.*$/ )
    final String finalRealString
    
    String someOtherString
    
}

