package com.dekeyserjarno.hoefsmidapp.objects.invoice;


import javax.persistence.*;

@Entity
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item")
    private InvoiceItem invoiceItem;
    private int amount;
    public InvoiceLine(){}
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }
    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
