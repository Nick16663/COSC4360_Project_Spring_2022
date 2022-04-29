public class Customer {
    private String customerName;
    private String streetName;
    private String zipCode;
    private String state;
    private String city;

    public Route(String streetName, String zipCode, String state, String city, String customerName) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.customerName = customerName;
    }

    private String getStreetName() {
        return streetName;
    }

    private String getZipCode() {
        return zipCode;
    }

    private String getState() {
        return state;
    }

    private String getCity() {
        return city;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String displayAddress() {
        return "Address: " + this.streetName + " " + this.city + ", " + this.state + " " + this.zipCode;
    }

}
