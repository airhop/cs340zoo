package shared.serialization;

import client.model.GameModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;

public class Deserializer {

	/**
  	* deserializes the JSON formatted data from the server 
  	* @return object, you must cast this object to the thing you are deseralizing.
  	*/
	public GameModel deserialize(String jsonString, GameModel myModel)
	{
		JsonParser myParse = new JsonParser();
		JsonElement myEle = myParse.parse(jsonString);
		JsonTreeReader myTree = new JsonTreeReader(myEle);
		System.out.println();

		return myModel;
	}


}
