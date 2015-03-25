import java.text.NumberFormat

BigDecimal.metaClass.getInEuros = { ->
	def exchangeRate = 0.763461
	def nf = NumberFormat.getCurrencyInstance(Locale.US)
	nf.setCurrency(Currency.getInstance('EUR'))
	nf.format(delegate * exchangeRate)
}

println "assert 1500.00.inEuros == 'EUR1,145.19'"
assert 1500.00.inEuros == 'EUR1,145.19'