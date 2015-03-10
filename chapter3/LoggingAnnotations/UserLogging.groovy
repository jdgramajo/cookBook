@groovy.util.logging.Log
class UserService {
	def createUser(String username, String password) {
		log.info("creating user with name ${username}")
	}
}

def userService = new UserService()
userService.createUser('john', 'secret')