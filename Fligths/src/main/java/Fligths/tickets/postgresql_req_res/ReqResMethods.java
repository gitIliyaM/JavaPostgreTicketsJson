package Fligths.tickets.postgresql_req_res;

import javax.json.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReqResMethods {
    void setTablesCarriers();
    void setDataCarriers();
    void selectTimeAsc();
    String getVvoTlvMinTime(ResultSet resultSetAsc);
    int getAverage();
    int getMedian();
    void getDifference();
    void deleteTableCarriers();
}
