package info.shelfunit.properties.sample

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

