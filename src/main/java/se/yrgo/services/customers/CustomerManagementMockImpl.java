package se.yrgo.services.customers;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import se.yrgo.domain.Call;
import se.yrgo.domain.Customer;
import java.util.Collection;
import se.yrgo.domain.Action;
import se.yrgo.domain.Customer;



public class CustomerManagementMockImpl implements CustomerManagementService {
    private HashMap<String, Customer> customerMap;

    public CustomerManagementMockImpl() {
        customerMap = new HashMap<String, Customer>();
        customerMap.put("OB74", new Customer("OB74", "Fargo Ltd", "some notes"));
        customerMap.put("NV10", new Customer("NV10", "North Ltd", "some other notes"));
        customerMap.put("RM210", new Customer("RM210", "River Ltd", "some more notes"));
    }

    private List<Customer> customers = new ArrayList<>();
    @Override
    public void newCustomer(Customer newCustomer) {
        for (Customer customer : customers );

    }

    @Override
    public void updateCustomer(Customer changedCustomer) {


    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        // TODO Auto-generated method stub

    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> result = new ArrayList<>();

        for (Customer c : customers) {
            if (c.getCompanyName() != null &&
                    c.getCompanyName().equalsIgnoreCase(name)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        return null;
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
