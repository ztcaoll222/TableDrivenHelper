package com.ztcaoll222;

import com.ztcaoll222.item.MoreByOneItem;
import com.ztcaoll222.item.OneByOneItem;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntFunction;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 11:55
 */
public class TableDrivenHelper {
    private TableDrivenHelper() {
    }

    @NotNull
    public static <Value, Values extends Collection<Value>>
    OneByOneItem<Value, Values> upOneByOne(@NotNull Values values) {
        return new OneByOneItem<>(values);
    }

    @NotNull
    public static <Value> MoreByOneItem<Value> upMoreByOne(@NotNull Value value) {
        return new MoreByOneItem<>(value);
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Value, Values extends Collection<Value>, Return extends Map<Key, Value>>
    Return oneByOne(@NotNull Keys keys, @NotNull Values values, @NotNull IntFunction<Return> initContainFunc) {
        assert keys.size() == values.size();

        Return res = initContainFunc.apply(keys.size());
        Iterator<Key> key = keys.iterator();
        Iterator<Value> value = values.iterator();
        while (key.hasNext() && value.hasNext()) {
            res.put(key.next(), value.next());
        }

        return res;
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Value, Values extends Collection<Value>>
    Map<Key, Value> oneByOne(@NotNull Keys keys, @NotNull Values values) {
        return oneByOne(keys, values, HashMap::new);
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Value, Values extends Collection<Value>>
    Map<Key, Value> oneByOneWithConcurrent(@NotNull Keys keys, @NotNull Values values) {
        return oneByOne(keys, values, ConcurrentHashMap::new);
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Values, Return extends Map<Key, Values>>
    Return moreByOne(@NotNull Keys keys, @NotNull Values value, @NotNull IntFunction<Return> initContainFunc) {
        Return res = initContainFunc.apply(keys.size());

        for (Key k : keys) {
            res.put(k, value);
        }

        return res;
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Values>
    Map<Key, Values> moreByOne(@NotNull Keys keys, @NotNull Values value) {
        return moreByOne(keys, value, HashMap::new);
    }

    @NotNull
    public static <Key, Keys extends Collection<Key>, Values>
    Map<Key, Values> moreByOneWithConcurrent(@NotNull Keys keys, @NotNull Values value) {
        return moreByOne(keys, value, ConcurrentHashMap::new);
    }
}
