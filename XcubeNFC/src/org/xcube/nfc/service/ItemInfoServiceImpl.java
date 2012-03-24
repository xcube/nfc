package org.xcube.nfc.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.xcube.nfc.domain.Item;
import org.xcube.nfc.util.StringUtil;

public class ItemInfoServiceImpl implements ItemInfoService {

    private static final String ITEM_INFO_ENDPOINT = "http://192.168.43.93:8080/xcubeserver/upc/";

    private static final String ITEM_TYPE_KEY = "type";
    private static final String ITEM_NAME_KEY = "name";
    private static final String ITEM_ORIGIN_KEY = "origin";
    private static final String ITEM_QUANTITY_KEY = "quantity";
    private static final String ITEM_UPC_KEY = "upc";

    public Item getItem(String upc) {
        return httpRequest("50378289");
    }

    /**
     * @param upc unique product code
     * @param itemJsonString json response from service
     * @return item domain object
     */
    public Item getItemDomain(String itemJsonString) {

        Item item = new Item();
        if (null == itemJsonString) {
            return item;
        }

        try {
            JSONObject jsonItem = new JSONObject(itemJsonString);
            item.setName(jsonItem.getString(ITEM_NAME_KEY));
            item.setCalories(300);
            item.setUpc(ITEM_UPC_KEY);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return item;
    }

    /**
     * @param upc unique product code
     * @return item that represents upc
     */
    public Item httpRequest(String upc) {

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
