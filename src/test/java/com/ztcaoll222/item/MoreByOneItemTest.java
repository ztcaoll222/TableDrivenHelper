package com.ztcaoll222.item;

import com.ztcaoll222.TableDrivenHelper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log
class MoreByOneItemTest {
    private final Map<String, Integer> test = Map.of("1", 1, "2", 1, "3", 1);

    @Test
    void compute() {
        var compute = TableDrivenHelper.upMoreByOne(1).compute(Arrays.asList("1", "2", "3"), Hashtable::new);
        assertEquals(compute, test);
    }

    @Test
    void testCompute() {
        var compute = TableDrivenHelper.upMoreByOne(1).compute(Arrays.asList("1", "2", "3"));
        assertEquals(compute, test);
    }

    @Test
    void computeWithConcurrent() {
        var compute = TableDrivenHelper.upMoreByOne(1).computeWithConcurrent(Arrays.asList("1", "2", "3"));
        assertEquals(compute, test);
    }
}
