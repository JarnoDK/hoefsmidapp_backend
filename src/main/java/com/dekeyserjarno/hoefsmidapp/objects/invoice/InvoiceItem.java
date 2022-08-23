package com.dekeyserjarno.hoefsmidapp.objects.invoice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String englishName;
    private BigDecimal unitPrice;
    private int vtaValue;
    private boolean original = true;

    public InvoiceItem(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getVtaValue() {
        return vtaValue;
    }
    public void setVtaValue(int vtaValue) {
        this.vtaValue = vtaValue;
    }

    public String getEnglishName() {
        return englishName;
    }
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

}
