package org.seoul.kk.entity.constant;

public enum TravelProperty {

    TRAVELER(1, "여행자"),
    BEGGER(2, "꽃거지"),
    PROGRAMMER(3, "코딩족");

    private String type;
    private int code;

    TravelProperty() {
    }

    TravelProperty(int code, String type) {
        this.type = type;
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

}
