package Fligths.tickets.comparators;

import Fligths.tickets.data_storage.DataStorage;

import java.util.Comparator;

public class MedianComparator implements Comparator<DataStorage> {
    @Override
    public int compare(DataStorage o1, DataStorage o2) {
        return o1.getPrice() -  o2.getPrice();
    }
}
