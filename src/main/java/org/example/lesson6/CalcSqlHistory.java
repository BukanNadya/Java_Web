package org.example.lesson6;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalcSqlHistory implements CalcHistory{

    private final Connection conn;

    public CalcSqlHistory(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void store(int x, int y, int z) throws SQLException {
        String sql = "insert into calc_history (a, b, c) values (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, x);
        stmt.setInt(2, y);
        stmt.setInt(3, z);
        stmt.execute();
    }

    @Override
    public List<String> getAll() throws SQLException {
        String sql = "select a, b, c, dt from calc_history";
        PreparedStatement stmt = conn.prepareStatement(sql);
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
