package ru.otus.webserver.servlet;

import ru.otus.webserver.api.dao.UserDao;
import ru.otus.webserver.api.model.AddressDataSet;
import ru.otus.webserver.api.model.PhoneDataSet;
import ru.otus.webserver.api.model.User;
import ru.otus.webserver.api.service.DBServiceUser;
import ru.otus.webserver.api.service.DbServiceUserImpl;
import ru.otus.webserver.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class CreateUserServlet extends HttpServlet {

    private static final String PARAM_ID = "ID";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_PHONE = "phone";
    private static final String CREATE_USER_PAGE_TEMPLATE = "createuser.html";

    private final UserDao userDao;
    private final TemplateProcessor templateProcessor;

    public CreateUserServlet(TemplateProcessor templateProcessor, UserDao userDao) {
        this.userDao = userDao;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(CREATE_USER_PAGE_TEMPLATE, Collections.emptyMap()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter(PARAM_ID);
        String name = request.getParameter(PARAM_NAME);
        String password = request.getParameter(PARAM_PASSWORD);
        String address = request.getParameter(PARAM_ADDRESS);
        String phone = request.getParameter(PARAM_PHONE);

        if (id.isEmpty()) {
            id = "0";
        }
        User userNew = new User(Long.parseLong(id), name);
        if (password != null) {
            userNew.setPassword(password);
        }
        if (address != null) {
            userNew.setAddressDataSet(new AddressDataSet(address));
        }
        if (phone != null) {
            List<String> phones = Arrays.asList(phone.split("\\s*,\\s*"));
            Set<PhoneDataSet> phoneSet = new HashSet<>();
            phones.forEach(ph -> phoneSet.add(new PhoneDataSet(ph)));
            userNew.setPhoneDataSet(phoneSet);
        }
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
        long id1 = dbServiceUser.saveUser(userNew);

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<h2>User: " + name + " created<h2>");

    }
}

