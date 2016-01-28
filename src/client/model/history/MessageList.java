package client.model.history;

import java.util.ArrayList;

public class MessageList {

  ArrayList<MessageLine> chatMessages;
  ArrayList<MessageLine> logMessages;
  
  /**
  * Adds a message to the chat list
  * 
  * @param line - a line of text
  */
  public void addChatMessage(MessageLine line)
  {
    chatMessages.add(line);
  }
   /**
  * Adds a message to the log list
  * 
  * @param line - a line of text
  */
  public void addLogMessage(MessageLine line)
  {
    logMessages.add(line);
  }
   /**
  * Gets the line from the chat list that matches the paramater line
  * If there is an exact match it will return it, otherwise it will return null
  * 
  * @param line - a line of text
  */
  public MessageLine getChatLine(MessageLine line)
  {
  	for(int i = 0; i < chatMessages.getLength(); i++)
  	{
  	  if(chatMessages.get(i).getMessage.equals(line.getMessage) && chatMessages.get(i).getMessage.equals(line.getSource))
  	  {
  	    return chatMessages.get(i);
  	  }
  	}
  	return null;
  }
     /**
  * Gets the line from the log list that matches the paramater line
  * If there is an exact match it will return it, otherwise it will return null
  * 
  * @param line - a line of text
  */
  public MessageLine getLogLine()
  {
  	for(int i = 0; i < chatMessages.getLength(); i++)
  	{
  	  if(logMessages.get(i).getMessage.equals(line.getMessage) && chatMessages.get(i).getMessage.equals(line.getSource))
  	  {
  	    return logMessages.get(i);
  	  }
  	}
  	return null;
  }

}
