package com.dekeyserjarno.hoefsmidapp.objects.user;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.util.EncryptionUtil;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;
    @Convert(converter = EncryptionUtil.class)
    private String username;
    @Convert(converter = EncryptionUtil.class)
    private String password;
    private String VatNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address")
    private Address address;
    @Enumerated(EnumType.STRING)
    private Rank rank;

    private String email;

    private String phoneNumber;

    private String firstname;

    private String lastname;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "buyer")
//    private List<Invoice> soldTo_invoices;
//
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
//    private List<Invoice> receivedInvoices;
    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", VatNumber='" + VatNumber + '\'' +
                ", address=" + address +
                ", rank=" + rank +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVatNumber() {
        return VatNumber;
    }

    public void setVatNumber(String vatNumber) {
        VatNumber = vatNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
