package Fligths.tickets.cargo_carriers;

import Fligths.tickets.comparators.MedianComparator;
import Fligths.tickets.comparators.TimeComparator;
import Fligths.tickets.data_processor.AveragePrice;
import Fligths.tickets.data_processor.DateTimeProcessor;
import Fligths.tickets.data_processor.JsonToDataStorage;
import Fligths.tickets.data_processor.Median;
import Fligths.tickets.data_storage.DataStorage;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCarrier {
    private List<String> listStringTime = new ArrayList<>();
    private List<JsonObject> jsonObjectsList;
    private List<DataStorage> listDataStorage;

    public ListCarrier(List<JsonObject> objectslist) {
        this.jsonObjectsList = objectslist;
    }
    public void getStringTimeList() {
        DateTimeProcessor dateProcessor = new DateTimeProcessor(this.jsonObjectsList);
        this.listStringTime = dateProcessor.getStringList();
    }
    public void setJsonToDataStorage(){
        JsonToDataStorage jsonToDataStorage = new JsonToDataStorage();
        jsonToDataStorage.setJsonObjectsList(this.jsonObjectsList);
        jsonToDataStorage.setListStringTime(this.listStringTime);
        this.listDataStorage = jsonToDataStorage.setToDataStorage();
    }
    public void getMinimumFlightTime(){
        Comparator<DataStorage> timeComparator = new TimeComparator();
        this.listDataStorage.sort(timeComparator);
        System.out.println("Минимальное время полета выполнил перевозчик " + this.listDataStorage.getFirst().getCarrier() + " = " + this.listDataStorage.getFirst().getDurationTime());
    }

    public int getAverage() {
        AveragePrice averagePrice = new AveragePrice(this.jsonObjectsList);
        System.out.println("Средняя цена = " + averagePrice.getAverageNumber());
        return averagePrice.getAverageNumber();
    }
    public int getMedian() {
        Comparator<DataStorage> medianComparator = new MedianComparator();
        this.listDataStorage.sort(medianComparator);
        Median median = new Median(this.listDataStorage);
        System.out.println("Медиана = " + median.getMedianNumber());
        return median.getMedianNumber();
    }
    public void getDifference() {
        int average = getAverage();
        int median = getMedian();
        System.out.println("Разница между средней ценой и медианой = " + (average - median));
        System.out.println("----------------------------------------");
    }


}
