package edu.icet.demo.controller.supplier;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
public class SupplierController {
        @FXML
        private JFXButton btnNewSupplier;

        @FXML
        private TableColumn<?, ?> colCompany;

        @FXML
        private TableColumn<?, ?> colEmail;

        @FXML
        private TableColumn<?, ?> colProductName;

        @FXML
        private TableColumn<?, ?> colSupId;

        @FXML
        private TableColumn<?, ?> colSupName;

        @FXML
        private Label lblCategory;

        @FXML
        private Label lblSupId;

        @FXML
        private JFXTextField txtCompany;

        @FXML
        private JFXTextField txtEmail;

        @FXML
        private JFXTextField txtProductName;

        @FXML
        private JFXTextField txtSupName;
}
