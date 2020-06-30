package info.shelfunit.properties.sample.exception

import validation.ValidInt

class FirstSubject {
    
    @ValidInt( minValue = 30, maxValue = 400 )
    def firstNum // this will let it be null
    int secondNum
    
    static { 
        info.shelfunit.properties.annotations.StaticAnnotationProcessor.process( FirstSubject.class, true ) 
    }
}
