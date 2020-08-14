

package com.zyj.httpclient.annotation;

import java.lang.annotation.*;

/**
 * 对象 不获取
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpIgnore {

}
