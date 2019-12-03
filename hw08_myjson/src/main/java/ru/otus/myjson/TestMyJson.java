package ru.otus.myjson;

import com.google.gson.Gson;

import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class TestMyJson {
    public static void main(String[] args) {
        HashMap<String, ArrayList> hmap = new HashMap<>();
        ArrayList<String> arlist = new ArrayList<>();
        arlist.add("1");
        arlist.add("2");
        arlist.add("3");
        arlist.add("4");
        arlist.add("55");
        arlist.add("66");
        hmap.put("name", arlist);
        hmap.put("name2", arlist);
        hmap.put("name3", arlist);
        hmap.put("name4", arlist);
        hmap.put("name5", arlist);
        System.out.println(hmap);
        MyJson myjson = new MyJson();
        JsonObjectBuilder ob2 = myjson.myJsonBuilder(hmap);
        String json = myjson.toMyJson(ob2);
        System.out.println(json);

        Gson gson = new Gson();
        HashMap obj2 = gson.fromJson(json, HashMap.class);
        System.out.println(obj2);
        System.out.println(obj2.equals(hmap));

    }
}
