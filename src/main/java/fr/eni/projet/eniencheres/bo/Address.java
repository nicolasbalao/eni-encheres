package fr.eni.projet.eniencheres.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {
    private long id;
    @NotBlank
    @Size(min = 1, max = 100)
    private String street;
    @NotBlank
    @Size(min = 3, max = 50)
    private String city;
    @NotBlank
    @Size(min = 2, max = 10)
    private String zipcode;

    public Address() {

    }

    public Address(long id, String street, String city, String zipcode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
