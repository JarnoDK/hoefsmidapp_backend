package com.dekeyserjarno.hoefsmidapp.controller;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Company;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.InvoiceItem;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.InvoiceLine;
import com.dekeyserjarno.hoefsmidapp.objects.user.Address;
import com.dekeyserjarno.hoefsmidapp.objects.user.Rank;
import com.dekeyserjarno.hoefsmidapp.objects.user.User;
import com.dekeyserjarno.hoefsmidapp.util.ExcelService;
import com.dekeyserjarno.hoefsmidapp.util.InvoiceItemUtil;
import com.dekeyserjarno.hoefsmidapp.util.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceUtil invoiceUtil;
    @Autowired
    InvoiceItemUtil invoiceItemUtil;
    @Autowired
    ExcelService excelService;

    @GetMapping("getInvoices")
    public List<Invoice> getAll(){
        List<Invoice> invoices = invoiceUtil.getAllInvoices();
        return invoices;
    }

    @GetMapping("calculatePrice")
    public double calculatePriceOfInvoice(int id){
        return invoiceUtil.getInvoiceById(id).calculatePrice();
    }

    @PutMapping("addItem")
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem){
        return invoiceItemUtil.addInvoiceItem(invoiceItem);
    }

    @PutMapping("add")
    public Invoice addInvoice(@RequestBody Invoice invoice){

        Invoice invoice1 = new Invoice();
        User buyer = new User();
        buyer.setUsername("JarnoDk");
        buyer.setRank(Rank.employee);
        buyer.setPassword("Wachtwoord");
        buyer.setVatNumber("123456");


        List<InvoiceLine> lines = new ArrayList<>();
        InvoiceItem item1 = new InvoiceItem();

        item1.setName("Warm beslag");
        item1.setEnglishName("Hot slam");
        item1.setUnitPrice(new BigDecimal(35));
        item1.setVtaValue(6);
        InvoiceLine line = new InvoiceLine();
        line.setAmount(2);
        line.setInvoiceItem(item1);
        lines.add(line);

        InvoiceItem item2 = new InvoiceItem();
        item2.setName("hoefijzer vervangen");
        item2.setEnglishName("horseshoe change");
        item2.setUnitPrice(new BigDecimal(20));
        item2.setVtaValue(21);
        line = new InvoiceLine();
        line.setAmount(4);
        line.setInvoiceItem(item2);
        lines.add(line);

        invoice1.setInvoiceLines(lines);

        Address address = new Address();
        address.setCity("Ertvelde");
        address.setHouseNumber("19");
        address.setBusNumber("A");
        address.setPostalCode("9940");
        address.setStreet("Berkstraat");
        address.setProvince("Oost-Vlaanderen");
        address.setCountry("België");
        buyer.setAddress(address);
        buyer.setFirstname("Jarno");
        buyer.setLastname("Dekeyser");
        invoice1.setBuyer(buyer);


        User seller = new User();

        seller.setUsername("EnjoDk");
        seller.setRank(Rank.employee);
        seller.setPassword("Wachtwoord");
        seller.setVatNumber("123456");
        seller.setFirstname("Enjo");
        seller.setLastname("Dekeyser");

        address = new Address();
        address.setCity("Ertvelde");
        address.setHouseNumber("19");
        address.setBusNumber("A");
        address.setPostalCode("9940");
        address.setStreet("Molenstraat");
        address.setProvince("Oost-Vlaanderen");
        address.setCountry("België");

        seller.setAddress(address);


        invoice1.setSeller(seller);

        Company company = new Company();
        company.setAccountNumber("BE123456789");
        company.setAddress(address);
        company.setName("Hoefsmid enjo");
        company.setLogoPath("xxxx");
        company.setVtaNumber("VTANumber");

        invoice1.setCompany(company);
        invoice1.setDate(LocalDateTime.now());
        invoice1.setExpirationDate(LocalDateTime.now().plusDays(30));

        invoice1.setFollowNumber(invoiceUtil.createFollowNumber(invoice));
        return invoiceUtil.addInvoice(invoice1);
    }

    @GetMapping("createFile")
    public void addInvoice(int id){


        Invoice invoice1 = invoiceUtil.getInvoiceById(id);

        try {
            excelService.createInvoice(invoice1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("template")
    public void importExcel(int id){


        Invoice invoice1 = invoiceUtil.getInvoiceById(id);

        try {
            excelService.transformExcel(invoice1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
