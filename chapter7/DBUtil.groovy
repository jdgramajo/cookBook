@GrabConfig(systemClassLoader=true)
@Grab('org.hsqldb:hsqldb')
import org.hsqldb.Server

class DBUtil {

	static dbSettings = [
		url: 'jdbc:hsqldb:hsql://localhost/cookingdb',
		driver: 'org.hsqldb.jdbcDriver',
		user: 'sa',
		password: ''
	]

	static startServer() {
		Server server = new Server()
		def logFile = new File('db.log')
		server.setLogWriter(new PrintWriter(logFile))
		server.with {
			setDatabaseName(0, 'cookingdb')
			setDatabasePath(0, 'mem:cookingdb')
			start()
		}
		server
	}

}