package org.groovy.cookbook.intercept

class SlowClass extends PerformanceLogger {

	void test(String a) {
		Thread.sleep(rnd())
	}

	// return a value between 1000 and 5000
	static rnd() {
		Math.abs(new Random().nextInt() % 5000 + 1000)
	}

}