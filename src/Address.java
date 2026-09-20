public class Address {
    private final String street;
    private final String building;
    private final String apartment;

    public Address(String street, String building, String apartment) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return String.format("вул. %s, буд. %s, кв. %s", street, building, apartment);
    }
}