package edu.icet.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class Order {

    private String id;
    private String cusId;
    private String status;
    private Date date;
    private double amount;
    private String empId;


}
