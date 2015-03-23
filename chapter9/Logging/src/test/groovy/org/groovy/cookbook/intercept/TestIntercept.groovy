package org.groovy.cookbook.intercept

import org.junit.Test

class TestIntercept {
	@Test
	void methodIsIntercepted() {
		SlowClass sc = new SlowClass()
		3.times {
			sc.test('hello')
		}
	}
}