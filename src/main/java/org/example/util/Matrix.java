package org.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix<E> {
    public static void main(String[] args) {
        System.out.println("work");
        Matrix<Double> m = new Matrix<>(3, 3);
        m.setRow(1, new Double[] { 0.39674539471801074, 0.1634581294486046, 0.5936350946295619 });
        m.setRow(2, new Double[] { 0.8970772328061904, 0.7113953818994249, 0.8629320752940484 });
        m.setRow(3, new Double[] { 0.4994253457250343, 0.04248295274688174, 0.1696797075348634 });
        System.out.println(m.toArray());

    }

    List<List<E>> data;
    public Matrix(int rows, int cols) {
        // TODO: Write constructor
        this.data =  new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<E> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(null);
            }
            data.add(row);
        }
        System.out.println(this.data);

    }

    public E get(int i , int j) {
        return data.get(i - 1).get(j - 1);
    }

    public void set(int i, int j, E content) {
        data.get(i - 1).set(j - 1, content);
    }

    public void  setRow(int i, E [] array) {

        List<E> row = Arrays.stream(array).toList();
        for (int j = 0; j < row.size(); j++) {
            data.get(i - 1).set(j, row.get(j));
        }
    }

    public void swap(int i1, int j1, int i2, int j2) {
       E temp = get(i1,j1);
       set(i1,j1,get(i2,j2));
       set(i2,j2,temp);
    }


    public String [] [] toArray() {
        String [] [] result = new String [data.size()] [data.get(0).size()];
        for (int i = 0; i < data.size(); i++) {
            Object [] objects = data.get(i).toArray();
            for (int j = 0; j < objects.length; j++) {
                result[i][j] = objects[j].toString();
            }
        }

        return result;
    }
    // TODO: Add get, set, setRow, swap and toArray
}