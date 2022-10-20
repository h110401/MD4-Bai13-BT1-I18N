package rikkei.academy.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import rikkei.academy.model.Customer;
import rikkei.academy.model.Province;
import rikkei.academy.repository.ICustomerRepository;

import java.util.Optional;

public class CustomerServiceIMPL implements ICustomerService {


    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }
}
