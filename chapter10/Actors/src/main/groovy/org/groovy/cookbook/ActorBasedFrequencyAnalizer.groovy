final class CalculateFrequencyMessage {
	List<String> tokens
}

final class StartFrequency {

}

final class FrequencyMapMessage {
	Map frequencyMap
}

final class WordFrequencyActor extends DynamicDispatchActor {

	void onMessage(CalculateFrequencyMessage message) {
		reply new FrequencyMapMessage(frequencyMap: calculateFrequency(message.tokens))
	}

	private Map calculateFrequency(List<String> words) {
		// The code for this function can be found in the
		// recipe Splitting a large task into smaller parallel jobs.
	}
}