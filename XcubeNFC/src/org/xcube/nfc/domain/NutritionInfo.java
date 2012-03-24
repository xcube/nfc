package org.xcube.nfc.domain;

/**
 * Item nutrition information
 * 
 * @author pete
 */
public class NutritionInfo {

    private String calories;
    private String protein;
    private String fat;
    private String carbohydrate;
    private String fibre;
    private String salt;

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(String carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getFibre() {
        return fibre;
    }

    public void setFibre(String fibre) {
        this.fibre = fibre;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {

        return String.format("NutritionInfo [calories = %s, protein = %s, fat = %s, " 
                + "carbohydrate = %s, fibre = %s, salt = %s", 
                calories, protein, fat, carbohydrate, fibre, salt);
    }
}
