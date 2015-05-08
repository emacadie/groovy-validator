package info.shelfunit.properties.sample.exception

import validation.IntAnnotation

class FirstSubject {
    
    @IntAnnotation( minValue = 30, maxValue = 400 )
    def firstNum // this will let it be null
    int secondNum
}


