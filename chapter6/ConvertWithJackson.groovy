@Grab('com.fasterxml.jackson.core:jackson-databind:2.1.0')
import com.fasterxml.jackson.databind.ObjectMapper
import org.groovy.cookbook.Vehicle

def mapper = new ObjectMapper()
def file = new File('vehicle.json')

println mapper.readValue(file, Vehicle)