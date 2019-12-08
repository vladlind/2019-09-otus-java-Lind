package ru.otus.myjson;

import com.google.gson.Gson;

import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.HashMap;

public class TestMyJson {
    public static void main(String[] args) throws IllegalAccessException {
//        HashMap<String, ArrayList> hmap = new HashMap<>();
        ArrayList<String> arlist = new ArrayList<>();
        arlist.add("1");
        arlist.add("2");
        arlist.add("3");
        arlist.add("4");
        arlist.add("55");
        arlist.add("66");
        BagOfPrimitives obj1 = new BagOfPrimitives(22, "test", 10);
        BagOfPrimitives obj2 = new BagOfPrimitives(23, "test", 11);
        Boolean[] arr = {true, false, true};
        ArrayList<BagOfPrimitives> arlist2 = new ArrayList<>();
        arlist2.add(obj1);
        arlist2.add(obj2);
        MyJson myjson = new MyJson();
        System.out.println(myjson.toJson(arlist2));
        System.out.println(myjson.toJson(arlist));
        System.out.println(myjson.toJson(arr));
        System.out.println(myjson.toJson(null));
    }
}
