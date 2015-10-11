package dataModel;

import java.util.ArrayList;

/**
 * Created by mordreth on 10/11/15.
 */
public class Company {
    private String name;
    private String siteURL;
    private long phone;
    private String email;
    private ArrayList<String> products;
    private String clasification;

    public Company(String name, String clasification, ArrayList<String> products, String email, long phone, String siteURL) {
        this.name = name;
        this.clasification = clasification;
        this.products = products;
        this.email = email;
        this.phone = phone;
        this.siteURL = siteURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }
}