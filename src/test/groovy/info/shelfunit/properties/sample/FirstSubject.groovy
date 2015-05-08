package info.shelfunit.properties.sample

import validation.IntAnnotation

class FirstSubject {
    
    @IntAnnotation( minValue = 30, maxValue = 400, throwEx = false )
    def firstNum // this will let it be null
    int secondNum
}


