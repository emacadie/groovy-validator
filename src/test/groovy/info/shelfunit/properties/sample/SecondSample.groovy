package info.shelfunit.properties.sample

import info.shelfunit.properties.annotations.AnnotationProcessor
import info.shelfunit.properties.annotations.StringAnnotation

class SecondSample {
    
    int pages
    String title
    
    // no need to do this anymore!!
    def setPages( arg ) {
        if ( ( arg instanceof Integer ) && ( arg > 10 )  ) {
            pages = arg
        }
    }
}

