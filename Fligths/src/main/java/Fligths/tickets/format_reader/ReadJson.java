package Fligths.tickets.format_reader;

import javax.json.*;
import java.io.*;

public class ReadJson {
    private JsonObject jsonObject;

    public JsonObject jsonCarriers(){
        try {
            FileReader fileReader = new FileReader("src/main/resources/tickets.json");
            JsonReader jsonReader = Json.createReader(fileReader);
            jsonObject = jsonReader.readObject();
        }catch (IOException e){
            System.out.println("Отсутствует файл");
        }catch (JsonException ex){
            String jsonException = ex.toString();
            System.out.println(jsonException);
            System.out.println("Не правильный формат файла json");
        }
        return jsonObject;
    }
}
