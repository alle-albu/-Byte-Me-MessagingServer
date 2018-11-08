import application.Application;
import messages.QueueMessage;
import server.Server;

public class Main {

    public static void main(String[] args) {
        Thread t1=new Thread();
        Thread t2=new Thread();

        System.out.println("Bravo bravo, pentru raspunsuri la intrebari contactati: danc@cs.upt.ro");

        Server server=new Server();
        Application application1=new Application(1,"test");
        Application application2=new Application(2,"test");

        QueueMessage queueMessage = new QueueMessage("mesaj catre app2 de la app1");
        queueMessage.getQueueMessageHeader().setRecipient(application2);


        Application application3=new Application(2,"test");
        QueueMessage queueMessage2 = new QueueMessage("mesaj catre app2 de la app3");
        queueMessage2.getQueueMessageHeader().setRecipient(application2);

        for(int i=0 ; i<10000; i++) {
            application1.publishQueueMsg(queueMessage);
            application3.publishQueueMsg(queueMessage2);
        }

        application2.readQueueMessage();


    }

}
