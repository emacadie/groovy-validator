package info.shelfunit.properties.sample

import validation.ValidInt

class FirstSubject {
    
    @ValidInt( minValue = 30, maxValue = 400, throwEx = false )
    def firstNum // this will let it be null
    int secondNum
    
    static { 
        info.shelfunit.properties.annotations.StaticAnnotationProcessor.process( FirstSubject.class ) 
    }
}
