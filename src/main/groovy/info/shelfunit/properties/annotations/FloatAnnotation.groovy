package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )

public @interface FloatAnnotation {
  public float minValue() default 0.0f
  public float maxValue() default 3.4028235E38f // Float.MAX_VALUE 
}


