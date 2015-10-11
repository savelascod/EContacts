package dataModel;

/**
 * Created by mordreth on 10/11/15.
 */
public class Company {
    private String name;
    private String siteURL;
    private long phone;
    private String email;
    private String productsAndServices;
    private String clasification;


    public Company(String name, String clasification, String productsAndServices, String email, long phone, String siteURL) {

        this.name = name;
        this.clasification = clasification;
        this.productsAndServices = productsAndServices;
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

    public String getClasification() {
        return clasification;
    }

    public void setClasification(String clasification) {
        this.clasification = clasification;
    }

    public void setProductsAndServices(String productsAndServices) {
        this.productsAndServices = productsAndServices;
    }

    public String getProductsAndServices() {
        return productsAndServices;
    }

}