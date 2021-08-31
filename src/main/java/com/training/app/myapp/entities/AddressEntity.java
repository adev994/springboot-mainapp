package com.training.app.myapp.entities;

import com.training.app.myapp.shared.dto.UserDto;

import javax.persistence.*;

@Entity(name="addresses")
public class AddressEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false,length =20)
    private String addressId;

    @Column(nullable = true,length =20)
    private String country;

    @Column(nullable = false,length =20)
    private String postalCode;

    @Column(nullable = false,length =20)
    private String city;

    @Column(nullable = false,length =20)
    private String type;

    @ManyToOne
    @JoinColumn(name="users_id")
    private UserEntity user;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
