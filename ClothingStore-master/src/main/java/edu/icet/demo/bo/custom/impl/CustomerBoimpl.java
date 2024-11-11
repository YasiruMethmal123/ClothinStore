package edu.icet.demo.bo.custom.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.demo.bo.custom.CustomerBo;
import edu.icet.demo.dao.custom.impl.CustomerDaoImpl;
import edu.icet.demo.entity.CustomerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerBoimpl implements CustomerBo {


        CustomerDaoImpl customerDaoImpl = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

        public String generateCustomerId(){

            String lastCustomerId = customerDaoImpl.getLatestId();
            if (lastCustomerId==null){
                return "C0001";
            }
            int number = Integer.parseInt(lastCustomerId.split("C")[1]);
            number++;
            return String.format("C%04d", number);
        }

        public boolean insertCustomer(Customer customer) {

            CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);

            return  customerDaoImpl.insert(customerEntity);

        }

        public ObservableList<Customer> getAllCustomer() {
            ObservableList<CustomerEntity> customerEntities = customerDaoImpl.searchAll();

            ObservableList<Customer> customerList = FXCollections.observableArrayList();

            customerEntities.forEach(customerEntity -> {
                customerList.add(new ObjectMapper().convertValue(customerEntity, Customer.class));
            });
            return customerList;
        }

        public boolean isValidEmail(String email){
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }

        public Customer getUserById(String id) {
            CustomerEntity customerEntity = customerDaoImpl.search(id);

            return new ObjectMapper().convertValue(customerEntity, Customer.class);
        }

        public ObservableList<String> getAllCustomerIds(String id) {
            ObservableList<CustomerEntity> customerEntities = customerDaoImpl.searchAllByEmpId(id);
            ObservableList<String> idList = FXCollections.observableArrayList();

            customerEntities.forEach(customerEntity -> {
                idList.add(customerEntity.getId());
            });
            return idList;
        }

        public boolean updateCustomer(Customer customer) {
            return customerDaoImpl.update(new ObjectMapper().convertValue(customer, CustomerEntity.class));
        }

        public boolean deleteCustomerById(String id) {
            return customerDaoImpl.delete(id);
        }

        public ObservableList<Customer> getAllCustomerByEmpId(String id) {
            ObservableList<CustomerEntity> customerEntities = customerDaoImpl.getCustomersByEmpId(id);
            ObservableList<Customer> customers = FXCollections.observableArrayList();
            customerEntities.forEach(customerEntity -> {
                customers.add(new ObjectMapper().convertValue(customerEntity, Customer.class));
            });
            return customers;
        }

        public ObservableList<String> getAllCustomerId() {
            ObservableList<CustomerEntity> customerEntities = customerDaoImpl.searchAll();
            ObservableList<String> idList = FXCollections.observableArrayList();
            customerEntities.forEach(customerEntity -> {
                idList.add(customerEntity.getId());
            });
            return idList;
        }
    }
}
