package Fligths.tickets.data_processor;

import Fligths.tickets.source_patterns.DateTimePatterns;

import javax.json.JsonObject;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DateTimeProcessor {
    private List<JsonObject> objectList;
    private List<String> stringList = new ArrayList<>();;

    public DateTimeProcessor(List<JsonObject> list) {
        this.objectList = list;
        iteratorJsonObjects();
    }

    public void iteratorJsonObjects(){
        for(JsonObject jo: objectList){
            LocalTime timeDeparture = LocalTime.parse(jo.getString("departure_time"), DateTimePatterns.getTimeFormatter());
            LocalTime timeArrival = LocalTime.parse(jo.getString("arrival_time"), DateTimePatterns.getTimeFormatter());
            Duration durationTime = Duration.between(timeDeparture, timeArrival);
            stringList.add(getStringDurationTime(durationTime));
        }
    }
    String getStringDurationTime(Duration durationTime){
        String hh = String.valueOf(durationTime.toHoursPart());
        String mm = String.valueOf(durationTime.toMinutesPart());
        return hh + ":" + mm;
    }
    public List<String> getStringList() {
        return stringList;
    }
}
