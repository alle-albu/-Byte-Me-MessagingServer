import application.Application;
import headers.TopicMessageHeader;
import messages.QueueMessage;
import messages.TopicMessage;
import server.Server;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread();
        Thread t2=new Thread();

        System.out.println("Bravo bravo, pentru raspunsuri la intrebari contactati: danc@cs.upt.ro");

        Server server=new Server();

        Application application1=new Application(1,"test");
        Application application2=new Application(2,"test",20);
        Application application3=new Application(2,"test");

        QueueMessage queueMessage = new QueueMessage("mesaj catre app2 de la app1");
        queueMessage.getQueueMessageHeader().setRecipient(application2);

        QueueMessage queueMessage2 = new QueueMessage("mesaj catre app2 de la app3");
        queueMessage2.getQueueMessageHeader().setRecipient(application2);

        Thread th1 = new Thread( () ->  {
        	application2.readQueueMessage();
        });
        
        Thread th2 = new Thread( () -> {
        	for(int i=0 ; i<20; i++) {
                try {
					application1.publishQueueMsg(queueMessage);
	                application3.publishQueueMsg(queueMessage2);
	                
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        th1.start();
        th2.start();
        th2.join();
        th1.join();
        
        
        server.addTopicMessages(new TopicMessage(new TopicMessageHeader("topic",10),"mesaj1"));
        server.addTopicMessages(new TopicMessage(new TopicMessageHeader("topic2",15),"mesaj2"));
        server.addTopicMessages(new TopicMessage(new TopicMessageHeader("topic",20),"mesaj3"));
        server.startTopicMessagesDeletion();

            application1.readTopicMessages(server,"topic");
            application2.readTopicMessages(server,"topic2");
        

    }

}
