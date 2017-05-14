package info.shelfunit.properties.finality

import validation.ConvenientFinalFieldValidator 
import validation.ValidInt

@ConvenientFinalFieldValidator 
class Car {
    
    @ValidInt( minValue = 1990 )
    final def year
    @ValidInt( minValue = 10, throwEx = false )
    int miles
}

