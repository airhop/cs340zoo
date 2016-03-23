package shared.serialization;

import client.MVC.data.GameInfo;
import client.model.map.Hex;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import shared.locations.HexLocation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Joshua on 3/23/2016.
 */
public class MapSerializer implements JsonSerializer<TreeMap<HexLocation, Hex>> {
    @Override
    public JsonElement serialize(TreeMap<HexLocation, Hex> src, Type typeOfSrc, JsonSerializationContext context) {
        Type typeSource = new TypeToken<TreeMap<HexLocation, Hex>>() {}.getType();
        JsonObject object = new JsonObject();
        return new JsonPrimitive(new Gson().toJson(src, typeOfSrc));
    }
//    private class DateTimeSerializer implements JsonSerializer<DateTime> {
//        public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
//            return new JsonPrimitive(src.toString());
//        }
//    }
}
