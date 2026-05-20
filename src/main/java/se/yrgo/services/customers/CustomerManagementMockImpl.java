package se.yrgo.services.customers;

import java.util.*;

import org.springframework.stereotype.Service;

import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;


@Service
public class CustomerManagementMockImpl implements CustomerManagementService {

    private Map<String, Customer> customerMap = new HashMap<>();

    public CustomerManagementMockImpl() {
        customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
        customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
        customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        customerMap.put(newCustomer.getCustomerId(), newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        customerMap.put(changedCustomer.getCustomerId(), changedCustomer);
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        customerMap.remove(oldCustomer.getCustomerId());
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        Customer c = customerMap.get(customerId);
        if (c == null) {
            throw new CustomerNotFoundException("Customer not found: " + customerId);
        }
        return c;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> result = new ArrayList<>();

        for (Customer c : customerMap.values()) {
            if (c.getCompanyName() != null &&
                    c.getCompanyName().equalsIgnoreCase(name)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        return findCustomerById(customerId);
    }

    @Override
    public void recordCall(String customerId, Call call) {

        Customer customer = customerMap.get(customerId);

        if (customer != null) {
            customer.addCall(call);
            return;
        }

        throw new RuntimeException("Customer not found");
    }
}