package com.ztcaoll222.table;

import com.ztcaoll222.item.Item;
import com.ztcaoll222.item.MoreByOneItem;
import com.ztcaoll222.item.OneByOneItem;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 13:47
 */
public abstract class Table<Key, KeyType extends Collection<Key>, KeysType extends Collection<KeyType>, Value, ComputeValue> {
    protected final Item<Value> values;
    protected final KeysType keys;

    public Table(Item<Value> values, Supplier<KeysType> initKeysContainFunc) {
        this.values = values;
        this.keys = initKeysContainFunc.get();
    }

    public Table<Key, KeyType, KeysType, Value, ComputeValue> add(KeyType key) {
        keys.add(key);
        return this;
    }

    protected <A, Collect extends Collection<Map<Key, Value>>>
    Collect collect(Collector<? super Map<Key, Value>, A, Collect> collector,
                    IntFunction<Map<Key, Value>> initContainFunc) {
        return this.keys.stream().map(keys -> this.values.compute(keys, initContainFunc)).collect(collector);
    }

    protected <A, Collect extends Collection<Map<Key, Value>>>
    Collect collect(Collector<? super Map<Key, Value>, A, Collect> collector) {
        return this.keys.stream().map(this.values::compute).collect(collector);
    }

    protected <A, Collect extends Collection<Map<Key, Value>>>
    Collect collectWithConcurrent(Collector<? super Map<Key, Value>, A, Collect> collector) {
        return this.keys.stream().map(this.values::computeWithConcurrent).collect(collector);
    }

    public abstract <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, ComputeValue> compute(Collection<ComputeKey> keys,
                                          Collector<? super Map<Key, Value>, A, Collect> collector,
                                          IntFunction<Map<Key, Value>> initLowContainFunc,
                                          IntFunction<Map<ComputeKey, ComputeValue>> initContainFunc);

    public abstract <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, ComputeValue> compute(Collection<ComputeKey> keys,
                                          Collector<? super Map<Key, Value>, A, Collect> collector);

    public abstract <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, ComputeValue> computeWithConcurrent(Collection<ComputeKey> keys,
                                                        Collector<? super Map<Key, Value>, A, Collect> collector);

    public <ComputeKey> Map<ComputeKey, ComputeValue> compute(Collection<ComputeKey> keys,
                                                              IntFunction<Map<Key, Value>> initLowContainFunc,
                                                              IntFunction<Map<ComputeKey, ComputeValue>> initContainFunc) {
        return compute(keys, Collectors.toList(), initLowContainFunc, initContainFunc);
    }

    public <ComputeKey> Map<ComputeKey, ComputeValue> compute(Collection<ComputeKey> keys) {
        return compute(keys, Collectors.toList());
    }

    public <ComputeKey> Map<ComputeKey, ComputeValue> computeWithConcurrent(Collection<ComputeKey> keys) {
        return computeWithConcurrent(keys, Collectors.toList());
    }

    public <A, Collect extends Collection<Map<Key, Value>>>
    OneByOneItem<Map<Key, Value>, Collect> flattening2OneByOne(Collector<? super Map<Key, Value>, A, Collect> collector) {
        Collect flattening = collect(collector);
        return new OneByOneItem<>(flattening);
    }

    public OneByOneItem<Map<Key, Value>, List<Map<Key, Value>>> flattening2OneByOne() {
        return flattening2OneByOne(Collectors.toList());
    }

    public <A, Collect extends Collection<Map<Key, Value>>>
    MoreByOneItem<Collect> flattening2MoreByOne(Collector<? super Map<Key, Value>, A, Collect> collector) {
        Collect flattening = collect(collector);
        return new MoreByOneItem<>(flattening);
    }

    public MoreByOneItem<List<Map<Key, Value>>> flattening2MoreByOne() {
        return flattening2MoreByOne(Collectors.toList());
    }
}
