package info.shelfunit.properties.sample.exception

import validation.ValidString

class SampleRunner {
   /*
    def doMovieStuff001() {
        def m1 = new Movie()
        m1.title = "Star Trek"
        m1.year = 2009
        println "movie 1 is ${m1.title} which came out in ${m1.year}"

        def m2 = new Movie()
        m2.title = "Jaws"
        m2.year = 1977
        println "movie 1 is ${m2.title} which came out in ${m2.year}"

        def m3 = new Movie()
        m3.title = "Gone With The Wind"
        m3.year = 1939

        println "movie 1 is ${m3.title} which came out in ${m3.year}"

    } // def doMovieStuff001()
    */
    
    def seeSecondBookMethods() {
        println "\n\nStarting seeSecondBookMethods"
        def bmc = SecondBook.metaClass
        bmc.methods.each {
            print "name: ${it.getName()}, "
        }
        print "\n\n"
        println "Now properties"
        bmc.properties.each {
            print "name: ${it.getName()}, "
        }
        print "\n\n"
        println "Now get meta methods"
        bmc.metaMethods.each {
            print "name: ${it.getName()}, "
        }
        print "\n\n"
        println "Ending seeSecondBookMethods"
    } // seeSecondBookMethods
   

    def doStuff001() {
        // def b = new SecondBook()
        def bmc = SecondBook.metaClass
       
        def bFieldNames = [] // = SecondBook.class.getDeclaredFields().each{it.getName()}
       
        def bFields = SecondBook.class.getDeclaredFields()
        println "Fields of book:"
        bFields.each {
            print "${it.getName()} "
            bFieldNames << it.getName() 
        }
        println " "
        println "here comes bFieldNames"
        bFieldNames.each { print "${it} "  }
        println " \n"

        println " "
        /*
        bmc.properties.each {
            print "${it.getName()} "
            if ( bFieldNames.contains( it.getName() ) ) {
                def field = SecondBook.class.getDeclaredField( it.getName() )
                def stringAnnotation = field?.getAnnotation( ValidString.class )
                if ( stringAnnotation ) {
                    print "${it.getName()} has ValidString"
                    // intercept the setter
                    print " stringAnnotation.min(): ${stringAnnotation.min()} "
                    print " stringAnnotation.max(): ${stringAnnotation.max()} "
                    def mName = "set${it.getName().capitalize()}"
                    print " method would be ${mName} "
                    SecondBook.metaClass.invokeMethod = { String name, args ->
                        println "Method name in invokeMethod is ${name}"
                        if ( name == mName ) {
                            println "Looking at SecondBook.set${it.getName().capitalize()}"
                            if ( !( args[ 0 ].length() > stringAnnotation.min() ) ||
                                !( args[ 0 ].length() < stringAnnotation.max() ) ) {
                                SecondBook.metaClass.invokeMissingMethod(delegate, name, args)
                                SecondBook.metaClass.getMetaMethod("set${it.getName().capitalize()}").invoke(delegate, args)
                            } else {
                                println "Cannot call SecondBook.set${it.getName().capitalize()}"
                            }
                        }
                    }
                } else {
                    print  "no string annotation"
                }
            }
            print "\n"
        }
        */
       
       /*
        SecondBook.metaClass.invokeMethod = { String name, args ->
            println "Method name in standalone invokeMethod is ${name}"
            if ( name == 'setTitle' ) {
                println "Looking at SecondBook.setTitle"
                if ( !( args[ 0 ].length() > stringAnnotation.min() ) ||
                    !( args[ 0 ].length() < stringAnnotation.max() ) ) {
                    SecondBook.metaClass.invokeMissingMethod(delegate, name, args)
                    SecondBook.metaClass.getMetaMethod('setTitle').invoke(delegate, args)
                } else {
                    println "Cannot call SecondBook.setTitle"
                }
            }
        }
        */

        SecondBook.metaClass.setProperty = { String name, arg ->
            println "name in setProperty is ${name}"
            def field = SecondBook.class.getDeclaredField( name )
            def stringAnnotation = field?.getAnnotation( ValidString.class )
            println "-- Here is stringAnnotation: ${stringAnnotation}"
            if ( stringAnnotation ) {
                println "Looking at SecondBook.set${name.capitalize()}"
                if ( !( arg.length() < stringAnnotation.min() ) &&
                    !( arg.length() > stringAnnotation.max() ) ) {
                    SecondBook.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                } else {
                    println "Cannot call SecondBook.setTitle"
                }
            } else {
                def mName = "set${name.capitalize()}"
                def propClassName = SecondBook.class.metaClass.getMetaProperty( name ).getType().getName()
                println "LET'S TRY CALLING ${mName}, it's a ${propClassName}"
                SecondBook.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
            }
        }

        println " \n========"
        def bTest1 = new SecondBook()
       
        println "bTest1.title: ${bTest1.title} "
        bTest1.title = "abcdefg"
       
        println "bTest1.title: ${bTest1.title} "
        bTest1.title = "qw"
        println "bTest1.title: ${bTest1.title} "
        bTest1.title = "qwertyuiopasdfghjklzxcvbnm"
        println "bTest1.title: ${bTest1.title} "
       
        bTest1.year = 2010
        println "bTest1.year: ${bTest1.year} "
        bTest1.year = 2014
        println "bTest1.year: ${bTest1.year} "
    } // def doStuff001()
    
    def tryFirstSubject() {
        println "\n\n Starting tryFirstSubject"

        def fs001 = new FirstSubject()
        println "1. Just called constructor for fs001"
        def fs002 = new FirstSubject()
        // @ValidInt(minValue=30, maxValue=400)
        // int firstNum
        println "1. fs001.secondNum is ${fs001.secondNum}, fs001.firstNum is ${fs001.firstNum}"
        fs001.firstNum = 100
        println "2. fs001.secondNum is ${fs001.secondNum}, fs001.firstNum is ${fs001.firstNum}"
        fs001.firstNum = 10
        println "3. fs001.secondNum is ${fs001.secondNum}, fs001.firstNum is ${fs001.firstNum}"
        fs001.firstNum = 10000
        println "4. fs001.secondNum is ${fs001.secondNum}, fs001.firstNum is ${fs001.firstNum}"
        fs001.firstNum = "qwertyuiopasdfg"
        println "5. fs001.secondNum is ${fs001.secondNum}, fs001.firstNum is ${fs001.firstNum}"

        println "Instantiating with map, setting firstNum to 40"
        def fsWithMap = new FirstSubject(firstNum: 40)
        println "fsWithMap.firstNum is ${fsWithMap.firstNum}"
        println "Instantiating with map, setting firstNum to 20"
        def fsWithMap2 = new FirstSubject(firstNum: 20)
        println "fsWithMap2.firstNum is ${fsWithMap2.firstNum}"
    } // tryFirstSubject

  static void main( String[] args ) {
    def sr = new SampleRunner()
    sr.seeSecondBookMethods()
    sr.doStuff001()
    // sr.doMovieStuff001()
    // sr.seeSecondBookMethods()
    sr.tryFirstSubject()
  }

}

