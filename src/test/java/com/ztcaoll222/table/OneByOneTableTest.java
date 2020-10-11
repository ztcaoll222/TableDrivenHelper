package com.ztcaoll222.table;

import com.ztcaoll222.TableDrivenHelper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
class OneByOneTableTest {
    private final Map<Integer, Map<String, Integer>> test = Map.of(1, Map.of("1", 1, "2", 2),
            2, Map.of("3", 1, "4", 2));

    @Test
    void compute() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2))
                .upOneByOne()
                .add(Arrays.asList("1", "2"))
                .add(Arrays.asList("3", "4"))
                .compute(Arrays.asList(1, 2), Collectors.toList(), Hashtable::new, Hashtable::new);
        assertEquals(test, compute);
    }

    @Test
    void testCompute() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2))
                .upOneByOne()
                .add(Arrays.asList("1", "2"))
                .add(Arrays.asList("3", "4"))
                .compute(Arrays.asList(1, 2), Collectors.toList());
        assertEquals(test, compute);
    }

    @Test
    void computeWithConcurrent() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2))
                .upOneByOne()
                .add(Arrays.asList("1", "2"))
                .add(Arrays.asList("3", "4"))
                .computeWithConcurrent(Arrays.asList(1, 2), Collectors.toList());
        assertEquals(test, compute);
    }
}
