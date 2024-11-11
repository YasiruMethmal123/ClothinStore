package edu.icet.demo.controller.user;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EmployeeHomeController {
        @FXML
        private JFXButton btnExit;

        @FXML
        private JFXComboBox<String> servicesCmb;
        @FXML
        private JFXButton btnContinue;

        public void loadServiceCmb(JFXComboBox<String> servicesCmb) {


                this.servicesCmb = servicesCmb;
        }

}

