package rikkei.academy.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rikkei.academy.model.Customer;
import rikkei.academy.model.Province;
import rikkei.academy.service.IGenericService;

public interface ICustomerService extends IGenericService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByProvince(Province province, Pageable pageable);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFirstNameContaining(String firstName, Pageable pageable);
}
