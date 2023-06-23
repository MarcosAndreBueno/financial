package com.financial.financeapp.entities.enums;

public enum CategoryStatus {

    //Income
    SALARY(101),
    SIDE_GIGS(102),
    COMMISSIONS(103),
    INVESTMENTS(104),
    MONEY_GIFTS(105),

    //Outcome
    HOUSING_REPAIRS(1101),
    HOUSING_TAXES(1102),
    HOUSING_RENT(1103),
    FOOD_DELIVERY(1201),
    FOOD_SUPERMARKET(1202),
    FOOD_STREET(1203),
    FOOD_RESTAURANT(1204),
    FOOD_BAKERY(1205),
    PERSONAL_CLOTHING(1301),
    PERSONAL_SHOES(1302),
    PERSONAL_COSMETICS(1303),
    PERSONAL_HAIRCUT(1304),
    PERSONAL_SALON(1305),
    HEALTH_OPTHALMOLOGIST(1401),
    HEALTH_DENTIST(1402),
    HEALTH_INSURANCE(1403),
    HEALTH_MEDICINES(1404),
    TRANSPORTATION_PUBLIC(1501),
    TRANSPORTATION_TAXI(1502),
    TRANSPORTATION_PERSONAL(1503),
    TRANSPORTATION_PARKING(1504),
    GIFTS_FAMILY(1601),
    GIFTS_FRIENDS(1602),
    ENTERTAINMENT_CINEMA(1701),
    ENTERTAINMENT_THEATER(1702),
    ENTERTAINMENT_MUSIC(1703),
    ENTERTAINMENT_FRATERNIZE(1704),
    ENTERTAINMENT_SUBSCRIPTIONS(1705),
    GOALS_HOUSE(1801),
    EDUCATION_SUPPLIES(1901),
    EDUCATION_BOOKS(1902),
    ESSENTIALS_INTERNET(2001),
    ESSENTIALS_PHONE(2002),
    ESSENTIALS_ELETRICITY(2003),
    ESSENTIALS_WATER(2004),
    SAVINGS_BANK(2101),
    SAVINGS_TRIP(2102),
    OTHER(2201);

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
