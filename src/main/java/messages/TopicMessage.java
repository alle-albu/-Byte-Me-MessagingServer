package messages;

import headers.TopicMessageHeader;
import messages.IMessage;

public class TopicMessage implements IMessage {
    public TopicMessageHeader getMessageHeader() {
        return topicMessageHeader;
    }

    public void setMessageHeader(TopicMessageHeader messageHeader) {
        this.topicMessageHeader = messageHeader;
    }

    private TopicMessageHeader topicMessageHeader;

    public TopicMessage(TopicMessageHeader topicMessageHeader,String data){
        this.topicMessageHeader=topicMessageHeader;
        this.data=data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;


    public void setContent() {
    }
}
