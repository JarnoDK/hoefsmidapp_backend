package com.dekeyserjarno.hoefsmidapp.util.Repositories;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice,Integer> {

    @Query(value="select * from invoice inv where inv.follow_number = (select max(follow_number) from invoice)",nativeQuery = true)
    Invoice findMaxInvoiceNumber();
}
