package headers;


import application.Application;

public class QueueMessageHeader {
    private Application recipient;

    public Application getRecipient() {
        return recipient;
    }

    public void setRecipient(Application recipient) {
        this.recipient = recipient;
    }
}
