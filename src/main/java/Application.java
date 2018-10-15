
public class Application {

	int id;
	String type;
	Server server;
	
	void subscribeToTopic() {
		// server.subscribeToTopic()
	}
	
	void publishQueueMsg(int recipientId, IMessage message) {
		// server.sendQueueMsg(recipientId, message)
	}
	
	void publishTopicMsg(String type, IMessage message) {
		// server.publishTopic(type, message)
	}
	
	int[] getApplications() {
		return null;
	}
}
