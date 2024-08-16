package Fligths.tickets;

import Fligths.tickets.format_reader.ReadJson;
import Fligths.tickets.objects_carriers.ObjectsCarriers;
import Fligths.tickets.postgresql_req_res.SQLRequestResponse;

import javax.json.JsonObject;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        JsonObject jsonObject = new ReadJson().jsonCarriers();

        System.out.println("Используется POJO Data Storage");
        ObjectsCarriers objectsCarriers = new ObjectsCarriers();
        objectsCarriers.getArrayJson(jsonObject);

        System.out.println("Используется Postgresql Data Storage");
        SQLRequestResponse sqlRequestResponse = new SQLRequestResponse();
        sqlRequestResponse.getArrayJson(jsonObject);
    }
}
