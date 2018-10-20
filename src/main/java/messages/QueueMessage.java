package messages;

import headers.QueueMessageHeader;

import java.util.LinkedList;

public class QueueMessage implements IMessage {

    private int capacity;
    private QueueMessageHeader queueMessageHeader;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public QueueMessageHeader getQueueMessageHeader() {
        return queueMessageHeader;
    }

    public void setQueueMessageHeader(QueueMessageHeader queueMessageHeader) {
        this.queueMessageHeader = queueMessageHeader;
    }

    public LinkedList<String> getContent() {
        return content;
    }

    public void setContent(LinkedList<String> content) {
        this.content = content;
    }

    private LinkedList<String> content = new LinkedList<String>();


    public QueueMessage(int capacity) {
        this.capacity = capacity;
    }
}
