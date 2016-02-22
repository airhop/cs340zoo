package client.model.history;

import java.util.ArrayList;

public class MessageList {

    ArrayList<MessageLine> messages;


    public MessageList(){
        messages = new ArrayList<>();
    }

    public void clearMessageList() {
        messages.clear();
    }

    public int size()
    {
        return messages.size();
    }

    public ArrayList<MessageLine> getMessages()
    {
        return messages;
    }
    /**
     * Adds a message to the message list
     *
     * @param line - a line of text
     */
    public void addMessage(MessageLine line) {
        messages.add(line);
    }

    /**
     * Gets the line from the chat list that matches the paramater line
     * If there is an exact match it will return it, otherwise it will return null
     *
     * @param line - a line of text
     */
    public MessageLine getMessageLine(MessageLine line) {
//  	for(int i = 0; i < getMessage.length; i++)
//  	{
//  	  if(getMessage.get(i).getMessage.equals(line.getMessage) && chatMessages.get(i).getMessage.equals(line.getSource))
//  	  {
//  	    return chatMessages.get(i);
//  	  }
//  	}
        return null;
    }

}
