package se.yrgo.dataaccess;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import se.yrgo.domain.Customer;
import se.yrgo.domain.Call;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM CUSTOMER";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Customer c = new Customer();
            c.setCustomerId(rs.getString("CUSTOMER_ID"));
            c.setCompanyName(rs.getString("COMPANY_NAME"));
            return c;
        });
    }

    @Override
    public void create(Customer customer) {

        String sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, EMAIL, TELEPHONE, NOTES) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                customer.getCustomerId(),
                customer.getCompanyName(),
                customer.getEmail(),
                customer.getTelephone(),
                customer.getNotes());
    }

    @Override
    public Customer getById(String customerId) {

        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";

        return jdbcTemplate.queryForObject(sql,
                new Object[]{customerId},
                (rs, rowNum) -> {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getString("CUSTOMER_ID"));
                    c.setCompanyName(rs.getString("COMPANY_NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    c.setTelephone(rs.getString("TELEPHONE"));
                    c.setNotes(rs.getString("NOTES"));
                    return c;
                });
    }

    @Override
    public void update(Customer customer) {

        String sql = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, TELEPHONE=?, NOTES=? WHERE CUSTOMER_ID=?";

        jdbcTemplate.update(sql,
                customer.getCompanyName(),
                customer.getEmail(),
                customer.getTelephone(),
                customer.getNotes(),
                customer.getCustomerId());
    }

    @Override
    public void delete(Customer customer) {

        String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";

        jdbcTemplate.update(sql, customer.getCustomerId());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) {

        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{customerId},
                (rs, rowNum) -> {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getString("CUSTOMER_ID"));
                    c.setCompanyName(rs.getString("COMPANY_NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    c.setTelephone(rs.getString("TELEPHONE"));
                    c.setNotes(rs.getString("NOTES"));
                    return c;
                }
        );
    }

    @Override
    public List<Customer> getByName(String name) {

        String sql = "SELECT * FROM CUSTOMER WHERE LOWER(COMPANY_NAME) LIKE ?";

        return jdbcTemplate.query(sql,
                new Object[]{"%" + name.toLowerCase() + "%"},
                (rs, rowNum) -> {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getString("CUSTOMER_ID"));
                    c.setCompanyName(rs.getString("COMPANY_NAME"));
                    c.setEmail(rs.getString("EMAIL"));
                    c.setTelephone(rs.getString("TELEPHONE"));
                    c.setNotes(rs.getString("NOTES"));
                    return c;
                });
    }

    @Override
    public void addCall(Call call, String customerId) {

        String sql = "INSERT INTO CALLS (CUSTOMER_ID, TIMEANDDATE, NOTES) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
                customerId,
                call.getTimeAndDate(),
                call.getNotes());
    }

    public void createTables() {

        jdbcTemplate.execute(
                "CREATE TABLE CUSTOMER (" +
                        "CUSTOMER_ID VARCHAR(50) PRIMARY KEY, " +
                        "COMPANY_NAME VARCHAR(100), " +
                        "EMAIL VARCHAR(100), " +
                        "TELEPHONE VARCHAR(50), " +
                        "NOTES VARCHAR(255))"
        );

        jdbcTemplate.execute(
                "CREATE TABLE CALLS (" +
                        "ID INTEGER IDENTITY PRIMARY KEY, " +
                        "CUSTOMER_ID VARCHAR(50), " +
                        "TIMEANDDATE TIMESTAMP, " +
                        "NOTES VARCHAR(255))"
        );
    }
}