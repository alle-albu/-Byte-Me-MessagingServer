package server;

import application.Application;
import headers.TopicMessageHeader;
import messages.QueueMessage;
import messages.TopicMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Server {
    private List<TopicMessage> topicMessages;
    private int timeout;
    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public Server() {
        this.topicMessages = new ArrayList<>();
    }

    public Server(int timeout) {
        this.timeout=timeout;
        this.topicMessages = new ArrayList<>();
    }

    public synchronized void addTopicMessages(TopicMessage topicMessage){
        if(timeout!=0){
            topicMessage.getMessageHeader().setTimeout(this.timeout);
        }
        this.topicMessages.add(topicMessage);
    }

    public synchronized List<String> getTopicMessagesByTopic(String topic){
        return topicMessages
                .stream()
                .filter(topicMessage -> topicMessage.getMessageHeader().getTopic().equalsIgnoreCase(topic))
                .map(topicMessage -> topicMessage.getData())
                .collect(Collectors.toList());
    }


    public synchronized void startTopicMessagesDeletion(){
        Runnable t = () -> {
            if(!this.topicMessages.isEmpty()){
                topicMessages = topicMessages.stream()
                        .map(topicMessage -> {
                                TopicMessageHeader header=topicMessage.getMessageHeader();
                                header.setTimeout(header.getTimeout()-1);
                                return topicMessage;
                            })
                        .filter(topicMessage -> topicMessage.getMessageHeader().getTimeout()!=0)
                        .collect(Collectors.toList());

            }};
        scheduledExecutorService.scheduleAtFixedRate(t,0,1, SECONDS);
    }


}
