package messages;

import headers.QueueMessageHeader;

import java.util.LinkedList;

public class QueueMessage implements IMessage {

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    private QueueMessageHeader queueMessageHeader;

    public QueueMessageHeader getQueueMessageHeader() {
        return queueMessageHeader;
    }

    public void setQueueMessageHeader(QueueMessageHeader queueMessageHeader) {
        this.queueMessageHeader = queueMessageHeader;
    }


    public QueueMessage(String data){
        queueMessageHeader=new QueueMessageHeader();
        this.data=data;
    }

}
