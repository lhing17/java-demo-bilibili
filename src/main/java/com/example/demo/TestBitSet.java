package com.example.demo;

import java.util.Arrays;
import java.util.BitSet;

public class TestBitSet {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(10);
        bitSet.set(100);
        System.out.println(bitSet);
    }
}
