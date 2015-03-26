package org.groovy.cookbook

import org.junit.*
import edu.stanford.nlp.process.PTBTokenizer
import edu.stanford.nlp.process.CoreLabelTokenFactory
import edu.stanford.nlp.ling.CoreLabel

class WordAnalyzerTest {

	@Test
	void testFrequency() {
		def bigText = 'http://norvig.com/big.txt'.toURL()
		def wa = new WordAnalyzer()
		def tokens = tokenize(bigText.text)
		long start = System.currentTimeMillis()
		def m = wa.frequency(tokens)
		def timeSpent = System.currentTimeMillis() - start
		println "Execution time: ${timeSpent}ms"
		println 'For calculating frequency over: '
		println "${tokens.size()} tokens"
		m.sort { -it.value }.each {
			if(it.value > 50) {
				println it
			}
		}
	}

	def tokenize(String txt) {
		List<String> words = []
		PTBTokenizer ptbt = new PTBTokenizer(new StringReader(txt), new CoreLabelTokenFactory(), '')
		ptbt.each { entry ->
			words << entry.value()
		}
		words
	}

}