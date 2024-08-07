package Fligths.tickets.data_processor;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ObjectsJsonArrayProcessor {
    private List<JsonObject> objectList = new ArrayList<>();

    public List<JsonObject> setObjectsToList(JsonArray jsonArray){
        for(int i = 0; i < jsonArray.size(); i++){
            objectList.add(jsonArray.getJsonObject(i));
        }
        return objectList;
    }
}
