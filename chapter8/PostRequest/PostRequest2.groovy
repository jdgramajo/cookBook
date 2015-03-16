@Grab(
  group='org.codehaus.groovy.modules.http-builder',
  module='http-builder',
  version='0.6'
)

import groovyx.net.http.*

def baseUrl = 'http://api.duckduckgo.com'
def queryString = 'q=groovy&format=json&pretty=1'
def http = new HTTPBuilder(baseUrl)

http.request(Method.POST) {

  send ContentType.URLENC, queryString

  response.success = { response, reader ->
    println response.statusLine
    println reader.text
  }

  response.failure = { response ->
    println response.statusLine
  }
}