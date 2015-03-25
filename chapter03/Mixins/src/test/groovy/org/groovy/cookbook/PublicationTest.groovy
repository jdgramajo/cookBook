package org.groovy.cookbook

import org.junit.Test
import groovy.time.DatumDependentDuration

class PublicationTest {

	@Test
	def void testPublications() {
		def monthly = new DatumDependentDuration(0, 1, 0, 0, 0, 0, 0)
		def daily = new DatumDependentDuration(0, 0, 1, 0, 0, 0, 0)

		def groovyMag = new OnlineMagazine(id: 'GRMAG', title: 'GroovyMag').with {
			url = new URL('http://grailsmag.com/')
			issuePeriod = monthly
			it
		}

		def time = new MultimediaMagazine(id: 'TIME', title: 'Time').with {
			pageCount = 60
			url = new URL('http://www.time.com')
			issuePeriod = monthly
			it
		}

		def pravda = new Newspaper(id: 'PRVD', title: 'Pravda').with {
			pageCount = 8
			issuePeriod = daily
			it
		}
	}

}