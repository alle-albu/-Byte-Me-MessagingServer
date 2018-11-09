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


	public synchronized void publishQueueMsg(QueueMessage message) {
		Application recipient = message.getQueueMessageHeader().getRecipient();



		Runnable t = () -> recipient.getMessagesQueue().add(message);

		if (recipient.getMessagesCapacity() == 0) {
			executorService.submit(t);
		} else if (recipient.getMessagesQueue().size() < recipient.getMessagesCapacity()) {
            executorService.submit(t);
        }else {
		    while(recipient.getMessagesQueue().size()>recipient.getMessagesCapacity()) {
                recipient.getMessagesQueue().add(message);
            }



            /*final ScheduledFuture<?> queueHandle = scheduledExecutorService.scheduleAtFixedRate(t, 0, 1, SECONDS);
            scheduledExecutorService.schedule(new Runnable() {
                public void run() {
                    if ()
                        queueHandle.cancel(true);
                }
            }, 1 * 9, SECONDS);*/
        }

	}

	public void publishTopicMsg(String type, IMessage message) {
		// server.publishTopic(type, message)
	}

	int[] getApplications() {
		return null;
	}

	public void readQueueMessage(){
		Runnable t = () -> {
			if(!this.messagesQueue.isEmpty()){
				System.out.println(messagesQueue.size());
				System.out.println(this.messagesQueue.remove().getData());
			}};
		scheduledExecutorService.scheduleAtFixedRate(t,0,1, SECONDS);

	}

	public int getMessagesCapacity() {
		return messagesCapacity;
	}

	public void setMessagesCapacity(int messagesCapacity) {
		this.messagesCapacity = messagesCapacity;
	}
}
