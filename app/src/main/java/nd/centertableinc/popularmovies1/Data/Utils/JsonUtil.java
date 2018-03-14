package nd.centertableinc.popularmovies1.Data.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class JsonUtil {
    public static JSONObject createJsonObjFromJsonString(String json)
    {
        JSONObject jsonObject = null;

        try
        {
            jsonObject = new JSONObject(json);
        }
        catch (JSONException ex)
        {
            Log.e("JsonUtil.java","createJsonObjFromString(), Bad Json format. Exception: " + ex.toString());
        }

        return jsonObject;
    }
    public static JSONObject getJsonObjFromJsonObj(JSONObject jsonObject, String name)
    {
        JSONObject res = null;

        try
        {
            res = jsonObject.getJSONObject(name);
        }
        catch (JSONException ex)
        {
            Log.e("JsonUtil.java","getJsonObjFromJsonObj(), Bad Json format. Exception: " + ex.toString()+ ", jsonName:"+name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getJsonObjFromJsonObj(), Exception: " + ex.toString());
        }

        return res;
    }

    public static JSONArray getJsonArrayFromJsonObj(JSONObject jsonObject, String name)
    {
        JSONArray res = null;

        try
        {
            res = jsonObject.getJSONArray(name);
        }
        catch (JSONException ex)
        {
            Log.e("JsonUtil.java","getJsonArrayFromJsonObj(), Bad Json format. Exception: " + ex.toString()+ ", jsonName:"+name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getJsonObjFromJsonObj(), Exception: " + ex.toString());
        }

        return res;
    }

    public static List<String> getStringListFromJsonArray(JSONArray jsonArray)
    {
        List<String> res = new ArrayList<>();

        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
            {

                res.add(jsonArray.optString(i));

            }
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getJsonObjFromJsonObj(), Exception: " + ex.toString());
        }

        return res;
    }

    public static String getStringFromJsonObj(JSONObject jsonObject, String name)
    {
        String value = null;

        try
        {
            value =  jsonObject.optString(name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getStringFromJsonObj(), Exception: " + ex.toString());
        }

        return value;
    }

    public static int getIntFromJsonObj(JSONObject jsonObject, String name)
    {
        int value = 0;

        try
        {
            value =  jsonObject.optInt(name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getIntFromJsonObj(), Exception: " + ex.toString());
        }

        return value;
    }

    public static boolean getBooleanFromJsonObj(JSONObject jsonObject, String name)
    {
        boolean value = false;

        try
        {
            value =  jsonObject.optBoolean(name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getBooleanFromJsonObj(), Exception: " + ex.toString());
        }

        return value;
    }

    public static double getDoubleFromJsonObj(JSONObject jsonObject, String name)
    {
        double value = 0;

        try
        {
            value =  jsonObject.optDouble(name);
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getDoubleFromJsonObj(), Exception: " + ex.toString());
        }

        return value;
    }

    public static JSONObject getJsonObjectFromJsonArray(JSONArray jsonArray, int index)
    {
        JSONObject res = null;

        try
        {
            res = jsonArray.getJSONObject(index);
        }
        catch (JSONException ex)
        {
            Log.e("JsonUtil.java","getJsonObjectFromJsonArray(), Bad Json format. Exception: " + ex.toString());
        }
        catch (Exception ex)
        {
            Log.e("JsonUtil.java","getJsonObjectFromJsonArray(), Exception: " + ex.toString());
        }

        return res;
    }

}
