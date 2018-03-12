package com.lateroad.buber.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lateroad.buber.entity.Entity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class JsonUtils<T extends Entity> {

    public List<T> getEntityFromJson(String jsonStr, Class<T> clazz) throws IOException, JSONException {
        List<T> parsedData;
        ObjectMapper mapper = new ObjectMapper();

        JSONArray jsonArray = new JSONArray(jsonStr);
        parsedData = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            String jsonForMap = jsonArray.getJSONObject(i).toString();
            parsedData.add(mapper.readValue(jsonForMap, clazz));
        }
        return parsedData;
    }
}