package Fligths.tickets.objects_carriers;

import javax.json.JsonObject;
import java.util.List;

public interface ObjectsCarrier {
    void TK();
    void S7();
    void SU();
    void BA();
    void executeVvoTlvCarrier(List<JsonObject> cityVvoTlv);
    void executeVvoTlvCarriers(List<JsonObject> cityVvoTlv);
}
