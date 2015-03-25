package org.groovy.cookbook

class MyTestClass {

	def cacheMap1 = [:]

	@Cacheable
	Long veryExpensive(Long a) {
		sleep(rnd())
		a * 20
	}

	static rnd() {
		Math.abs(new Random().nextInt() % 5000 + 1000)
	}

}