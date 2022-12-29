package org.example.lesson7cookie;

import java.util.List;

public interface CalcHistory {
    void store(String userid, int x, int y, int z) throws Exception;
    List<String> getAll(String userid) throws Exception;
}
