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

import android.util.JsonReader;

public class ItemInfoServiceImpl implements ItemInfoService {

    private static final String ITEM_INFO_ENDPOINT = "http://192.168.43.93:8080/xcubeserver/upc/";

    private static final String ITEM_TYPE_KEY = "type";
    private static final String ITEM_NAME_KEY = "name";
    private static final String ITEM_ORIGIN_KEY = "origin";
    private static final String ITEM_QUANTITY_KEY = "quantity";
    private static final String ITEM_UPC_KEY = "upc";

    public Item getItem(String upc) {
        httpRequest("50378289");
        return null;
    }

    public Item getItemDomain(String itemJsonString) {

        try {
            JSONObject jsonItem = new JSONObject(itemJsonString);
            Item item = new Item();
            item.setName(jsonItem.getString(""));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Item();
    }

    public void httpRequest(String upc) {

        /* end point url */
        String endPoint = ITEM_INFO_ENDPOINT + upc;

        /* send and set response */
        try {
            HttpGet httpGet = new HttpGet(endPoint);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream response = httpResponse.getEntity().getContent();
            String stringRespose = StringUtil.readFromStream(response);
            System.out.println(stringRespose);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
