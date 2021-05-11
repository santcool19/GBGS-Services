package com.gbgs.edu.programm.ds;

public class FrogLongestJump {
    public static void main(String args[]) {
        int[] t = {2, 6, 8, 5};
        System.out.println(new FrogLongestJump().solution(t));
        //int[] t2 = {1,5,5,2,6};
        //System.out.println(new FrogLongestJump ().solution(t2));
    }

    public int solution(int[] blocks) {
        Integer maxSum = null;
        int start;
        int maxDistance = 0;
        int i;
        if (blocks.length < 2) {
            return blocks.length;
        }
        i = 0;
        while (i < blocks.length - 1) {
            while ((i - 1 >= 0) && (i < blocks.length) && (blocks[i - 1] == blocks[i]))
                i--;
            start = i;
            int downSum = blocks[i];
            while ((i + 1 < blocks.length) && (blocks[i + 1] <= blocks[i])) {
                downSum += blocks[++i];
            }
            int upSum = 0;
            while ((i + 1 < blocks.length) && (blocks[i + 1] >= blocks[i])) {
                upSum += blocks[++i];
            }
            int sum = upSum + downSum;
            if ((maxSum == null) || (sum > maxSum)) {
                maxSum = sum;
                maxDistance = i - start + 1;
            }
        }
        return maxDistance;
    }
}