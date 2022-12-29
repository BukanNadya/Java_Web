package org.example.lesson2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TableServlet extends HttpServlet {
    // http://localhost:8080/table
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> strings = Files.readAllLines(Paths.get("static-content/table.html"));

        try (PrintWriter w = resp.getWriter()) {
            strings.forEach(x -> w.println(x));
        }
    }

}
