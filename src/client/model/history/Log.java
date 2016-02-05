package client.model.history;

public class Log {

	MessageList LogList;
	/**
	 * for printing or returning the list, however the history will need it.
	*/
	public void printMessageList()
	{
		StringBuilder sb = new StringBuilder();
		for(MessageLine line : LogList.messages)
		{
			sb.append(line.message);
			sb.append("\n");
			sb.append(line.source);
			sb.append("\n");
		}
	}
	
	/**
	 * to add a message to the messagelist
	*/
	public void addMessage(String source, String message)
	{
		LogList.addMessage(new MessageLine(message, source));
	}

}
