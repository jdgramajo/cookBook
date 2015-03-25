class Book {

	String title
	Author author
	Long year
	Long pages

	Long getAmazonSalesPosition() {
		new Random().nextInt(1) + 1
	}

	void attachReview(String review) { }

}

class Author {
	String name
	String lastName
}

println "Access to the 'name' property:"
assert 'java.lang.String' == String.name
println Author.name

println "Access to the property collection:"
Author a = new Author(name: 'Ernest', lastName: 'Hemingway')
Book book = new Book()
book.with {
	title = 'The Old Man and the Sea'
	year = 1952
	pages = 200
	author = a
}
book.properties.each { println it }

println "Using 'metaClass':"
println book.metaClass.hasProperty(book, 'pages')
println "The methods"
println book.metaClass.methods.each { println it }
println book.metaClass.properties.each { println it }

println "Responds to..."
assert book.metaClass.respondsTo(book,'getAmazonSalesPosition')
assert book.metaClass.respondsTo(book,'attachReview', String)
assert book.respondsTo('attachReview', String)