package com.ztcaoll222.item;

import com.ztcaoll222.TableDrivenHelper;

import java.util.Collection;
import java.util.Map;
import java.util.function.IntFunction;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 13:48
 */
public class OneByOneItem<Value, Values extends Collection<Value>> extends Item<Value> {
    private final Values values;

    public OneByOneItem(Values values) {
        this.values = values;
    }

    @Override
    public <Key, Return extends Map<Key, Value>>
    Return compute(Collection<Key> keys, IntFunction<Return> initContainFunc) {
        return TableDrivenHelper.oneByOne(keys, values, initContainFunc);
    }

    @Override
    public <K> Map<K, Value> compute(Collection<K> keys) {
        return TableDrivenHelper.oneByOne(keys, values);
    }

    @Override
    public <Key> Map<Key, Value> computeWithConcurrent(Collection<Key> keys) {
        return TableDrivenHelper.oneByOneWithConcurrent(keys, values);
    }
}
