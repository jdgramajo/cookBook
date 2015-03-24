package org.groovy.cookbook.intercept

import org.junit.Test

class TestInterceptor {
	
	def useInterceptor = { Class theClass, Class theInterceptor, Closure theCode ->
		def proxy = ProxyMetaClass.getInstance(theClass)
		def interceptor = PerformanceInterceptor.newInstance()
		proxy.interceptor = interceptor
		proxy.use(theCode)
	}

	@Test
	void methodIsInterceptedByUsingProxy() {

		useInterceptor(SlowClass, PerformanceInterceptor) {
			def ic = new SlowClass()
			ic.test('a')
			ic.test('b')
			ic.test('c')
		}

	}

}