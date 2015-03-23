package org.groovy.cookbook.intercept

import org.junit.Test

class TestInterceptor {
	
	def useInterceptor = { Class theClass, Class theInterceptor, Closure theCode ->
		def proxy = ProxyMetaClass.getInstance(theClass) // <- where do you come from?
		def interceptor = theInterceptor.newInstance()
		proxy.interceptor = interceptor
		proxy.use(theCode)
	}
	
	@Test
	void methodIsInterceptedByUsingProxy() {

		useInterceptor(InterceptedClass, PerformanceInterceptor) {
			def ic = new InterceptedClass()
			ic.test('a')
			ic.test('b')
			ic.test('c')
		}

	}

}