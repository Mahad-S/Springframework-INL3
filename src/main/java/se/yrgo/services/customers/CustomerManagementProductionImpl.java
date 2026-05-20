package se.yrgo.services.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.yrgo.dataaccess.CustomerDao;
import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;

@Service
public class CustomerManagementProductionImpl implements CustomerManagementService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public void newCustomer(Customer newCustomer) {
        customerDao.create(newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        customerDao.update(changedCustomer);
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        customerDao.delete(oldCustomer);
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getFullCustomerDetail(customerId);
        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        try {
            customerDao.addCall(callDetails, customerId);
        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}