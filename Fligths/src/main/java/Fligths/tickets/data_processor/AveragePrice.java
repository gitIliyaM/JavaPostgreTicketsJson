package Fligths.tickets.data_processor;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class AveragePrice {
    private List<JsonObject> jsonObjectsList;

    public AveragePrice(List<JsonObject> list) {
        this.jsonObjectsList = list;
    }
    public int getAverageNumber(){
        int sum = 0;
        for(JsonObject jo: jsonObjectsList){
            int number = jo.getInt("price");
            sum += number;
        }
        sum = sum / jsonObjectsList.size();
        return sum;
    }
}
