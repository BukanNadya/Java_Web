package org.example.lesson2;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DynamicContentServlet extends HttpServlet {
    // http://localhost:8080/dynamic
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration conf = new Configuration(Configuration.VERSION_2_3_31);
        conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        conf.setDirectoryForTemplateLoading(new File("static-content"));
        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Jim");

        ArrayList<Item> priceList = new ArrayList<>();
        priceList.add(new Item(5001, "Iphone 5", 500.5));
        priceList.add(new Item(5002, "Iphone 10", 1000d));
        priceList.add(new Item(5003, "Iphone 14", 1500d));
        data.put("price", priceList);

        try (PrintWriter w = resp.getWriter()) {
                conf.getTemplate("table-dynamic.ftl").process(data, w);
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }


