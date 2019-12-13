package ru.otus.jdbc.jdbc;

import ru.otus.jdbc.api.dao.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SqlQueryGenerator {

    private String annotatedfield;
    private String annotatedfieldvalue;
    private ArrayList<String> otherfields = new ArrayList<>();

    public ArrayList<String> getOtherfieldsvalues() {
        return otherfieldsvalues;
    }

    private ArrayList<String>  otherfieldsvalues = new ArrayList<>();

    private String sqlInsert;
    private String sqlSelect;
    private String sqlUpdate;

    public String getSqlInsert() {
        return sqlInsert;
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public String getSqlUpdate() { return sqlUpdate; }


    public void createInsert(Object objectData) throws IllegalAccessException {
        otherfields.clear();
        otherfieldsvalues.clear();
        getFieldsObject(objectData);
        sqlInsert = "insert into " + objectData.getClass().getSimpleName().toLowerCase() +
                "(" + String.join(", ", otherfields) + ")" +
                " values " +
                "(?," + " ?".repeat(otherfields.size()-1) + ")";
        System.out.println(sqlInsert);

    }
    public void createSelect(Class clazz) {
        otherfields.clear();
        getFieldsClass(clazz);
        sqlSelect = "select " + annotatedfield + ", " +
                String.join(", ",otherfields) +
                " from " + clazz.getSimpleName().toLowerCase() + " where " +
                annotatedfield + " = ?";
        System.out.println(sqlSelect);
    }
    public void createUpdate(Object objectData) throws IllegalAccessException {
        otherfields.clear();
        otherfieldsvalues.clear();
        getFieldsObject(objectData);
        StringBuilder str = new StringBuilder();
        for (String otherfield : otherfields) {
            str.append(otherfield).append(" = ?, ");
        }
        sqlUpdate = "update " + objectData.getClass().getSimpleName().toLowerCase() +
                " set " + str.substring(0,str.length()-2) +
                " where " + annotatedfield + " = ?";
        System.out.println(sqlUpdate);
    }

    private void getFieldsObject(Object objectData) throws IllegalAccessException {
        for (Field field : objectData.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
                annotatedfield = field.getName();
                annotatedfieldvalue = field.get(objectData).toString();
            } else {
                otherfields.add(field.getName());
                otherfieldsvalues.add(field.get(objectData).toString());
            }
        }
    }

    private void getFieldsClass(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
                annotatedfield = field.getName();
            } else {
                otherfields.add(field.getName());
            }
        }
    }
}
