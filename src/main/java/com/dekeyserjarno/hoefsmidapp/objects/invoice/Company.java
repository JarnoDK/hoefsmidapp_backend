package com.dekeyserjarno.hoefsmidapp.objects.invoice;

import com.dekeyserjarno.hoefsmidapp.objects.user.Address;
import com.dekeyserjarno.hoefsmidapp.util.EncryptionUtil;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String logoPath;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address")
    private Address address;
    @Convert(converter = EncryptionUtil.class)
    private String vtaNumber;
    @Convert(converter = EncryptionUtil.class)
    private String accountNumber;

    public Company(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getVtaNumber() {
        return vtaNumber;
    }

    public void setVtaNumber(String vtaNumber) {
        this.vtaNumber = vtaNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
