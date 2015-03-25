package org.groovy.cookbook.intercept

import groovy.util.logging.Slf4j

@Slf4j
class PerformanceInterceptor implements Interceptor {

	private Long start = 0

	Object beforeInvoke(Object object, String methodName, Object[] arguments) {
		start = System.currentTimeMillis()
		null
	}

	boolean doInvoke() { true }

	Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
		Long spentTime = System.currentTimeMillis() - start
		log.debug("Execution time for method ${methodName}: ${spentTime}ms.")
		result
	}

}