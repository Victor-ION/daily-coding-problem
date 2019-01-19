package dcp_4;

import java.util.Arrays;

/*
This problem was asked by Stripe.
Given an array of integers, find the first missing positive integer in linear time and constant space.
In other words, find the lowest positive integer that does not exist in the array.
The array can contain duplicates and negative numbers as well.
For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
You can modify the input array in-place.
 */
public class dcp4 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 8, -5, 6, 0, 1, 12, -10, 2, 7, 4}; //5
        int [] arr2 = new int[] {3, 4, -1, 1}; //2
        int [] arr3 = new int[] {1, 2, 0}; //3
        int [] arr4 = new int[] {-1, -2, 0, -10, 3}; //1

        System.out.println(findLowestPosInt(arr));
        System.out.println(findLowestPosInt(arr2));
        System.out.println(findLowestPosInt(arr3));
        System.out.println(findLowestPosInt(arr4));
    }

    public static int findLowestPosInt(int[] arr) {
        for (int i = 0; i < arr.length;) {
            int curr = arr[i];
            if (curr <= 0 || curr > arr.length || curr == i + 1) {
                i++;
                continue;
            }
            arr[i] = arr[curr-1];
            arr[curr-1]= curr;
//            System.out.println("index: " + i);
//            System.out.println(Arrays.toString(arr));

        }
        int max = 0;
        System.out.println(Arrays.toString(arr));
        for (int k = 0; k < arr.length; k++){
            int curr = arr[k];
            if (curr <= max) continue;
            if (curr == max + 1) {
                max++;
                continue;
            }
            return max + 1;
        }
        return max + 1;
    }
}
