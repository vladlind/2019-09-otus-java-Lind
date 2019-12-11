package ru.otus.myjson;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

class MyJson {

    String toJson(Object src) throws IllegalAccessException {
        if (src == null) {
            return JsonValue.NULL.toString();
        }
        return myJsonValue(src).toString();
    }

    private JsonValue myJsonValue(Object src) throws IllegalAccessException {
        Class clazz = src.getClass();
        if (isWrapperType(clazz)) {
             return primitiveChecker(src);
        } else if (src instanceof String) {
            return Json.createValue(src.toString());
        } else if (clazz.isArray()) {
            JsonArrayBuilder arraybuilder = Json.createArrayBuilder();
            for (int i = 0; i < Array.getLength(src); i++) {
                arraybuilder.add(myJsonValue(Array.get(src, i)));
            }
            return arraybuilder.build();
        } else if (src instanceof Collection<?>) {
            JsonArrayBuilder arraybuilder = Json.createArrayBuilder();
            for (int i = 0; i < Array.getLength(((Collection) src).toArray()); i++) {
                arraybuilder.add(myJsonValue(Array.get(((Collection) src).toArray(), i)));
            }
            return arraybuilder.build();
        } else {
            JsonObjectBuilder objectbuilder = Json.createObjectBuilder();
            for (Field field : src.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(src) != null) {
                    objectbuilder.add(field.getName(), myJsonValue(field.get(src)));
                }
            }
            return objectbuilder.build();
        }
    }

    private JsonValue primitiveChecker(Object src) {
        if (src instanceof Integer) {
            return Json.createValue((Integer) src);
        } else if (src instanceof Double) {
            return Json.createValue((Double) src);
        } else if (src instanceof Float) {
            return Json.createValue((Float) src);
        } else if (src instanceof Long) {
            return Json.createValue((Long) src);
        } else if (src instanceof Character) {
            return Json.createValue((Character) src);
        } else if (src instanceof Byte) {
            return Json.createValue((Byte) src);
        } else if (src instanceof Short) {
            return Json.createValue((Short) src);
        } else if (src instanceof Boolean) {
            return src.equals(true) ? JsonValue.TRUE : JsonValue.FALSE;
        }
        return JsonValue.NULL;
    }
    private static boolean isWrapperType(Class<?> clazz) {
        return clazz.equals(Boolean.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Float.class);
    }
}