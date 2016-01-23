package client.model.history;

import java.util.ArrayList;

public class MessageList {

  ArrayList<MessageLine> chatMessages;
  ArrayList<MessageLine> logMessages;
  
  /**
  * Adds a message to the message list
  * 
  * @param line - a line of text
  */
  public void addChatMessage(MessageLine line)
  {
    chatMessages.add(line);
  }
  public void addLogMessage(MessageLine line)
  {
    logMessages.add(line);
  }
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
