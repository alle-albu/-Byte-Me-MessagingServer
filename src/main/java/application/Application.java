package application;

import messages.IMessage;
import messages.QueueMessage;
import server.Server;

import java.util.Stack;
import java.util.concurrent.*;

public class Application extends Thread{

	Thread thread;
	int id;
	String type;
	Server server;
	ExecutorService executorService = Executors.newScheduledThreadPool(3);
	ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	public Stack<QueueMessage> getMessagesQueue() {
		return messagesQueue;
	}

	public void setMessagesQueue(Stack<QueueMessage> messagesQueue) {
		this.messagesQueue = messagesQueue;
	}

	private Stack<QueueMessage> messagesQueue;


	public Application(int id, String type){
		this.id=id;
		this.type=type;
		this.messagesQueue=new Stack<QueueMessage>();
	}

	public void subscribeToTopic() {
		// server.subscribeToTopic()
	}

	public void publishQueueMsg(QueueMessage message) {
		Runnable t = () -> {
		message.getQueueMessageHeader().getRecipient().getMessagesQueue().push(message);};
		executorService.submit(t);
		//executorService.shutdown();

	}
	
	public void publishTopicMsg(String type, IMessage message) {
		// server.publishTopic(type, message)
	}
	
	int[] getApplications() {
		return null;
	}

	public void readQueueMessage(){
		Runnable t = () -> {
			if(!this.messagesQueue.empty()){
				System.out.println(messagesQueue.size());
				System.out.println(this.messagesQueue.pop().getData());
			}};
		scheduledExecutorService.scheduleAtFixedRate(t,0,1, TimeUnit.SECONDS);

	}
}
