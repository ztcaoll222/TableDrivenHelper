package com.ztcaoll222.item;

import com.ztcaoll222.TableDrivenHelper;

import java.util.Collection;
import java.util.Map;
import java.util.function.IntFunction;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 13:48
 */
public class MoreByOneItem<Value> extends Item<Value> {
    private final Value value;

    public MoreByOneItem(Value value) {
        this.value = value;
    }

    @Override
    public <Key, Return extends Map<Key, Value>>
    Return compute(Collection<Key> keys, IntFunction<Return> initContainFunc) {
        return TableDrivenHelper.moreByOne(keys, value, initContainFunc);
    }

    @Override
    public <K> Map<K, Value> compute(Collection<K> keys) {
        return TableDrivenHelper.moreByOne(keys, value);
    }

    @Override
    public <Key> Map<Key, Value> computeWithConcurrent(Collection<Key> keys) {
        return TableDrivenHelper.moreByOneWithConcurrent(keys, value);
    }
}
