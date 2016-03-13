package server.servermain;

import server.shared.CommandType;

/**
 * Created by Joshua on 3/10/2016.
 */
public class JsonConstructionInfo {
    private CommandType name;
    private String JsonBody;

    public JsonConstructionInfo(){

    }

    public JsonConstructionInfo(CommandType n, String jb)
    {
        name = n;
        JsonBody = jb;
    }

    public CommandType getName() {
        return name;
    }

    public void setName(CommandType name) {
        this.name = name;
    }

    public String getJsonBody() {
        return JsonBody;
    }

    public void setJsonBody(String jsonBody) {
        JsonBody = jsonBody;
    }
}
