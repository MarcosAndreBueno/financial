package com.financial.financeapp.entities.enums;

public enum CategoryStatus {

    //Income
    SALARY(1),
    SIDE_GIGS(2),
    COMMISSIONS(3),
    INVESTMENTS(4),
    MONEY_GIFTS(5),

    //Outcome
    HOUSING_REPAIRS(6),
    HOUSING_TAXES(7),
    HOUSING_RENT(8),
    FOOD_DELIVERY(9),
    FOOD_SUPERMARKET(10),
    FOOD_STREET(11),
    FOOD_RESTAURANT(12),
    FOOD_BAKERY(13),
    PERSONAL_CLOTHING(14),
    PERSONAL_SHOES(15),
    PERSONAL_COSMETICS(16),
    PERSONAL_HAIRCUT(17),
    PERSONAL_SALON(18),
    HEALTH_OPTHALMOLOGIST(19),
    HEALTH_DENTIST(20),
    HEALTH_INSURANCE(21),
    HEALTH_MEDICINES(22),
    TRANSPORTATION_PUBLIC(23),
    TRANSPORTATION_TAXI(24),
    TRANSPORTATION_PERSONAL(25),
    TRANSPORTATION_PARKING(26),
    GIFTS_FAMILY(27),
    GIFTS_FRIENDS(28),
    ENTERTAINMENT_CINEMA(29),
    ENTERTAINMENT_THEATER(30),
    ENTERTAINMENT_MUSIC(31),
    ENTERTAINMENT_FRATERNIZE(32),
    ENTERTAINMENT_SUBSCRIPTIONS(33),
    GOALS_HOUSE(34),
    EDUCATION_SUPPLIES(35),
    EDUCATION_BOOKS(36),
    ESSENTIALS_INTERNET(37),
    ESSENTIALS_PHONE(38),
    ESSENTIALS_ELETRICITY(39),
    ESSENTIALS_WATER(40),
    SAVINGS_BANK(41),
    SAVINGS_TRIP(42),
    OTHER(43);

    private int code;

    CategoryStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CategoryStatus valueOf(int code) {
        for (CategoryStatus value : CategoryStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid CategoryStatus code");
    }

}
