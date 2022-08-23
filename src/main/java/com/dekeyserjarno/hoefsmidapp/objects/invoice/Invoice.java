package com.dekeyserjarno.hoefsmidapp.objects.invoice;

import com.dekeyserjarno.hoefsmidapp.objects.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String followNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller")
    private User seller;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer")
    private User buyer;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice")
    private List<InvoiceLine> invoiceLines;
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    private LocalDateTime expirationDate;

//    private List<String> conditions;

    public Invoice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(String followNumber) {
        this.followNumber = followNumber;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double calculatePrice() {
        double price = invoiceLines
                .stream()
                .map(s -> s.getAmount() *
                        (s.getInvoiceItem().getUnitPrice().doubleValue() *
                                (1 + ((double) s.getInvoiceItem().getVtaValue() / 100))))
                .collect(Collectors.summingDouble(s -> s));
        return price;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    //    public List<String> getConditions() {
//        return conditions;
//    }
//
//    public void setConditions(List<String> conditions) {
//        this.conditions = conditions;
//    }
}
