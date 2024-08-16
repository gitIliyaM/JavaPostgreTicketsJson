package Fligths.tickets.postgresql_req_res;

import Fligths.tickets.all_enums.EnumCity;
import Fligths.tickets.data_processor.ObjectsJsonArrayProcessor;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLRequestResponse implements ReqResMethods{
    private String url = "jdbc:postgresql://localhost/db";
    private String user = "user";
    private String pass = "pass";
    private String sqlInsert, sqlTime;
    private List<JsonObject> objectsList = new ArrayList<>();
    private String tableCarrier, tableCarrierTime, commonTableCarriers;
    private String dbFull = "База данных заполнена";
    private String selectAsc;

    public void getArrayJson(JsonObject jsonObject){
        JsonArray jsonArray = jsonObject.getJsonArray("tickets");
        objectsList = new ObjectsJsonArrayProcessor().setObjectsToList(jsonArray);
        setTablesCarriers();
        setDataCarriers();
        selectTimeAsc();

    }
    public HashSet<String> getSetCarriers(){
        HashSet<String> setCarriers = new HashSet<>();
        for(JsonObject jo: objectsList){
            String carrier = jo.getString("carrier");
            setCarriers.add(carrier);
        }
        return setCarriers;
    }

    @Override
    public void setTablesCarriers() {
        Set<String> setCarriers = getSetCarriers();
        for(String carriers: setCarriers){
            tableCarrier = new Carriers().setSqlTableCarriers().replace("Carriers", carriers);
            tableCarrierTime = new Carriers().setSqlTableTimeAsc().replace("CarriersTimeAsc", carriers+"Time");
            commonTableCarriers = new Carriers().setSqlTableAllCarriers();
            try (Connection connection = DriverManager.getConnection(url, user, pass); Statement statement = connection.createStatement()) {
                statement.execute(tableCarrier);
                statement.execute(tableCarrierTime);
                statement.execute(commonTableCarriers);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
    }
    @Override
    public void setDataCarriers() {
        Set<String> setCarriers = getSetCarriers();
        for(String carriers: setCarriers){
            for(JsonObject jo: objectsList){
                if(jo.getString("carrier").contains(carriers)){
                    try(Connection connection = DriverManager.getConnection(url,user,pass)){
                        connection.createStatement().execute(new Carriers().sqlInsertCarriers(jo));
                        String time = new Carriers().getTimeDifference(jo);
                        connection.createStatement().execute(new Carriers().sqlInsertCarriersTime(jo, time));
                        connection.createStatement().execute(new Carriers().sqlInsertAllCarriers(jo, time));
                    } catch (SQLException e){
                        dbFull = e.getMessage();
                    }
                }
            }
        }
        System.out.println(dbFull);
    }
    @Override
    public void selectTimeAsc() {
        Set<String> setCarriers = getSetCarriers();
        for(String carriers: setCarriers){
            selectAsc = new Carriers().sqlAscSelect(carriers);
            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                ResultSet resultSetAsc = connection.createStatement().executeQuery(selectAsc);
                String minTime = getVvoTlvMinTime(resultSetAsc);
                System.out.println(minTime);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
    }
    @Override
    public String getVvoTlvMinTime(ResultSet resultSetAsc) {
        String time = "Минимальное время полета выполнил перевозчик ";
        String error = "Ошибка в коде. ПРОВЕРИТЬ КОД";
        try{
            while (resultSetAsc.next()){
                if(resultSetAsc.getString("origin_name").contains(EnumCity.VVO.nameCity) && resultSetAsc.getString("destination_name").contains(EnumCity.TLV.nameCity)){
                    return time + resultSetAsc.getString("carrier") + " = " + resultSetAsc.getString("min_time");
                }
            }
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        return error;
    }

    @Override
    public int getAverage() {
        String select = new Carriers().sqlSelect();
        int sum = 0;
        int counter = 0;

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            ResultSet resultSetAsc = connection.createStatement().executeQuery(select);
            while (resultSetAsc.next()){
                sum += resultSetAsc.getInt("price");
                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        System.out.println("Средняя цена = " + sum / counter);
        return sum / counter;
    }

    @Override
    public int getMedian() {
        int medianOne = 0, medianTwo = 0, mediana = 0;
        int count;
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            ResultSet resultCount = connection.createStatement().executeQuery(new Carriers().sqlSelectCount());
            resultCount.next();
            count = resultCount.getInt(1);

            ResultSet resultMedian = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(new Carriers().sqlSelectAll());
            if(count % 2 == 0){
                resultMedian.absolute(count / 2);
                medianOne = resultMedian.getInt("price");
                resultMedian.relative(1);
                medianTwo = resultMedian.getInt("price");
                mediana = (medianOne + medianTwo) / 2;
            } else if(count == 1){
                mediana = resultMedian.getInt("price");
            } else {
                resultMedian.absolute(count / 2 + 1);
                mediana = resultMedian.getInt("price");
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        System.out.println("Медиана = " + mediana);
        return mediana;
    }

    @Override
    public void getDifference() {
        int average = getAverage();
        int median = getMedian();
        System.out.println("Разница между средней ценой и медианой = " + (average - median));
        System.out.println("----------------------------------------");
    }
    public int getMediana() {
        int intFirst = 0, intSecond = 0, mediana = 0;
        int counter = 0, count = 1, i = 0;
        String select =  "select * from \"CarriersTimeAsc\" where id = ?";
        String select1 = "select * from \"CarriersTimeAsc\" where id = ?";
        String select2 = "select * from \"CarriersTimeAsc\"";
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            //ResultSet result = connection.createStatement().executeQuery(select1);
            /*Statement statement = connection.createStatement();
            statement.addBatch(select);
            statement.addBatch(select1);
            statement.executeBatch();
            ResultSet s = statement.executeQuery(select2);*/

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            PreparedStatement preparedStatement1 = connection.prepareStatement(select1);
            preparedStatement.setInt(1, 12/2);
            preparedStatement1.setInt(1,12/2 + 1);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet.next() && resultSet1.next()){
                System.out.println(resultSet.getInt("price"));
                System.out.println(resultSet1.getInt("price"));
            }

            /*while (result.next()){
                counter++;
            }
            ResultSet resultSetAsc = connection.createStatement().executeQuery(select);
            if(counter % 2 == 0){
                while(resultSetAsc.next()) {

                }
            }else {
                while(resultSetAsc.next()){
                    if(counter / 2 == count){
                        mediana = resultSetAsc.getInt("price");
                    }
                }
            }*/
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        //System.out.println("Медиана = " + mediana);
        return mediana;
    }
    @Override
    public void deleteTableCarriers() {
        String delete = "truncate table \"Carriers\"";
        //Connection connection = DriverManager.getConnection(url, user, pass);
        //connection.createStatement().execute(delete);
    }
}
