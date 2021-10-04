package kr.co.yooooon.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //어노테이션이 적용할 위치 (멤버변수 선언시)
@Retention(RetentionPolicy.RUNTIME) //런타임까지 남아있는다.(=사실상 안 사라진다.)
public @interface ColumnName
{
    public String name();
}