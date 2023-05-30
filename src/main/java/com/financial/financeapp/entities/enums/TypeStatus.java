package com.financial.financeapp.entities.enums;

public enum TypeStatus {

    //Income
    INCOME_L(100),
    INCOME_M(200),
    ACCOUNT(300),

    //Outcome
    HOUSING(1100),
    FOOD(1200),
    PERSONAL(1300),
    HEALTH(1400),
    TRANSPORTATION(1500),
    GIFTS(1600),
    ENTERTAINMENT(1700),
    GOALS(1800),
    EDUCATION(1900),
    ESSENTIALS(2000),
    SAVINGS(2100),
    OTHER(2200);

    private Integer code;

    TypeStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static TypeStatus valueOf(int code) {
        for (TypeStatus value : TypeStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TypeStatus code");
    }

}
