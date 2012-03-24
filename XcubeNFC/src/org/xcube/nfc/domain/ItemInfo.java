package org.xcube.nfc.domain;

/**
 * Represents one (tagged) item
 * 
 * @author pete
 */
public class ItemInfo {

    private String upc;
    private String name;
    private String type;
    private String origin;
    private String quantity;
    private NutritionInfo per100g;
    private NutritionInfo each;

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
     * @return nutrition information per 100 grams
     */
    public NutritionInfo getPer100g() {
        return per100g;
    }

    /**
     * @param per100g nutrition information per 100 grams
     */
    public void setPer100g(NutritionInfo per100g) {
        this.per100g = per100g;
    }

    /**
     * @return item type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type item type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return item country of origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin item country of origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return quantity of products in the item
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity number of products in the item
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return nutrition information per item
     */
    public NutritionInfo getEach() {
        return each;
    }

    /**
     * @param each nutrition information per item
     */
    public void setEach(NutritionInfo each) {
        this.each = each;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((upc == null) ? 0 : upc.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof ItemInfo)) {
            return false;
        }
        ItemInfo other = (ItemInfo) obj;
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

        return String.format("ItemInfo [upc = %s, name = %s, type = %s, origin = %s, quantity = %s," 
                + " per100g = %s, each = %s]", upc, name, type, origin, quantity, per100g, each);
    }
}
