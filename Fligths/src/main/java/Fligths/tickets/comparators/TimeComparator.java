package Fligths.tickets.comparators;

import Fligths.tickets.data_storage.DataStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class TimeComparator implements Comparator<DataStorage>{
    @Override
    public int compare(DataStorage o1, DataStorage o2) {
        String time1 = o1.getDurationTime();
        String time2 = o2.getDurationTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date data1, data2;
        try {
            data1 = format.parse(time1);
            data2 = format.parse(time2);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return data1.compareTo(data2);
    }
}
