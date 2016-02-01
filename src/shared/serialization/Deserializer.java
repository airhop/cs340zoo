package shared.serialization;

import client.model.GameModel;
import com.google.gson.Gson;

public class Deserializer {

	/**
  	* deserializes the JSON formatted data from the server 
  	* @return object, you must cast this object to the thing you are deseralizing.
  	*/
	public Object deserialize(String json)
	{
		Gson gson = new Gson();
		GameModel deserializeGame = gson.fromJson(json, GameModel.class);


		return deserializeGame;
	}


}
