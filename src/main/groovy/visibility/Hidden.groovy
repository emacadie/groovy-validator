package visibility

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

import org.codehaus.groovy.transform.GroovyASTTransformationClass

/**
<p>The purpose of this annotation is to help you keep your private fields private.
</p>
<p>Suppose you have a field in an object you want to change. In that case, making it final will not work. But you also do not want code outside the object to change it. Making it private won't work in Groovy either. You could write a setter that takes an argument and does nothing with it, over and over. Or you could use this annotation.</p>
<p>Here is an example on how to use it:</p>
<p>It is said you are only as old as you feel. Suppose going on a yoga retreat helps you feel a year younger, but visiting your in-laws makes you feel a year older. (One way to avoid this is to not marry a Scala programmer.)
<pre>
class AgeHolder
    {@code @Hidden}
    int perceivedAge
    
    AgeHolder( argArg ) {
        this.perceivedAge = argAge
    }
    
    def visitYogaRetreat() {
        perceivedAge--
    }
    
    def visitInLaws() {
        perceivedAge++
    }
}
 
</pre>


<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass( [ 'visibility.HiddenTransformation' ] )
public @interface Hidden {
   
}


