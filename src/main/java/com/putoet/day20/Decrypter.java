package com.putoet.day20;

import java.util.List;

public class Decrypter {
    private final int[] numbers;

    public Decrypter(int[] numbers) {
        assert numbers != null;

        this.numbers = numbers;
    }

    public Decrypter mix() {
        final int[] copy = new int[numbers.length];
        System.arraycopy(numbers, 0, copy, 0, numbers.length);

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0)
                continue;

            int ci = find(copy, numbers[i]);
            int nci = ci + (numbers[i] % numbers.length);
            if (nci <= 0)
                nci = numbers.length + nci - 1;
            else if (nci >= numbers.length)
                nci = nci - numbers.length + 1;

            if (ci < nci) {
                for (int x = ci; x < nci; x++) {
                    copy[x] = copy[x + 1];
                }
                copy[nci] = numbers[i];
            } else {
                for (int x = ci; x > nci; x--) {
                    copy[x] = copy[x - 1];
                }
                copy[nci] = numbers[i];
            }
        }


        return new Decrypter(copy);
    }

    private static int find(int[] array, int value) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == value)
                return i;

        throw new IllegalStateException("Element " + value + " not found");
    }

    public int[] numbers() {
        return numbers;
    }

    public List<Integer> groveCoordinates() {
        final int zero = find(numbers, 0);
        return List.of(1000, 2000, 3000).stream()
                .mapToInt(i -> numbers[(zero + i) % numbers.length])
                .boxed()
                .toList();
    }
}
