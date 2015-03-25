def baseUrl = new URL('http://api.duckduckgo.com/')
def queryString = '?q=groovy&format=json&pretty=1'
def connection = baseUrl.openConnection()
connection.with {
	doOutput = true
	requestMethod = 'POST'
	outputStream.withWriter { writer ->
		writer << queryString
	}
	println content.text
}