package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * Created by kobus on 2017/08/08.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BillItem {

    private String description;
    private Double price;
    private int quantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BillItem{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
