package ru.otus.hw15_MS.configHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw15_MS.domain.AddressDataSet;
import ru.otus.hw15_MS.domain.PhoneDataSet;
import ru.otus.hw15_MS.domain.User;
import ru.otus.hw15_MS.services.UserService;


import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitDB {

    private UserService userService;

    @Autowired
    public DataInitDB(UserService userService) {
        this.userService = userService;
        LoadUsers();
    }

    private void LoadUsers() {
        User user1 = new User(0, "Ivan");
        user1.setPassword("1234");
        user1.setAddressDataSet(new AddressDataSet("Mira,1"));
        List<PhoneDataSet> phoneDataSet = new ArrayList<>();
        phoneDataSet.add(new PhoneDataSet("123"));
        phoneDataSet.add(new PhoneDataSet("321"));
        phoneDataSet.add(new PhoneDataSet("231"));
        user1.setPhoneDataSet(phoneDataSet);
        this.userService.saveUser(user1);
    }
}
