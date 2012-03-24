package org.xcube.nfc.domain;

import java.math.BigDecimal;

/**
 * Represents one (tagged) item
 * 
 * @author pete
 */
public class Item {

    private String upc;
    private String name;
    private int calories;
    private BigDecimal price;

    /**
     * @return universal product code (unique item identifier)
     */
    public String getUpc() {
        return upc;
    }

    /**
     * @param upc universal product code (unique item identifier)
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * @return product/item name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name product/item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return total amount of calories per item
     */
    public int getCalories() {
        return calories;
    }

    /**
     * @param caloires total amount of calories per item
     */
    public void setCalories(int caloires) {
        this.calories = caloires;
    }

    /**
     * @return price per item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price item price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + calories;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((upc == null) ? 0 : upc.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Item)) {
            return false;
        }
        Item other = (Item) obj;
        if (calories != other.calories) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (price == null) {
            if (other.price != null) {
                return false;
            }
        } else if (!price.equals(other.price)) {
            return false;
        }
        if (upc == null) {
            if (other.upc != null) {
                return false;
            }
        } else if (!upc.equals(other.upc)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Item [upc = %s, name = %s, calories = %d price = %d]", 
                name, calories, price);
    }
}
