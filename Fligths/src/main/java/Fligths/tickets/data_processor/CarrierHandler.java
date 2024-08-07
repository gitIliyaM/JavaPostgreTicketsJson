package Fligths.tickets.data_processor;

import Fligths.tickets.all_enums.EnumCity;
import Fligths.tickets.objects_carriers.ObjectsCarriers;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class CarrierHandler implements ObjectCarrierHandler{
    private List<JsonObject> jsonObjectList;

    public CarrierHandler(List<JsonObject> jsonObjectList) {
        this.jsonObjectList = jsonObjectList;
    }

    public List<JsonObject> getJsonObjectList() {
        return jsonObjectList;
    }

    public void setJsonObjectList(List<JsonObject> jsonObjectList) {
        this.jsonObjectList = jsonObjectList;
    }

    @Override
    public List<JsonObject> cityVvoTlv() {
        List<JsonObject> listVvoTlv = new ArrayList<>();
        for(JsonObject jo: jsonObjectList){
            if(jo.getString("origin_name").contains(EnumCity.VVO.nameCity) && jo.getString("destination_name").contains(EnumCity.TLV.nameCity)){
                listVvoTlv.add(jo);
            }
        }
        return listVvoTlv;
    }
}
