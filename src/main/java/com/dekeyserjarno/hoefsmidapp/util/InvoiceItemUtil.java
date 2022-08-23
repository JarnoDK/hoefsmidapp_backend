package com.dekeyserjarno.hoefsmidapp.util;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.objects.invoice.InvoiceItem;
import com.dekeyserjarno.hoefsmidapp.util.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class InvoiceItemUtil {
    @Autowired
    private ItemRepository itemRepository;

    public InvoiceItem addInvoiceItem(InvoiceItem item){
        boolean original = itemRepository.findById(item.getId()).orElse(null) == null;

        if(!original){
            InvoiceItem orig = itemRepository.findById(item.getId()).get();
            if(item.getVtaValue() <=0){
                item.setVtaValue(orig.getVtaValue());
            }
            if(item.getEnglishName() == null){
                item.setEnglishName(orig.getEnglishName());
            }
            if(item.getUnitPrice() == null){
                item.setUnitPrice(orig.getUnitPrice());
            }
            if(item.getName() == null){
                item.setName(orig.getName());
            }
            item.setOriginal(false);
            if(item == orig){
                return item;
            }
        }
        item.setId(0);
        itemRepository.save(item);
        return item;
    }
}
