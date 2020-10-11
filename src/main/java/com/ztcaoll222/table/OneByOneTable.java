package com.ztcaoll222.table;

import com.ztcaoll222.TableDrivenHelper;
import com.ztcaoll222.item.Item;

import java.util.Collection;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 13:47
 */
public class OneByOneTable<Key, KeyType extends Collection<Key>, KeysType extends Collection<KeyType>, Value>
        extends Table<Key, KeyType, KeysType, Value, Map<Key, Value>> {
    public OneByOneTable(Item<Value> values, Supplier<KeysType> initKeysContainFunc) {
        super(values, initKeysContainFunc);
    }

    @Override
    public <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, Map<Key, Value>> compute(Collection<ComputeKey> computeKeys,
                                             Collector<? super Map<Key, Value>, A, Collect> collector,
                                             IntFunction<Map<Key, Value>> initLowContainFunc,
                                             IntFunction<Map<ComputeKey, Map<Key, Value>>> initContainFunc) {
        return TableDrivenHelper.oneByOne(computeKeys, collect(collector, initLowContainFunc), initContainFunc);
    }

    @Override
    public <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, Map<Key, Value>> compute(Collection<ComputeKey> computeKeys,
                                             Collector<? super Map<Key, Value>, A, Collect> collector) {
        return TableDrivenHelper.oneByOne(computeKeys, collect(collector));
    }

    @Override
    public <A, ComputeKey, Collect extends Collection<Map<Key, Value>>>
    Map<ComputeKey, Map<Key, Value>> computeWithConcurrent(Collection<ComputeKey> computeKeys,
                                                           Collector<? super Map<Key, Value>, A, Collect> collector) {
        return TableDrivenHelper.oneByOneWithConcurrent(computeKeys, collectWithConcurrent(collector));
    }
}
