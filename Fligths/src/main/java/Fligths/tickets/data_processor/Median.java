package Fligths.tickets.data_processor;

import Fligths.tickets.data_storage.DataStorage;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Median {
    private List<DataStorage> jsonObjectsList;

    public Median(List<DataStorage> list) {
        this.jsonObjectsList = list;
    }
    public int getMedianNumber(){
        int intFirst, intSecond, mediana = 0;
        int sizeJsonObjectsList = jsonObjectsList.size();
        if(sizeJsonObjectsList % 2 == 0){
            intFirst = jsonObjectsList.get(sizeJsonObjectsList / 2).getPrice();
            intSecond = jsonObjectsList.get(sizeJsonObjectsList / 2 - 1).getPrice();
            mediana = (intFirst + intSecond) / 2;
        } else {
            mediana = jsonObjectsList.get(sizeJsonObjectsList / 2).getPrice();
        }
        return mediana;
    }
}
