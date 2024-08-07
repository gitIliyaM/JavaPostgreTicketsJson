package Fligths.tickets.all_enums;

public enum EnumCity {
    VVO("Владивосток"),
    TLV("Тель-Авив"),
    UFA("Уфа"),
    LRN("Ларнака");

    public final String nameCity;
    EnumCity(String city) {
        this.nameCity = city;
    }
}
