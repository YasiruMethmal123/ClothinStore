package edu.icet.demo.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.demo.bo.custom.impl.UserBoImpl;
import edu.icet.demo.model.User;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class Admincontroller {
    public Label lblCategory;
    public JFXButton btnNewEmployee;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeId;
    public JFXButton btnExit;
    public JFXTextField txtSpecialNotes;

    UserBoImpl userBo = new UserBoImpl();
    String selectedId;
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Object newValue = null;
        User user = UserBoImpl.getUserById((String) newValue);

            txtEmployeeName.setText(user.getName());
            txtEmployeeId.setText(user.getUserId());

            selectedId = (String) newValue;

        });



}
