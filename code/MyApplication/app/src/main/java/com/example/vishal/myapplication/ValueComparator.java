package com.example.vishal.myapplication;

import java.util.Comparator;
import java.util.Map;
<<<<<<< HEAD
/*
 * Copyright (c) 2017 Vishal Srivastava<vsriva10@asu.edu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
=======

/**
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
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