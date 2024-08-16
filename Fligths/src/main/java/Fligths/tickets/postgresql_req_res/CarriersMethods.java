package Fligths.tickets.postgresql_req_res;

import Fligths.tickets.data_storage.DataStorage;

import javax.json.JsonObject;
import java.util.List;

public interface CarriersMethods {
    String sqlInsertCarriers(JsonObject objectsList);
    String sqlInsertCarriersTime(JsonObject objectsList, String time);
    String sqlInsertAllCarriers(JsonObject objectsList, String time);
    String setSqlTableCarriers();
    String setSqlTableTimeAsc();
    String setSqlTableAllCarriers();
    String sqlAscSelect(String carriers);
    String deleteCarriers();
    String deleteTimeAsc();
    String sqlSelect();
    String sqlSelectAll();
    String sqlSelectCount();
    String getTimeDifference(JsonObject jo);
}
