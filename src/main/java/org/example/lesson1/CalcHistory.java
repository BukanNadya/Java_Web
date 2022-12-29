package org.example.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcHistory implements CalcLog {

    static class Entry {
        final int x, y, z;

        Entry(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private final List<Entry> storage = new ArrayList<>();

    @Override
    public void store(int x, int y, int z) {
        storage.add(new Entry(x, y, z));
    }

    @Override
    public List<String> getAll() {
        return storage.
                stream().
                map(e -> String.format("%d + %d = %d", e.x, e.y, e.z))
                .collect(Collectors.toList());
    }
}
