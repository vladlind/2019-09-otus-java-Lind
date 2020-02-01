package ru.otus.webserver.servlet;


import ru.otus.webserver.api.dao.UserDao;
import ru.otus.webserver.api.service.DBServiceUser;
import ru.otus.webserver.api.service.DbServiceUserImpl;
import ru.otus.webserver.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_All_USERS = "allUsers";

    private final UserDao userDao;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, UserDao userDao) {
        this.templateProcessor = templateProcessor;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);
        paramsMap.put(TEMPLATE_All_USERS, dbServiceUser.getAll());

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
