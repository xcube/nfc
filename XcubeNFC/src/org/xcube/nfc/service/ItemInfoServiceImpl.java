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

    private static final String ITEM_INFO_ENDPOINT = "http://xcubeserver.herokuapp.com/xcubeserver/upc/";

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
        if (null == itemJsonString) {
            return itemInfo;
        }

        try {
            /* main item properties */
            JSONObject jsonItem = new JSONObject(itemJsonString);
            itemInfo.setName(jsonItem.getString(ITEM_NAME_KEY));
            itemInfo.setUpc(jsonItem.getString(ITEM_UPC_KEY));
            itemInfo.setImg(jsonItem.getString(ITEM_IMG_KEY));
            itemInfo.setType(jsonItem.getString(ITEM_TYPE_KEY));
            itemInfo.setOrigin(jsonItem.getString(ITEM_ORIGIN_KEY));
            itemInfo.setQuantity(jsonItem.getString(ITEM_QUANTITY_KEY));

            /* nutrition info per 100 grams */
            NutritionInfo per100g = new NutritionInfo();
            JSONObject jsonPer100g = jsonItem.getJSONObject(PER100G_KEY);
            per100g.setCalories(jsonPer100g.getString(NUTRITION_CALORIES_KEY));
            per100g.setProtein(jsonPer100g.getString(NUTRITION_PROTEIN_KEY));
            per100g.setFat(jsonPer100g.getString(NUTRITION_FAT_KEY));
            per100g.setCarbohydrate(jsonPer100g.getString(NUTRITION_CARBOHYDRATE_KEY));
            per100g.setFibre(jsonPer100g.getString(NUTRITION_FIBRE_KEY));
            per100g.setSalt(jsonPer100g.getString(NUTRITION_SALT_KEY));
            itemInfo.setPer100g(per100g);

            /* nutrition info for the whole item */
            NutritionInfo each = new NutritionInfo();
            JSONObject jsonEach = jsonItem.getJSONObject(EACH_KEY);
            each.setCalories(jsonEach.getString(NUTRITION_CALORIES_KEY));
            each.setProtein(jsonEach.getString(NUTRITION_PROTEIN_KEY));
            each.setFat(jsonEach.getString(NUTRITION_FAT_KEY));
            each.setCarbohydrate(jsonEach.getString(NUTRITION_CARBOHYDRATE_KEY));
            each.setFibre(jsonEach.getString(NUTRITION_FIBRE_KEY));
            each.setSalt(jsonEach.getString(NUTRITION_SALT_KEY));
            itemInfo.setEach(each);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemInfo;
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
