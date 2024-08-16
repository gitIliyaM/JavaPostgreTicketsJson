package Fligths.tickets.postgresql_req_res;

import Fligths.tickets.data_storage.DataStorage;
import Fligths.tickets.source_patterns.DateTimePatterns;

import javax.json.JsonObject;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Carriers implements CarriersMethods{
    private String string;
    private String deleteTableCarriers = "\"Carriers\"";
    private String deleteTableTimeAsc = "\"CarriersTimeAsc\"";
    private String sqlTableCarriers = """
        CREATE TABLE if not exists "Carriers"(
        id SERIAL PRIMARY KEY,
        origin varchar(50) not null,
        origin_name varchar(50) not null,
        destination varchar(50) not null,
        destination_name varchar(50) not null,
        departure_date varchar(20) not null,
        departure_time varchar(20) not null,
        arrival_date varchar(20) not null,
        arrival_time varchar(20) not null,
        carrier varchar(50) not null,
        stops integer not null,
        price numeric not null
        );""";
    private String sqlTableTimeAsc = """
        CREATE TABLE if not exists "CarriersTimeAsc"(
        id SERIAL PRIMARY KEY,
        origin varchar(50) not null,
        origin_name varchar(50) not null,
        destination varchar(50) not null,
        destination_name varchar(50) not null,
        departure_date varchar(20) not null,
        departure_time varchar(20) not null,
        arrival_date varchar(20) not null,
        arrival_time varchar(20) not null,
        carrier varchar(50) not null,
        stops integer not null,
        price numeric not null,
        min_time time not null
        );""";
    private String sqlTableAllCarriers = """
        CREATE TABLE if not exists "allCarrier"(
        id SERIAL PRIMARY KEY,
        origin varchar(50) not null,
        origin_name varchar(50) not null,
        destination varchar(50) not null,
        destination_name varchar(50) not null,
        departure_date varchar(20) not null,
        departure_time varchar(20) not null,
        arrival_date varchar(20) not null,
        arrival_time varchar(20) not null,
        carrier varchar(50) not null,
        stops integer not null,
        price numeric not null,
        time time not null
        );""";
    public Carriers() {}

    @Override
    public String sqlInsertCarriers(JsonObject jo){
        return "insert into" + "\"" + jo.getString("carrier") + "\"" + "(origin, origin_name, destination, destination_name, departure_date, departure_time, arrival_date, arrival_time, carrier, stops, price) values"+
                "('"+jo.getString("origin")+"',"+
                "'"+jo.getString("origin_name")+"',"+
                "'"+jo.getString("destination")+"',"+
                "'"+jo.getString("destination_name")+"',"+
                "'"+jo.getString("departure_date")+"',"+
                "'"+jo.getString("departure_time")+"',"+
                "'"+jo.getString("arrival_date")+"',"+
                "'"+jo.getString("arrival_time")+"',"+
                "'"+jo.getString("carrier")+"',"+
                "'"+jo.getInt("stops")+"',"+
                "'"+jo.getInt("price")+"')";
    }

    @Override
    public String sqlInsertCarriersTime(JsonObject jo, String time) {
        return "insert into" + "\"" + jo.getString("carrier") + "Time"+ "\"" + "(origin, origin_name, destination, destination_name, departure_date, departure_time, arrival_date, arrival_time, carrier, stops, price, min_time) values"+
            "('"+jo.getString("origin")+"',"+
            "'"+jo.getString("origin_name")+"',"+
            "'"+jo.getString("destination")+"',"+
            "'"+jo.getString("destination_name")+"',"+
            "'"+jo.getString("departure_date")+"',"+
            "'"+jo.getString("departure_time")+"',"+
            "'"+jo.getString("arrival_date")+"',"+
            "'"+jo.getString("arrival_time")+"',"+
            "'"+jo.getString("carrier")+"',"+
            "'"+jo.getInt("stops")+"',"+
            "'"+jo.getInt("price")+"',"+
            "'"+time+"')";
    }

    @Override
    public String sqlInsertAllCarriers(JsonObject jo, String time) {
        return "insert into" + "\"allCarrier\"" + "(origin, origin_name, destination, destination_name, departure_date, departure_time, arrival_date, arrival_time, carrier, stops, price, time) values"+
            "('"+jo.getString("origin")+"',"+
            "'"+jo.getString("origin_name")+"',"+
            "'"+jo.getString("destination")+"',"+
            "'"+jo.getString("destination_name")+"',"+
            "'"+jo.getString("departure_date")+"',"+
            "'"+jo.getString("departure_time")+"',"+
            "'"+jo.getString("arrival_date")+"',"+
            "'"+jo.getString("arrival_time")+"',"+
            "'"+jo.getString("carrier")+"',"+
            "'"+jo.getInt("stops")+"',"+
            "'"+jo.getInt("price")+"',"+
            "'"+time+"')";
    }

    @Override
    public String setSqlTableCarriers() {
        return sqlTableCarriers;
    }
    @Override
    public String setSqlTableTimeAsc(){
        return sqlTableTimeAsc;
    }

    @Override
    public String setSqlTableAllCarriers() {
        return sqlTableAllCarriers;
    }

    @Override
    public String sqlAscSelect(String carriers){
        return "select id, origin, origin_name, destination, destination_name, departure_date, departure_time, arrival_date, arrival_time, carrier, stops, price, min_time from " + "\""+carriers+"Time"+"\"" + " order by min_time asc";
    }

    @Override
    public String deleteCarriers(){
        return deleteTableCarriers;
    }
    @Override
    public String deleteTimeAsc(){
        return deleteTableTimeAsc;
    }
    @Override
    public String sqlSelect(){
        return "select * from \"CarriersTimeAsc\"";
    }
    @Override
    public String sqlSelectAll(){
        return "select * from \"allCarrier\"";
    }

    @Override
    public String sqlSelectCount() {
        return "select count(1) from \"allCarrier\"";
    }

    @Override
    public String getTimeDifference(JsonObject jo) {
        LocalTime timeDeparture = LocalTime.parse(jo.getString("departure_time"), DateTimePatterns.getTimeFormatter());
        LocalTime timeArrival = LocalTime.parse(jo.getString("arrival_time"), DateTimePatterns.getTimeFormatter());
        Duration durationTime = Duration.between(timeDeparture, timeArrival);
        String hh = String.valueOf(durationTime.toHoursPart());
        String mm = String.valueOf(durationTime.toMinutesPart());
        return hh + ":" + mm;
    }
}
