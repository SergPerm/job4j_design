package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length;
    }
//    @Override
//    public boolean hasNext() {
//        while (row < data.length) {
//            if (data[row].length != 0) {
//                return row < data.length && column < data[row].length;
//            } else {
//                row++;
//            }
//        }
//        return false;
//    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int tmp = data[row][column++];
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        return tmp;

    }
}
