package org.seoul.kk.entity.constant;

public enum Classification {

    ADJECTIVE(1,"형용사"),
    NOUN(2,"명사");
    // Review TW
    // 왜 code 값을 1부터 시작하는지?
    // 실제로는 0부터 시작하는 값이 들어가는데??

    private int code;
    private String value;

    Classification(){

    }

    Classification(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }



}
