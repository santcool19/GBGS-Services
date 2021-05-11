package com.gbgs.edu.programm.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GenericIntermediate {
    public static void main(String args[]) {
    }

    public static List<List> findTriplets(int[] numbers, int sum) {
        List<List> tripletsCombo = new ArrayList<List>();
        HashSet set = new HashSet();
        List triplets = new ArrayList();
        if (numbers.length == 0 || sum <= 0) {
            return tripletsCombo;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            int j = i + 1;
            int k = numbers.length - 1;
            while (j < k) {
                if (numbers[i] + numbers[j] + numbers[k] == sum) {
                    String str = numbers[i] + "," + numbers[j] + "," + numbers[k];
                    if (!set.contains(str)) {
                        triplets.add(numbers[i]);
                        triplets.add(numbers[j]);
                        triplets.add(numbers[k]);
                        tripletsCombo.add(triplets);
                        triplets = new ArrayList();
                        set.add(str);
                    }
                    j++;
                    k--;
                } else if (numbers[i] + numbers[j] + numbers[k] < sum) {
                    j++;
                } else { // numbers[i] + numbers[j] + numbers[k] > sum
                    k--;
                }
            }
        }

        return tripletsCombo;
    }
}
