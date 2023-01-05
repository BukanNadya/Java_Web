package org.example.lesson8filter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.lesson8filter.flt.CheckCookieFilter;
import org.example.lesson8filter.svc.CalcHistory;
import org.example.lesson8filter.svc.CalcSqlHistory;
import org.example.lesson8filter.svt.*;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class ServerApp {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

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
        handler.addFilter(CheckCookieFilter.class, "/calc", ft );
        handler.addFilter(CheckCookieFilter.class, "/history", ft );

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
