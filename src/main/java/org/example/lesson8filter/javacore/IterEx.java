package org.example.lesson8filter.javacore;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IterEx {

    public static void main(String[] args) {
        List<String> data = Arrays.asList("Jim", "Bim");

        Iterable<String> its = new Iterable<>() {

            @Override
            public Iterator<String> iterator() {
                return data.iterator();
            }

        };

        Iterator<String> it = its.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        its.forEach(x -> System.out.println(x));

    }
}

