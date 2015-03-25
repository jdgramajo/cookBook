package org.groovy.cookbook

import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass (['org.groovy.cookbook.CacheableTransformation'])
@interface Cacheable {

}