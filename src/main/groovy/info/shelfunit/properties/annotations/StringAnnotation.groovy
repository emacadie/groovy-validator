package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )

public @interface StringAnnotation {
  public int minLength() default 0
  public int maxLength() default 2147483647 // Integer.MAX_VALUE
}

