package com.ztcaoll222;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
class TableDrivenHelperTest {

    @Test
    void upOneByOne() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3)).compute(Arrays.asList("a", "b", "c"));
        assertEquals("{\"a\":1,\"b\":2,\"c\":3}", new Gson().toJson(compute));
    }

    @Test
    void upMoreByOne() {
        var compute = TableDrivenHelper.upMoreByOne(Arrays.asList(1, 2, 3)).compute(Arrays.asList("a", "b", "c"));
        assertEquals("{\"a\":[1,2,3],\"b\":[1,2,3],\"c\":[1,2,3]}", new Gson().toJson(compute));
    }

    @Test
    public void t0() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3))
                .upOneByOne((Supplier<ArrayList<List<String>>>) ArrayList::new)
                .add(Arrays.asList("a", "b", "c"))
                .add(Arrays.asList("d", "e", "f"))
                .add(Arrays.asList("g", "h", "i"))
                .compute(Arrays.asList(1, 2, 3));
        assertEquals("{\"1\":{\"a\":1,\"b\":2,\"c\":3},\"2\":{\"d\":1,\"e\":2,\"f\":3},\"3\":{\"h\":2,\"i\":3,\"g\":1}}", new Gson().toJson(compute));
    }

    @Test
    public void t1() {
        var compute = TableDrivenHelper.upOneByOne(Arrays.asList(1, 2, 3))
                .upMoreByOne((Supplier<ArrayList<List<String>>>) ArrayList::new)
                .add(Arrays.asList("a", "b", "c"))
                .add(Arrays.asList("d", "e", "f"))
                .compute(Arrays.asList(1, 2));
        assertEquals("{\"1\":[{\"a\":1,\"b\":2,\"c\":3},{\"d\":1,\"e\":2,\"f\":3}],\"2\":[{\"a\":1,\"b\":2,\"c\":3},{\"d\":1,\"e\":2,\"f\":3}]}", new Gson().toJson(compute));
    }

    @Test
    void oneByOne() {
        var compute = TableDrivenHelper.oneByOne(Arrays.asList("a", "b", "c"), Arrays.asList(1, 2, 3));
        assertEquals("{\"a\":1,\"b\":2,\"c\":3}", new Gson().toJson(compute));
    }

    @Test
    void oneByOneWithConcurrent() {
        var compute = TableDrivenHelper.oneByOneWithConcurrent(Arrays.asList("a", "b", "c"), Arrays.asList(1, 2, 3));
        assertEquals("{\"a\":1,\"b\":2,\"c\":3}", new Gson().toJson(compute));
    }

    @Test
    void moreByOne() {
        var compute = TableDrivenHelper.moreByOne(Arrays.asList("a", "b", "c"), Arrays.asList(1, 2, 3));
        assertEquals("{\"a\":[1,2,3],\"b\":[1,2,3],\"c\":[1,2,3]}", new Gson().toJson(compute));
    }

    @Test
    void moreByOneWithConcurrent() {
        var compute = TableDrivenHelper.moreByOneWithConcurrent(Arrays.asList("a", "b", "c"), Arrays.asList(1, 2, 3));
        assertEquals("{\"a\":[1,2,3],\"b\":[1,2,3],\"c\":[1,2,3]}", new Gson().toJson(compute));
    }
}
