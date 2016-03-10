package server.servermain;

/**
 * Created by Joshua on 3/10/2016.
 */
public class JsonConstructionInfo {
    private String name;
    private String JsonBody;

    public JsonConstructionInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonBody() {
        return JsonBody;
    }

    public void setJsonBody(String jsonBody) {
        JsonBody = jsonBody;
    }
}
