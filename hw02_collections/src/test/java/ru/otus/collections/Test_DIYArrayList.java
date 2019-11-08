package ru.otus.collections;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@DisplayName("Test Class DIY")
class Test_DIYArrayList {

    private DIYArrayList diyArrayList;

    @BeforeAll
    static void beforeClass() {
        System.out.println("before class");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("after class");
    }

    @BeforeEach
    void init() {
        diyArrayList = new DIYArrayList();
        System.out.println("before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("after");
    }

    @Test
    @DisplayName("test method addAll")
    void add() {
        List<String> arrlist = new DIYArrayList<>();
        Collections.addAll(arrlist, "BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA", "ZZ");
        List<String> arrlist_test = Arrays.asList("BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA", "ZZ");
        assertEquals(arrlist_test, arrlist, "add result");
    }
    @Test
    @DisplayName("test method copy")
    void copy() {
        List<String> arrlist = new DIYArrayList<>();
        Collections.addAll(arrlist, "BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA", "ZZ");
        List<String> arrlist2 = new DIYArrayList<>();
        for(int i = 0; i<201; i++) {
            arrlist2.add("element" + i);
        }
        Collections.copy(arrlist2, arrlist);
        String expected = "ZZ";
        assertEquals(expected, arrlist2.get(10), "copy");
    }
    @Test
    @DisplayName("test method sort")
    void sort() {
        List<String> arrlist = new DIYArrayList<>();
        Collections.addAll(arrlist, "BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA", "ZZ");
        Collections.sort(arrlist, Collections.reverseOrder());
        String expected = "AA";
        assertEquals(expected, arrlist.get(arrlist.size()-1), "sort");
    }
}
