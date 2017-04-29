package com.example.vishal.myapplication;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by vishal on 4/22/17.
 */

class ValueComparator<K, V extends Comparable<V>> implements Comparator<K> {

    Map<K, V> map;

    public ValueComparator(Map<K, V> base) {
        this.map = base;
    }

    @Override
    public int compare(K o1, K o2) {
        //Changing order to show in decreasing order
        return -(map.get(o1).compareTo(map.get(o2)));
    }
}