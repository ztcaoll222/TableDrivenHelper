package com.ztcaoll222.table;

import com.ztcaoll222.TableDrivenHelper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
class MoreByOneTableTest {
    private final Map<Integer, List<Map<String, Integer>>> test = Map.of(1, List.of(Map.of("1", 1), Map.of("2", 1)),
            2, List.of(Map.of("1", 1), Map.of("2", 1)));

    @Test
    void compute() {
        var compute = TableDrivenHelper.upMoreByOne(1)
                .upMoreByOne()
                .add(Collections.singletonList("1"))
                .add(Collections.singletonList("2"))
                .compute(Arrays.asList(1, 2), Collectors.toList(), Hashtable::new, Hashtable::new);
        assertEquals(test, compute);
    }

    @Test
    void testCompute() {
        var compute = TableDrivenHelper.upMoreByOne(1)
                .upMoreByOne()
                .add(Collections.singletonList("1"))
                .add(Collections.singletonList("2"))
                .compute(Arrays.asList(1, 2), Collectors.toList());
        assertEquals(test, compute);
    }

    @Test
    void computeWithConcurrent() {
        var compute = TableDrivenHelper.upMoreByOne(1)
                .upMoreByOne()
                .add(Collections.singletonList("1"))
                .add(Collections.singletonList("2"))
                .computeWithConcurrent(Arrays.asList(1, 2), Collectors.toList());
        assertEquals(test, compute);
    }
}
