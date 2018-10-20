package server;

import application.Application;
import messages.QueueMessage;
import messages.TopicMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Server {
    private List<TopicMessage> topicMessages;
    private Queue<QueueMessage> queueMessages;
    private List<Application> topicClients;
    private int queueMaxSize;
    private int topicMessagesTimeoutInSeconds;


    public Server() {
        this.topicMessages = new ArrayList<TopicMessage>();
        this.queueMessages = new LinkedList<QueueMessage>();
    }

    public void sendQueueMessage(Application recipient, QueueMessage queueMessage) {
        //recipient.sendQueueMessage(topicMessage); TODO:: IMPLEMENT sendMessage in client, do not forget about the removal from queue.
    }

    public void sendTopicMessage(Application recipient, String topic){
        for(TopicMessage topicMessage: topicMessages){
            if(topicMessage.equals(topic)){
                //recipient.sendTopicMessage(topicMessage); TODO::IMPLEMENT sendTopicMessage in client
            }
        }
    }

    private void deleteTopicMessage(TopicMessage topicMessage){
        this.topicMessages.remove(topicMessage);
    }

    //TODO:: Implement delete topic message



    public void addTopicMessage(TopicMessage topicMessage) {
        this.topicMessages.add(topicMessage);
    }

    public void addQueueMessage(QueueMessage queueMessage) {
        this.queueMessages.add(queueMessage);
    }

    public void removeTopicMessage(TopicMessage topicMessage) {
        this.queueMessages.remove(topicMessage);
    }

    public int getQueueMaxSize() {
        return queueMaxSize;
    }

    public void setQueueMaxSize(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
    }

    public int getTopicMessagesTimeoutInSeconds() {
        return topicMessagesTimeoutInSeconds;
    }

    public void setTopicMessagesTimeoutInSeconds(int topicMessagesTimeoutInSeconds) {
        this.topicMessagesTimeoutInSeconds = topicMessagesTimeoutInSeconds;
    }
}
