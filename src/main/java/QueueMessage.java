import java.util.LinkedList;

public class QueueMessage implements IMessage {

    private int capacity;

        

    private LinkedList<String> content = new LinkedList<String>();


    public QueueMessage(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void setupHeader() {

    }

    @Override
    public void setContent() {

    }
}
