package org.groovy.cookbook

import org.junit.*

class TestAst {

	@Test
	void checkCacheWorks() {

		def myTest = new MyTestClass()

		3.times {
			withTime { println myTest.veryExpensive(10) }
		}

	}

	def withTime = {Closure operation ->
		def start = System.currentTimeMillis()
		operation()
		def timeSpent = System.currentTimeMillis() - start
		println "TIME IS > ${timeSpent}ms."
	}

}