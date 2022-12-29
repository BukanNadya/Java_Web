package org.example.lesson7cookie;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;

public class ServerApp {

    // http://localhost:8080/calc?x=1&y=3
    // http://localhost:8080/calc?x=1&y=4
    // http://localhost:8080/calc?x=1&y=5
    // http://localhost:8080/calc?x=1&y=6
    // http://localhost:8080/history
    // http://localhost:8080/login
    // http://localhost:8080/logout
    public static void main(String[] args) throws Exception {

//        Connection conn = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/fs1",
//                "postgres",
//                "pg123456"
//        );

        // ANTI-PATTERN !!!
        Connection conn = GlobalSqlConnection.get();
        // ANTI-PATTERN !!!
        Connection conn2 = GlobalSqlConnection.get();

        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler();

        CalcHistory log = new CalcSqlHistory(conn);
                //new CalcInMemoryHistory();

        handler.addServlet(HelloServlet.class, "/hello");
        handler.addServlet(HandlerPostServlet.class, "/post");

        CalcServlet calcServlet = new CalcServlet(log);
        HistoryServlet historyServlet = new HistoryServlet(log);

        handler.addServlet(new ServletHolder(calcServlet), "/calc");
        handler.addServlet(new ServletHolder(historyServlet), "/history");
        handler.addServlet(SetCookieServlet.class, "/login");
        handler.addServlet(RemoveCookieServlet.class, "/logout");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
