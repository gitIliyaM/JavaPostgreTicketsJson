package Fligths.tickets.objects_carriers;

import Fligths.tickets.cargo_carriers.ListCarrier;
import Fligths.tickets.data_processor.CarrierHandler;
import Fligths.tickets.data_processor.ObjectsJsonArrayProcessor;
import org.w3c.dom.ls.LSInput;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class ObjectsCarriers implements ObjectsCarrier{
    private List<JsonObject> objectsList = new ArrayList<>();

    public void getArrayJson(JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.getJsonArray("tickets");
        objectsList = new ObjectsJsonArrayProcessor().setObjectsToList(jsonArray);
        TK();
        S7();
        SU();
        BA();
        executeVvoTlvCarriers(objectsList);
    }

    @Override
    public void TK() {
        List<JsonObject> TK = new ArrayList<>();
        for(JsonObject jo: objectsList){
            if(jo.getString("carrier").contains("TK")){
                 TK.add(jo);
            }
        }
        CarrierHandler carrierHandler = new CarrierHandler(TK);
        List<JsonObject> cityVvoTlv = carrierHandler.cityVvoTlv();
        executeVvoTlvCarrier(cityVvoTlv);
    }

    @Override
    public void S7() {
        List<JsonObject> S7 = new ArrayList<>();
        for(JsonObject jo: objectsList){
            if(jo.getString("carrier").contains("S7")){
                S7.add(jo);
            }
        }
        CarrierHandler carrierHandler = new CarrierHandler(S7);
        List<JsonObject> cityVvoTlv = carrierHandler.cityVvoTlv();
        executeVvoTlvCarrier(cityVvoTlv);
    }

    @Override
    public void SU() {
        List<JsonObject> SU = new ArrayList<>();
        for(JsonObject jo: objectsList){
            if(jo.getString("carrier").contains("SU")){
                SU.add(jo);
            }
        }
        CarrierHandler carrierHandler = new CarrierHandler(SU);
        List<JsonObject> cityVvoTlv = carrierHandler.cityVvoTlv();
        executeVvoTlvCarrier(cityVvoTlv);
    }

    @Override
    public void BA() {
        List<JsonObject> BA = new ArrayList<>();
        for(JsonObject jo: objectsList){
            if(jo.getString("carrier").contains("BA")){
                BA.add(jo);
            }
        }
        CarrierHandler carrierHandler = new CarrierHandler(BA);
        List<JsonObject> cityVvoTlv = carrierHandler.cityVvoTlv();
        executeVvoTlvCarrier(cityVvoTlv);
    }

    @Override
    public void executeVvoTlvCarrier(List<JsonObject> cityVvoTlv) {
        ListCarrier listCarriers = new ListCarrier(cityVvoTlv);
        listCarriers.getStringTimeList();
        listCarriers.setJsonToDataStorage();
        listCarriers.getMinimumFlightTime();
    }

    @Override
    public void executeVvoTlvCarriers(List<JsonObject> objectslist) {
        CarrierHandler carrierHandler = new CarrierHandler(objectslist);
        List<JsonObject> cityVvoTlv = carrierHandler.cityVvoTlv();
        ListCarrier listCarriers = new ListCarrier(cityVvoTlv);
        listCarriers.getStringTimeList();
        listCarriers.setJsonToDataStorage();
        listCarriers.getDifference();
    }
}
