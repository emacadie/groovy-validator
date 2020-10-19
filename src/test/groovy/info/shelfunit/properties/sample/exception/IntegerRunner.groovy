package info.shelfunit.properties.sample.exception

import validation.ValidInt

class IntegerRunner {
    
    @ValidInt( minValue = 0, maxValue = 1000 )
    def numAsDef
    @ValidInt( minValue = 100, maxValue = 1000 )
    int numAsInt
    
    static { 
        // info.shelfunit.properties.annotations.StaticAnnotationProcessor.process( IntegerRunner.class, true )
    }

}

