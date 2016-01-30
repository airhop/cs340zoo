package shared.serialization;

import com.google.gson.Gson;

public class Serializer{

	/**
  	* serializes the JSON formatted to be sent to the server
  	* 
  	*/
	public String serialize(Object objectToSeralize)
	{
		Gson gson = new Gson();
		String json = gson.toJson(objectToSeralize);

		return json;

	}


}
