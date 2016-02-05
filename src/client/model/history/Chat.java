package client.model.history;

public class Chat 
{


	private MessageList ChatList;

	public Chat(){
		ChatList = new MessageList();
	}

	/**
	 * for printing or returning the list, however the history will need it.
	*/
	public void printMessageList()
	{
		StringBuilder sb = new StringBuilder();
		for(MessageLine line : ChatList.messages)
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
		ChatList.addMessage(new MessageLine(message, source));
	}

	public void clearMessageList(){
		ChatList.clearMessageList();
	}

	public MessageList getChatList() {
		return ChatList;
	}

	public void setChatList(MessageList chatList) {
		ChatList = chatList;
	}
}
