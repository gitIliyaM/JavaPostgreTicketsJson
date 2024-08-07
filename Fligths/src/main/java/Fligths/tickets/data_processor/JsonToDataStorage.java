package Fligths.tickets.data_processor;

import Fligths.tickets.data_storage.DataStorage;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class JsonToDataStorage extends DataStorage {
    private List<String> listStringTime = new ArrayList<>();
    private List<JsonObject> jsonObjectsList = new ArrayList<>();
    private List<DataStorage> jsonToDataStorage = new ArrayList<>();

    public JsonToDataStorage() {
        super();
    }
    public void setJsonObjectsList(List<JsonObject> jsonObjectsList) {
        this.jsonObjectsList = jsonObjectsList;
    }
    public void setListStringTime(List<String> listStringTime){
        this.listStringTime = listStringTime;
    }
    public List<DataStorage> setToDataStorage(){
        int i = 0;
        for(JsonObject jo: jsonObjectsList) {
                jsonToDataStorage.add (new DataStorage(
                    jo.getString("origin"),
                    jo.getString("origin_name"),
                    jo.getString("destination"),
                    jo.getString("destination_name"),
                    jo.getString("departure_date"),
                    jo.getString("departure_time"),
                    jo.getString("arrival_date"),
                    jo.getString("arrival_time"),
                    jo.getString("carrier"),
                    jo.getInt("stops"),
                    jo.getInt("price"),
                    listStringTime.get(i)
                    )
                );
                i++;
        }
        return jsonToDataStorage;
    }
}
