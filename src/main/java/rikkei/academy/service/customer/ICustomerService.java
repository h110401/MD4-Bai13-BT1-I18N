package rikkei.academy.service.customer;

import rikkei.academy.model.Customer;
import rikkei.academy.model.Province;
import rikkei.academy.service.IGenericService;

public interface ICustomerService extends IGenericService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}
