package com.financial.financeapp.entities.enums;

public enum TypeStatus {

    //Income
    INCOME_L(1),
    INCOME_M(2),
    ACCOUNT(3),

    //Outcome
    HOUSING(4),
    FOOD(5),
    PERSONAL(6),
    HEALTH(7),
    TRANSPORTATION(8),
    GIFTS(9),
    ENTERTAINMENT(10),
    GOALS(11),
    EDUCATION(12),
    ESSENTIALS(13),
    SAVINGS(14),
    OTHER(15);

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
