package org.groovy.cookbook.intercept

import groovy.util.logging.Slf4j
import org.codehaus.groovy.runtime.InvokerHelper

@Slf4j
class PerformanceLogger implements GroovyInterceptable {
	def invokeMethod(String name, Object args) {

		long start = System.currentTimeMillis()

		def metaClass = InvokerHelper.getMetaClass(this)
		def result = metaClass.invokeMethod(this, name, args)

		long spentTime = System.currentTimeMillis() - start
		log.debug("Execution time for method ${name}: ${spentTime}ms.")

	}
}