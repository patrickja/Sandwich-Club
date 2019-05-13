package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String INIT_NAME = "name";
    private static final String INIT_MAIN_NAME = "mainName";
    private static final String INIT_KNOWN_AS = "alsoKnownAs";
    private static final String INIT_ORIGIN = "placeOfOrigin";
    private static final String INIT_DESCRIPTION = "description";
    private static final String INIT_IMAGE = "image";
    private static final String INIT_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject initSandwichObject = new JSONObject(json);
            JSONObject name = initSandwichObject.getJSONObject(INIT_NAME);
            String mainName = name.getString(INIT_MAIN_NAME);

            JSONArray knownAsList = name.getJSONArray(INIT_KNOWN_AS);
            List<String> knownAs = JSONArrayToListConverter(knownAsList);

            String origin = initSandwichObject.optString(INIT_ORIGIN);
            String description = initSandwichObject.getString(INIT_DESCRIPTION);
            String image = initSandwichObject.getString(INIT_IMAGE);

            JSONArray ingredientsArray = initSandwichObject.getJSONArray(INIT_INGREDIENTS);
            List<String> ingredients = JSONArrayToListConverter(ingredientsArray);

            Sandwich sandwich = new Sandwich(mainName, knownAs, origin, description, image, ingredients);
            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> JSONArrayToListConverter(JSONArray array) {
        List<String> sandwichList = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                sandwichList.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwichList;
    }
}
