package org.groovy.cookbook

import static groovyx.gpars.GParsPool.runForkJoin
import static groovyx.gpars.GParsPool.withPool
import static com.google.common.collect.Lists.*

class WordAnalyzer {

	static final Integer THRESHOLD = 50000
	static final int MAX_THREAD = 8

	private Map calculateFrequency(List<String> words) {
		def frequencies = [:]
		words.each {
			Integer num = frequencies.get(it)
			frequencies.put(it, num ? num + 1 : 1)
		}
		frequencies
	}

	Map frequency(List<String> tokens) {
		def frequencyMap = [:]
		def maps

		withPool(MAX_THREAD) {
			maps = runForkJoin(tokens) { words ->
				if(words.size() <= THRESHOLD) {
					// No parallelism
					return calculateFrequency(words)
				} else {
					partition(words, THRESHOLD).each { sublist ->
						forkOffChild(sublist)
					}
					// Collect all results
					return childrenResults
				}
			}
		}

		maps.each {
			frequencyMap.putAll(it)
		}

		// Reverse sort
		frequencyMap.sort { a, b -> b.value <=> a.value }

	}

}