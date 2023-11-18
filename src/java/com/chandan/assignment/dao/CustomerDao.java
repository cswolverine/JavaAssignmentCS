package com.chandan.assignment.dao;

import com.chandan.assignment.entities.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDao {

    private Connection con;
    public CustomerDao(Connection con) {
        this.con = con;
    }

    
    public boolean saveCustomer(Customer cus) {
        boolean flag = false;
        try {
            
            String query = "insert into customer(fname, lname, street, address, city, state, email, phone) values(?,?,?,?,?,?,?,?)";
            PreparedStatement psmt = this.con.prepareStatement(query);
            psmt.setString(1, cus.getFirstName());
            psmt.setString(2, cus.getLastName());
            psmt.setString(3, cus.getStreet());
            psmt.setString(4, cus.getAddress());
            psmt.setString(5, cus.getCity());
            psmt.setString(6, cus.getState());
            psmt.setString(7, cus.getEmail());
            psmt.setString(8, cus.getPhone());
            
            psmt.executeUpdate();
            flag = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
