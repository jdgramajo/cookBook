@Grab(
	group = 'org.codehaus.groovy.modules.http-builder',
	module = 'http-builder',
	version='0.6'
)

import groovyx.net.http.URIBuilder

def baseUri = 'http://groovy.services.com/service1/operation2'
def uri = new URIBuilder(baseUri)

uri.with {
	scheme = 'https'
	host = 'localhost'
	port = 8080
	fragment = 'some_anchor'
	path = 'some_folder/some_page.html'
	query = [param1: 2, param2: 'x']
}

println uri.toURI()
println uri.toURL()
println uri.toString()