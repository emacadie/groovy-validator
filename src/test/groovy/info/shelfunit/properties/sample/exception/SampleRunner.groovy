package info.shelfunit.properties.sample.exception

import info.shelfunit.properties.annotations.StringAnnotation
import info.shelfunit.properties.annotations.AnnotationProcessor

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
    
    def seeBookMethods() {
        println "\n\nStarting seeBookMethods"
        def bmc = Book.metaClass
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
        println "Ending seeBookMethods"
    } // seeBookMethods
   

    def doStuff001() {
        // def b = new Book()
        def bmc = Book.metaClass
       
        def bFieldNames = [] // = Book.class.getDeclaredFields().each{it.getName()}
       
        def bFields = Book.class.getDeclaredFields()
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
                def field = Book.class.getDeclaredField( it.getName() )
                def stringAnnotation = field?.getAnnotation( StringAnnotation.class )
                if ( stringAnnotation ) {
                    print "${it.getName()} has StringAnnotation"
                    // intercept the setter
                    print " stringAnnotation.min(): ${stringAnnotation.min()} "
                    print " stringAnnotation.max(): ${stringAnnotation.max()} "
                    def mName = "set${it.getName().capitalize()}"
                    print " method would be ${mName} "
                    Book.metaClass.invokeMethod = { String name, args ->
                        println "Method name in invokeMethod is ${name}"
                        if ( name == mName ) {
                            println "Looking at Book.set${it.getName().capitalize()}"
                            if ( !( args[ 0 ].length() > stringAnnotation.min() ) ||
                                !( args[ 0 ].length() < stringAnnotation.max() ) ) {
                                Book.metaClass.invokeMissingMethod(delegate, name, args)
                                Book.metaClass.getMetaMethod("set${it.getName().capitalize()}").invoke(delegate, args)
                            } else {
                                println "Cannot call Book.set${it.getName().capitalize()}"
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
        Book.metaClass.invokeMethod = { String name, args ->
            println "Method name in standalone invokeMethod is ${name}"
            if ( name == 'setTitle' ) {
                println "Looking at Book.setTitle"
                if ( !( args[ 0 ].length() > stringAnnotation.min() ) ||
                    !( args[ 0 ].length() < stringAnnotation.max() ) ) {
                    Book.metaClass.invokeMissingMethod(delegate, name, args)
                    Book.metaClass.getMetaMethod('setTitle').invoke(delegate, args)
                } else {
                    println "Cannot call Book.setTitle"
                }
            }
        }
        */

        Book.metaClass.setProperty = { String name, arg ->
            println "name in setProperty is ${name}"
            def field = Book.class.getDeclaredField( name )
            def stringAnnotation = field?.getAnnotation( StringAnnotation.class )
            println "-- Here is stringAnnotation: ${stringAnnotation}"
            if ( stringAnnotation ) {
                println "Looking at Book.set${name.capitalize()}"
                if ( !( arg.length() < stringAnnotation.min() ) &&
                    !( arg.length() > stringAnnotation.max() ) ) {
                    Book.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg.toString() )
                } else {
                    println "Cannot call Book.setTitle"
                }
            } else {
                def mName = "set${name.capitalize()}"
                def propClassName = Book.class.metaClass.getMetaProperty( name ).getType().getName()
                println "LET'S TRY CALLING ${mName}, it's a ${propClassName}"
                Book.class.metaClass.getMetaProperty( name ).setProperty( delegate, arg ) // this works
            }
        }

        println " \n========"
        def bTest1 = new Book()
       
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
        // AnnotationProcessor.process( FirstSubject.class ) 
        def fs001 = new FirstSubject()
        println "1. Just called constructor for fs001"
        def fs002 = new FirstSubject()
        // @IntAnnotation(minValue=30, maxValue=400)
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
    sr.seeBookMethods()
    sr.doStuff001()
    // sr.doMovieStuff001()
    // sr.seeBookMethods()
    sr.tryFirstSubject()
  }

}

