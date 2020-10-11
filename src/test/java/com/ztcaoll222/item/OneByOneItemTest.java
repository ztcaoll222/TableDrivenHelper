package com.ztcaoll222.item;

import com.ztcaoll222.TableDrivenHelper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log
class OneByOneItemTest {
    private final Map<String, Integer> test = Map.of("1", 1, "2", 2, "3", 3);

    @Test
    void compute() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3)).compute(Arrays.asList("1", "2", "3"), Hashtable::new);
        assertEquals(test, compute);
    }

    @Test
    void testCompute() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3)).compute(Arrays.asList("1", "2", "3"));
        assertEquals(test, compute);
    }

    @Test
    void computeWithConcurrent() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3)).computeWithConcurrent(Arrays.asList("1", "2", "3"));
        assertEquals(test, compute);
    }
}
