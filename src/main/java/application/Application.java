package application;

import messages.IMessage;
import messages.QueueMessage;
import server.Server;

import java.util.Stack;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Application extends Thread{

	Thread thread;
	int id;
	String type;
	Server server;
	private int messagesCapacity;
    private ReentrantLock writeLock=new ReentrantLock();

    public Application(int id, String type){
        this.id=id;
        this.type=type;
        this.messagesQueue=new ConcurrentLinkedQueue<QueueMessage>();
    }

    public Application(int id, String type,int capacity){
        this.id=id;
        this.type=type;
        this.messagesQueue=new ConcurrentLinkedQueue<QueueMessage>();
        this.messagesCapacity = capacity;
    }
    
	ExecutorService executorService = Executors.newScheduledThreadPool(0);
	ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	public ConcurrentLinkedQueue<QueueMessage> getMessagesQueue() {
		return messagesQueue;
	}

	public void setMessagesQueue(ConcurrentLinkedQueue<QueueMessage> messagesQueue) {
		this.messagesQueue = messagesQueue;
	}

	private ConcurrentLinkedQueue<QueueMessage> messagesQueue;




	public void subscribeToTopic() {
		// server.subscribeToTopic()
	}


	public synchronized void publishQueueMsg(QueueMessage message) throws InterruptedException {
		Application recipient = message.getQueueMessageHeader().getRecipient();

		Runnable t = () -> {
			if (recipient.getMessagesCapacity() == 0) {
				System.out.println("A; size " + recipient.getMessagesQueue().size());
				recipient.getMessagesQueue().add(message);
			} else if (recipient.getMessagesQueue().size() < recipient.getMessagesCapacity()-1) {
				System.out.println("B; size " + recipient.getMessagesQueue().size());
				recipient.getMessagesQueue().add(message);
	        }else {
	        	System.out.println("C; size " + recipient.getMessagesQueue().size());
			    while(recipient.getMessagesQueue().size()>=recipient.getMessagesCapacity()-1) {
			    	System.out.println("waiting...");
			    	try {
						executorService.awaitTermination(2, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
	            }
			    recipient.getMessagesQueue().add(message);
	        }
		};
		executorService.execute(t);

	}

	public void publishTopicMsg(String type, IMessage message) {
		// server.publishTopic(type, message)
	}

	int[] getApplications() {
		return null;
	}

	public synchronized void readQueueMessage(){
		Runnable t = () -> {
			if(!this.messagesQueue.isEmpty()){
				System.out.println(messagesQueue.size());
				System.out.println(this.messagesQueue.remove().getData());
			} };
		scheduledExecutorService.scheduleAtFixedRate(t,0,1, SECONDS);

	}

	public int getMessagesCapacity() {
		return messagesCapacity;
	}

//	public void setMessagesCapacity(int messagesCapacity) {
//		this.messagesCapacity = messagesCapacity;
//	}

	public void readTopicMessages(Server server,String topic){
		Runnable t = () -> System.out.println(server.getTopicMessagesByTopic(topic));
		scheduledExecutorService.scheduleAtFixedRate(t,0,1, SECONDS);
	}
}
