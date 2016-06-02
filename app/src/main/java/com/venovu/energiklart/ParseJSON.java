package com.venovu.energiklart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Christoffer Nordfeldt on 2016-03-25.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
public class ParseJSON {

    public static String [] names;
    public static String [] fNr;
    public static String [] adress;
    public static String [] byggår;


    public static final String KEY_NAME = "name";
    public static final String KEY_FNR = "propertyNr";
    public static final String KEY_ADRESS = "adress";
    public static final String KEY_BYGGÅR = "buildYear";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        try {
            JSONArray users= new JSONArray(json);


            names = new String[users.length()];
            fNr = new String[users.length()];
            adress = new String[users.length()];
            byggår = new String[users.length()];
            //JSONObject jsonObject = users.getJSONObject(0);

            for (int i = 0; i < users.length(); i++) {
                JSONObject jr = (JSONObject) users.get(i);
                names[i] = jr.getString(KEY_NAME);
                fNr[i] = jr.getString(KEY_FNR);
                adress[i] = jr.getString(KEY_ADRESS);
                byggår[i] = jr.getString(KEY_BYGGÅR);
               // System.out.println(pass[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
