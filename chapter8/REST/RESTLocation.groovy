@Grab(
	group = 'org.codehaus.groovy.modules.http-builder',
	module = 'http-builder',
	version = '0.6'
)

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.XML
import groovyx.net.http.RESTClient

class LocationFinder {

	static final KEY = 'AhrmS1_NRh-LAWbSHxKSTHS1epJuOnF8a--1OsvDYQdb8QTQHqDaH18KQEhSumq7'
	static final URL = 'http://dev.virtualearth.net'
	static final BASE_PATH = '/REST/v1/Locations/'

	def printCityCoordinates(countryCode, city) {

		def mapClient = new RESTClient(URL)
		def path = "${countryCode}/${city}"
		def response = mapClient.get(
			path: "${BASE_PATH}${path}",
			query: [key: KEY]
		)

		assert response.status == 200
		assert response.contentType == JSON.toString()

		println response.data.resourceSets.resources.point.coordinates

	}
}

LocationFinder map = new LocationFinder()
map.printCityCoordinates('fr', 'paris')
map.printCityCoordinates('uk', 'london')