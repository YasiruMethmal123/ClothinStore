package edu.icet.demo.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.bo.BoFactory;
import edu.icet.demo.bo.custom.impl.CustomerBoimpl;
import edu.icet.demo.util.BoType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController {
    public JFXButton btnlogin;
    public JFXButton btnOtp;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    String selectedId;
    private Object BoType;
    CustomerBoimpl customerBoImpl = BoFactory.getInstance().getBo(edu.icet.demo.util.BoType.CUSTOMER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cusIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cusEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        cusAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));


        customerTable.setItems(customerBoImpl.getAllCustomerByEmpId(id));


            cusNameTxt.setText(user.getName());
            cusAddressTxt.setText(user.getAddress());
            cusEmailTxt.setText(user.getEmail());
            selectedId = (String) newValue;
        });
    }

    @FXML
    void actionBtnAction(ActionEvent event) {

        addCusBtn.setVisible(false);
        actionBtn.setVisible(false);
        cusIdTxt.setVisible(false);
        cusIdLable.setVisible(false);
        cusIdComboBox.setVisible(true);
        deleteBtn.setVisible(true);
        updateCusBtn.setVisible(true);
        String id = EmployeeData.getInstance().getId();
        cusIdComboBox.setItems(customerBoImpl.getAllCustomerIds(id));
    }

    @FXML
    void addCusOnAction(ActionEvent event) {
        if (!cusAddressTxt.getText().equals("") && !cusEmailTxt.getText().equals("")
                && !cusNameTxt.getText().equals("")){
            String id = EmployeeData.getInstance().getId();
            Customer customer = new Customer(cusIdTxt.getText(),
                    cusNameTxt.getText(),
                    cusEmailTxt.getText(),
                    cusAddressTxt.getText(),
                    id);

            boolean isAdd = customerBoImpl.insertCustomer(customer);

            if (isAdd){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added");
                alert.setContentText("Customer Added Successfully..!");
                alert.showAndWait();
                cusIdTxt.setText(customerBoImpl.generateCustomerId());
                cusAddressTxt.setText("");
                cusEmailTxt.setText("");
                cusNameTxt.setText("");
                customerTable.setItems(customerBoImpl.getAllCustomerByEmpId(id));
            }
        }
    }

    @FXML
    void closeBtnAction(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Are you sure want to exit..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

    @FXML
    void deleteBtnAction(ActionEvent event){
        if (!selectedId.equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Customer");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get()==ButtonType.OK){
                boolean isDeleted = customerBoImpl.deleteCustomerById(selectedId);
                if (isDeleted){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Employee Deleted");
                    alert2.setContentText("Employee deleted successfully");
                    cusNameTxt.setText("");
                    cusAddressTxt.setText("");
                    cusEmailTxt.setText("");
                    alert2.showAndWait();
                    String id = EmployeeData.getInstance().getId();
                    customerTable.setItems(customerBoImpl.getAllCustomerByEmpId(id));
                    cusIdComboBox.setItems(customerBoImpl.getAllCustomerIds(id));

                }
            }
        }

    }

    @FXML
    void emailAddressKeyReleased(KeyEvent event) {
        boolean isValidEmail = customerBoImpl.isValidEmail(cusEmailTxt.getText());
        if (!isValidEmail) {
            errorMsgtxt.setVisible(true);
            addCusBtn.setDisable(true);
            updateCusBtn.setDisable(true);
        } else {
            addCusBtn.setDisable(false);
            updateCusBtn.setDisable(false);
            errorMsgtxt.setVisible(false);
        }

    }

    @FXML
    void manageCustomersBtnAction(ActionEvent event) {

        addCusBtn.setVisible(true);
        actionBtn.setVisible(true);
        cusIdTxt.setVisible(true);
        cusIdLable.setVisible(true);
        cusIdComboBox.setVisible(false);
        deleteBtn.setVisible(false);
        updateCusBtn.setVisible(false);
        cusIdTxt.setText(customerBoImpl.generateCustomerId());
    }

    @FXML
    void manageOrdersBtnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(customerWindow,"manageOrder-form.fxml");
    }

    @FXML
    void manageProductsBtnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(customerWindow,"product-form.fxml");

    }

    @FXML
    void manageSuppliersBtnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(customerWindow,"supplier-form.fxml");
    }

    @FXML
    void placeSectionBtnAction(ActionEvent event) throws IOException {
        sceneSwitch.switchScene(customerWindow,"placeOrder-form.fxml");
    }

    @FXML
    void shoppingBagBtnMouseClicked(MouseEvent event) throws IOException {
        sceneSwitch.switchScene(customerWindow,"placeOrder-form.fxml");
    }

    @FXML
    void signOutBtnMouseClicked(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setContentText("Are you sure want to Sign Out..?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            SceneSwitchController.getInstance().switchScene(customerWindow, "dashBoard-form.fxml");
        }
    }

    @FXML
    void updateCusOnAction(ActionEvent event) {
        if (!cusEmailTxt.getText().equals("") && !cusAddressTxt.getText().equals("")
                && !cusNameTxt.getText().equals("")){
            String id = EmployeeData.getInstance().getId();
            Customer customer = new Customer(selectedId,
                    cusNameTxt.getText(),
                    cusEmailTxt.getText(),
                    cusAddressTxt.getText(),
                    id);
            boolean isUpdated = customerBoImpl.updateCustomer(customer);
            if (isUpdated){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Update");
                alert.setContentText("Customer Updated Successfully");
                alert.showAndWait();
                cusEmailTxt.setText("");
                cusAddressTxt.setText("");
                cusNameTxt.setText("");
                customerTable.setItems(customerBoImpl.getAllCustomerByEmpId(id));
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }

    @FXML
    void userBtnMouseClicked(MouseEvent event) throws IOException {

    }
}
