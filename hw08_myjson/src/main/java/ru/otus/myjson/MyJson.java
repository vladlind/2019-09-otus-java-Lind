package ru.otus.myjson;

import javax.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyJson {

    JsonObjectBuilder mainbuilder = Json.createObjectBuilder();
    JsonArrayBuilder arraybuilder = Json.createArrayBuilder();
    String key = null;

    public JsonObjectBuilder myJsonBuilder(Object src) {

        if (src instanceof HashMap) {
            HashMap<Object, Object> hmap = (HashMap<Object, Object>) src;
            for (Map.Entry<Object, Object> entry : hmap.entrySet()) {
                this.key = entry.getKey().toString();
                myJsonBuilder(entry.getValue());
            }
        } else if (src.getClass().isArray()) {
            Object[] arr = (Object[]) src;
            for (int i = 0; i < arr.length; i++) {
                myJsonBuilder(arr[i]);
            }
            nullKeyChecker(this.key);
        } else if (src instanceof ArrayList) {
            ArrayList<Object> arrlist = (ArrayList<Object>) src;
            for (int i = 0; i < arrlist.size(); i++) {
                myJsonBuilder(arrlist.get(i));
            }
            nullKeyChecker(this.key);
        } else if (src instanceof String) {
            this.arraybuilder.add((String) src);
        } else if (src instanceof Integer) {
            this.arraybuilder.add((Integer) src);
        } else {
            System.out.println("no match");
        }
        return this.mainbuilder;
    }
    public String toMyJson(JsonObjectBuilder objectbuilder) {
        return objectbuilder.build().toString();
    }
    private void nullKeyChecker(String key) {
        if (this.key!=null) {
            this.mainbuilder.add(this.key, this.arraybuilder);
        } else {
            this.mainbuilder.add("Null", this.arraybuilder);
        }
    }
}
