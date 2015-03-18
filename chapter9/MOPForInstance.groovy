class Customer {
	Long id
	String name
	String lastName
}

Customer.metaClass.sayHello {
	"Hi ${name} ${lastName}!"
}

def c = new Customer()
c.metaClass.fullName { "${name} ${lastName}" }
c.name = 'John'
c.lastName = 'Doe'

assert c.fullName() == 'John Doe'

Customer.metaClass.constructor << { String name ->
	new Customer(name: name)
} << { Long id, String fullName ->
	new Customer(
		id: id,
		name: fullName.split(',')[0],
		lastName: fullName.split(',')[1]
	)
}

def c0 = new Customer('Mike')
assert c0.name == 'Mike'
//println c0.fullName

def c1 = new Customer(1000, 'Mike,Whitall')
assert c1.name == 'Mike'
assert c1.lastName == 'Whitall'

println c.sayHello()
println c0.sayHello()
println c1.sayHello()