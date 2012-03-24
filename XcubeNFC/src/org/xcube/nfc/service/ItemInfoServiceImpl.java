package org.xcube.nfc.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.xcube.nfc.domain.ItemInfo;
import org.xcube.nfc.domain.NutritionInfo;
import org.xcube.nfc.util.StringUtil;

public class ItemInfoServiceImpl implements ItemInfoService {

    private static final String ITEM_INFO_ENDPOINT = "http://xcubeserver.herokuapp.com/upc/";

    private static final String ITEM_IMG_KEY = "img";
    private static final String ITEM_TYPE_KEY = "type";
    private static final String ITEM_NAME_KEY = "name";
    private static final String ITEM_ORIGIN_KEY = "origin";
    private static final String ITEM_QUANTITY_KEY = "quantity";
    private static final String ITEM_UPC_KEY = "upc";

    private static final String PER100G_KEY = "per100g";
    private static final String EACH_KEY = "each";

    private static final String NUTRITION_CALORIES_KEY = "calories";
    private static final String NUTRITION_PROTEIN_KEY = "protein";
    private static final String NUTRITION_FAT_KEY = "fat";
    private static final String NUTRITION_CARBOHYDRATE_KEY = "carbohydrate";
    private static final String NUTRITION_FIBRE_KEY = "fibre";
    private static final String NUTRITION_SALT_KEY = "salt";

    @Override
    public ItemInfo getItemInfo(String upc) {
        return httpRequest(upc);
    }

    /**
     * @param upc unique product code
     * @param itemJsonString json response from service
     * @return item domain object
     */
    public ItemInfo getItemDomain(String itemJsonString) {

        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setPer100g(new NutritionInfo());
        itemInfo.setEach(new NutritionInfo());

        if (null == itemJsonString) {
            return itemInfo;
        }

        System.out.println("------- " + itemJsonString);
        try {
            /* main item properties */
            JSONObject jsonItem = new JSONObject(itemJsonString);
            itemInfo.setName(getJsonValue(jsonItem, ITEM_NAME_KEY));
            itemInfo.setUpc(getJsonValue(jsonItem, ITEM_UPC_KEY));
            itemInfo.setImg(getJsonValue(jsonItem, ITEM_IMG_KEY));
            itemInfo.setType(getJsonValue(jsonItem, ITEM_TYPE_KEY));
            itemInfo.setOrigin(getJsonValue(jsonItem, ITEM_ORIGIN_KEY));
            itemInfo.setQuantity(getJsonValue(jsonItem, ITEM_QUANTITY_KEY));

            /* nutrition info per 100 grams */
            if (jsonItem.has(PER100G_KEY)) {
                NutritionInfo per100g = itemInfo.getPer100g();
                JSONObject jsonPer100g = jsonItem.getJSONObject(PER100G_KEY);
                per100g.setCalories(getJsonValue(jsonPer100g, NUTRITION_CALORIES_KEY));
                per100g.setProtein(getJsonValue(jsonPer100g, NUTRITION_PROTEIN_KEY));
                per100g.setFat(getJsonValue(jsonPer100g, NUTRITION_FAT_KEY));
                per100g.setCarbohydrate(getJsonValue(jsonPer100g, NUTRITION_CARBOHYDRATE_KEY));
                per100g.setFibre(getJsonValue(jsonPer100g, NUTRITION_FIBRE_KEY));
                per100g.setSalt(getJsonValue(jsonPer100g, NUTRITION_SALT_KEY));
            }

            /* nutrition info for the whole item */
            if (jsonItem.has(EACH_KEY)) {
                NutritionInfo each = itemInfo.getEach();
                JSONObject jsonEach = jsonItem.getJSONObject(EACH_KEY);
                each.setCalories(getJsonValue(jsonEach, NUTRITION_CALORIES_KEY));
                each.setProtein(getJsonValue(jsonEach, NUTRITION_PROTEIN_KEY));
                each.setFat(getJsonValue(jsonEach, NUTRITION_FAT_KEY));
                each.setCarbohydrate(getJsonValue(jsonEach, NUTRITION_CARBOHYDRATE_KEY));
                each.setFibre(getJsonValue(jsonEach, NUTRITION_FIBRE_KEY));
                each.setSalt(getJsonValue(jsonEach, NUTRITION_SALT_KEY));
                itemInfo.setEach(each);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemInfo;
    }

    /**
     * @param jsonObject
     * @param key
     * @return empty string if key is not found or value for the key
     * @throws JSONException
     */
    public String getJsonValue(JSONObject jsonObject, String key) throws JSONException {

        if (jsonObject.has(key)) {
            String value = jsonObject.getString(key);
            return (null == value) ? "" : value;
        }
        return "";
    }

    /**
     * @param upc unique product code
     * @return item that represents upc
     */
    public ItemInfo httpRequest(String upc) {

        /* end point url */
        String endPoint = ITEM_INFO_ENDPOINT + upc;

        /* send and return response */
        String stringResponse = null;
        try {
            HttpGet httpGet = new HttpGet(endPoint);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream response = httpResponse.getEntity().getContent();
            stringResponse = StringUtil.readFromStream(response);
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getItemDomain(stringResponse);
    }
}
