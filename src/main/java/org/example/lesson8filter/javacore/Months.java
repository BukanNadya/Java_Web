package org.example.lesson8filter.javacore;

import java.util.Iterator;

public class Months implements Iterable<String> {

    private String[] data = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    @Override
    public Iterator<String> iterator() {

        return new Iterator<>() {

            int c = 0;

            @Override
            public boolean hasNext() {
                return c < data.length;
            }

            @Override
            public String next() {
                return data[c++];
            }
        };
    }
}

