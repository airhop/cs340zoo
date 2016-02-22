
package client.model.history;

public class MessageLine {

    String message;
    String source;

    public MessageLine(String mess, String src) {
        message = mess;
        source = src;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

//getters and setters right here

}
