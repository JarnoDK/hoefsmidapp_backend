package com.dekeyserjarno.hoefsmidapp.util.Repositories;

import com.dekeyserjarno.hoefsmidapp.objects.invoice.Invoice;
import com.dekeyserjarno.hoefsmidapp.objects.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {


}
