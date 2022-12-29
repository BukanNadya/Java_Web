package org.example.lesson8filter.svc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CalcSqlHistory implements CalcHistory {

    private final Connection conn;

    public CalcSqlHistory(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void store(String userid, int x, int y, int z) throws Exception {
        String sql = "insert into calc_history2 (a, b, c, usr) values (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, x);
        stmt.setInt(2, y);
        stmt.setInt(3, z);
        stmt.setString(4, userid);
        stmt.execute();
    }

    @Override
    public List<String> getAll(String userid) throws Exception {
        String sql = "select a, b, c, dt from calc_history2 where usr = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userid);

        ResultSet rs = stmt.executeQuery();
        ArrayList<String> outcome = new ArrayList<>();
        while (rs.next()) {
            int a = rs.getInt("a");
            int b = rs.getInt("b");
            int c = rs.getInt("c");
            Timestamp dt = rs.getTimestamp("dt");
            outcome.add(String.format("%d + %d = %d @ %s", a, b, c, dt));
        }
        return outcome;
    }

}
