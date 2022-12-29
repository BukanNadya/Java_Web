package org.example.lesson7cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcInMemoryHistory implements CalcHistory {

    static class Entry {
        final int x, y, z;
        String userId;

        Entry(String userId, int x, int y, int z) {
            this.userId = userId;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private final List<Entry> storage = new ArrayList<>();

    @Override
    public void store(String userId, int x, int y, int z) throws Exception {
        storage.add(new Entry(userId, x, y, z));
    }

    @Override
    public List<String> getAll(String userId) throws Exception {
        return storage.stream()
                .filter(x -> x.userId.equals(userId))
                .map(e -> String.format("%d + %d = %d", e.x, e.y, e.z))
                .collect(Collectors.toList());
    }
}
