package shared.serialization;

import client.model.map.Hex;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import shared.locations.HexLocation;

import java.lang.reflect.Type;
import java.util.TreeMap;

/**
 * Created by Joshua on 3/23/2016.
 */
public class MapDeserializer implements JsonDeserializer<TreeMap<HexLocation, Hex>> {
    @Override
    public TreeMap<HexLocation, Hex> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Type typeSource = new TypeToken<TreeMap<HexLocation, Hex>>() {
        }.getType();
        return new Gson().fromJson(json, typeSource);
    }
//    private class DateTimeDeserializer implements JsonDeserializer<DateTime> {
//        public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                throws JsonParseException {
//            return new DateTime(json.getAsJsonPrimitive().getAsString());
//        }
//    }
}
