package kr.co.yooooon.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)   //어노테이션이 적용할 위치 (멤버변수 선언시)
@Retention(RetentionPolicy.RUNTIME)  //어떤 시점까지 어노테이션이 영향을 미칠지, 어노테이션 적용 범위  (컴파일 이후에도 참조) 
public @interface Remove
{
    public boolean value() default true;
}
