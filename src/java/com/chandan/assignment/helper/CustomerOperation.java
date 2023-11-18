package com.chandan.assignment.helper;

import com.chandan.assignment.entities.Customer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

public class CustomerOperation {

    public static List<Customer> getCustomerList(String token) {
        List<Customer> list = new ArrayList<>();
        try {
            var url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
            var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Authorization", "Bearer " + token).header("Content-Type", "application/json").build();
            var client = HttpClient.newBuilder().build();
            var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            JSONArray getArray = new JSONArray(resp.body());

            for (int i = 0; i < getArray.length(); i++) {
                JSONObject resObj = getArray.getJSONObject(i);
                Customer customer = new Customer();

                customer.setUuid(resObj.getString("uuid"));
                customer.setFirstName(resObj.getString("first_name"));
                customer.setLastName(resObj.getString("last_name"));
                customer.setStreet(resObj.getString("street")); 
                customer.setAddress(resObj.getString("address"));
                customer.setCity(resObj.getString("city"));
                customer.setState(resObj.getString("state")); 
                customer.setEmail(resObj.getString("email"));
                customer.setPhone(resObj.getString("phone"));
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
