def outputFile = new File('image.png')
def baseUrl = 'http://groovy.codehaus.org'
def imagePath = '/images/groovy-logo-medium.png'
def url = new URL("${baseUrl}${imagePath}")

outputFile.delete()

url.withInputStream { inputStream ->
	outputFile << inputStream
}

outputFile = new File('groovy.html')

url = new URL(baseUrl)

outputFile.delete()

outputFile.text = url.text