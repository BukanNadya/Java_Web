package org.example.lesson8filter.svc;

import java.util.List;

public interface CalcHistory {
    void store(String userid, int x, int y, int z) throws Exception;
    List<String> getAll(String userid) throws Exception;
}
