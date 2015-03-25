package org.groovy.cookbook

import static groovyx.gpars.GParsPool.*

import org.junit.*

import edu.stanford.nlp.process.PTBTokenizer
import edu.stanford.nlp.process.CoreLabelTokenFactory
import edu.stanford.nlp.ling.CoreLabel

class ParallelTest {

	static words = []

	@BeforeClass
	static void loadDict() {
		def libraryUrl = 'http://www.gutenberg.org/cache/epub'
		def bookFile = '17405/pg17405.txt'
		def bigText = "${libraryUrl}/${bookFile}".toURL()
		words = tokenize(bigText.text)
	}

	static tokenize(String txt) {
		List<String> words = []
		PTBTokenizer ptbt = new PTBTokenizer(new StringReader(txt), new CoreLabelTokenFactory(), '')
		ptbt.each { entry ->
			words << entry.value()
		}
		words
	}

	@Test
	void testParallelEach() {
		withPool {
			words.eachParallel { token ->
				if(token.length() > 10 && !token.startsWith('http')) {
					println token
				}
			}
		}
	}

	@Test
	void testEveryParallel() {
		withPool {
			assert !(words.everyParallel { token ->
				token.length() > 20
				})
		}
	}

	@Test
	void combinedParallel() {
		withPool {
			println words
			.findAllParallel { it.length() > 10 && !it.startsWith('http') }
			.groupByParallel{ it.length() }
			.collectParallel { "WORD LENGTH ${it.key}: " + it.value*.toLowerCase().unique() }
		}
	}

}