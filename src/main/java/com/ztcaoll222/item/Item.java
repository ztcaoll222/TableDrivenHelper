package com.ztcaoll222.item;

import com.ztcaoll222.table.MoreByOneTable;
import com.ztcaoll222.table.OneByOneTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Ztcaoll222
 * @date 2020/9/30 13:47
 */
public abstract class Item<Value> {
    public abstract <Key, Return extends Map<Key, Value>>
    Return compute(Collection<Key> keys, IntFunction<Return> initContainFunc);

    public abstract <Key> Map<Key, Value> compute(Collection<Key> keys);

    public abstract <Key> Map<Key, Value> computeWithConcurrent(Collection<Key> keys);

    public <Key, KeyType extends Collection<Key>, KeysType extends Collection<KeyType>>
    OneByOneTable<Key, KeyType, KeysType, Value> upOneByOne(Supplier<KeysType> initKeysContainFunc) {
        return new OneByOneTable<>(this, initKeysContainFunc);
    }

    public <Key, KeyType extends Collection<Key>>
    OneByOneTable<Key, KeyType, List<KeyType>, Value> upOneByOne() {
        return upOneByOne(ArrayList::new);
    }

    public <Key, KeyType extends Collection<Key>, KeysType extends Collection<KeyType>>
    MoreByOneTable<Key, KeyType, KeysType, Value> upMoreByOne(Supplier<KeysType> initKeysContainFunc) {
        return new MoreByOneTable<>(this, initKeysContainFunc);
    }

    public <Key, KeyType extends Collection<Key>>
    MoreByOneTable<Key, KeyType, List<KeyType>, Value> upMoreByOne() {
        return upMoreByOne(ArrayList::new);
    }
}
