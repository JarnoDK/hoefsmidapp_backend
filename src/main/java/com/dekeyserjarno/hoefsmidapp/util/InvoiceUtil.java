package com.dekeyserjarno.hoefsmidapp.util;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.util.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceUtil {


    @Autowired
    InvoiceRepository invoiceRepo;

    public InvoiceUtil() {

    }

    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        invoiceRepo.findAll().forEach(s -> invoices.add(s));
        return invoices;
    }

    public Invoice getInvoiceById(int id) {
        return invoiceRepo.findById(id).orElse(null);
    }


    public Invoice addInvoice(Invoice invoice) {
        invoiceRepo.save(invoice);
        return invoice;
    }

    public String createFollowNumber(Invoice invoice) {

        Invoice last = invoiceRepo.findMaxInvoiceNumber();
        String result = "";
        if (last == null || last.getDate().getYear() != LocalDateTime.now().getYear()) {
            result =  String.format("%04d-%04d",invoice.getDate().getYear(), 1);
            return result;
        }
        String[] split = last.getFollowNumber().split("-");
        int number = Integer.parseInt(split[1]);
        result = String.format("%s-%04d",split[0], number);
        return result;
    }
}
