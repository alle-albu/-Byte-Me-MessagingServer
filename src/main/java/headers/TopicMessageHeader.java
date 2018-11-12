package headers;

public class TopicMessageHeader {
    private String topic;
    private int timeout;


    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

public TopicMessageHeader(String topic, int timeout){
        this.topic=topic;
        this.timeout=timeout;
}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
