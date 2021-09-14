package com.company.sort;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Sort {

    public static void quicksort(int[] a, int low, int high){
        int i, j , index;
        if(low > high){
            return;
        }
        i = low;
        j = high;
        index = a[i];
        while(i < j){
            while(i < j && a[j] >= index){
                j--;
            }
            if(i < j){
                a[i++] = a[j];
            }
            while(i< j && a[i] < index){
                i++;
            }
            if(i < j){
                a[j--] = a[i];
            }
        }
        a[i] = index;
        quicksort(a, low, i - 1);
        quicksort(a, i + 1, high);

    }

    public static void sort(int a[]) {
        quicksort(a, 0, a.length - 1);
    }
    public static void main(String[] args) {
        int a[] = {45, 36, 7, 91, 34};
        sort(a);
        System.out.println(Arrays.toString(a));

    }
}

class AllSort{

    public static void main(String[] args) {
        Arrays.sort(new int[10]);
    }



// 冒泡排序

    public static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j + 1]){
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


// 选择排序

    public static void selectionSort(int[] arr){
        int minIndex;
        for(int i = 0; i < arr.length - 1; i++){
            minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[minIndex] > arr[j]){
                    // 记录最小值的下标
                    minIndex = j;
                }
            }
            // 将最小元素交换至首位
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }


// 插入排序

// 交换法插入排序

    public static void insertSort1(int[] arr){
        // 从第二个数开始，往前插入数字
        for(int i = 1; i < arr.length; i++){
            // j记录当前数字下标
            int j = i;
            // 当前数字比前一个数字小，则将当前数字与前一个数字交换
            while(j >= 1 && arr[j] < arr[j - 1]){
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                // 更新当前数字下标
                j--;
            }
        }
    }

// 移动法插入排序

    public static void insertSort2(int[] arr) {
        // 从第二个数开始，往前插入数字
        for (int i = 1; i < arr.length; i++) {
            int currentNumber = arr[i];
            int j = i - 1;
            // 寻找插入位置的过程中，不断地将比 currentNumber 大的数字向后挪
            while (j >= 0 && currentNumber < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 两种情况会跳出循环：1. 遇到一个小于或等于 currentNumber 的数字，跳出循环，currentNumber 就坐到它后面。
            // 2. 已经走到数列头部，仍然没有遇到小于或等于 currentNumber 的数字，也会跳出循环，此时 j 等于 -1，currentNumber 就坐到数列头部。
            arr[j + 1] = currentNumber;
        }
    }


// 希尔排序

// 本质上是对插入排序的一种优化

// 增量序列的选择会极大地影响希尔排序的效率
// 希尔增量序列：Dm = N / 2, Dk = Dk+1 / 2

    public static void shellSort(int[] arr) {
        // 间隔序列，在希尔排序中我们称之为增量序列
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 分组
            for (int groupStartIndex = 0; groupStartIndex < gap; groupStartIndex++) {
                // 插入排序
                for (int currentIndex = groupStartIndex + gap; currentIndex < arr.length; currentIndex += gap) {
                    // currentNumber 站起来，开始找位置
                    int currentNumber = arr[currentIndex];
                    int preIndex = currentIndex - gap;
                    while (preIndex >= groupStartIndex && currentNumber < arr[preIndex]) {
                        // 向后挪位置
                        arr[preIndex + gap] = arr[preIndex];
                        preIndex -= gap;
                    }
                    // currentNumber 找到了自己的位置，坐下
                    arr[preIndex + gap] = currentNumber;
                }
            }
        }
    }


// Knuth增量序列：D1 = 1， Dk+1 = 3 * Dk + 1, 即1, 4, 13, 40..., 数学界猜想其平均时间复杂度为O(n3/2)

    public static void shellSortByKnuth(int[] arr) {
        // 找到当前数组需要用到的 Knuth 序列中的最大值
        int maxKnuthNumber = 1;
        while (maxKnuthNumber <= arr.length / 3) {
            maxKnuthNumber = maxKnuthNumber * 3 + 1;
        }
        // 增量按照 Knuth 序列规则依次递减
        for (int gap = maxKnuthNumber; gap > 0; gap = (gap - 1) / 3) {
            // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
            for (int i = gap; i < arr.length; i++) {
                // currentNumber 站起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }


// 堆排序

// 堆：符合以下两个条件之一的完全二叉树
// · 根节点的值 >= 子节点的值，这样的堆被称为最大堆，或大顶堆（数组中大数在前）
// · 根节点的值 <= 子节点的值，这样的堆被称为最小堆，或小顶堆（数组中大数在后）

    public static void heapSort(int[] arr) {
        // 构建初始大顶堆
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            // 将最大值交换到数组最后
            swap(arr, 0, i);
            // 调整剩余数组，使其满足大顶堆
            maxHeapify(arr, 0, i);
        }
    }
    // 构建初始大顶堆
    private static void buildMaxHeap(int[] arr) {
        // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length / 2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }
    // 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
    private static void maxHeapify(int[] arr, int i, int heapSize) {
        // 左子结点下标
        int l = 2 * i + 1;
        // 右子结点下标
        int r = l + 1;
        // 记录根结点、左子树结点、右子树结点三者中的最大值下标
        int largest = i;
        // 与左子树结点比较
        if (l < heapSize && arr[l] > arr[largest]) {
            largest = l;
        }
        // 与右子树结点比较
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            // 将最大值交换为根结点
            swap(arr, i, largest);
            // 再次调整交换数字后的大顶堆
            maxHeapify(arr, largest, heapSize);
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


// 快速排序

// 快速排序算法的基本思想是：
// · 从数组中取出一个数，称之为基数（pivot）
// · 遍历数组，将比基数大的数字放到它的右边，比基数小的数字放到它的左边。遍历完成后，数组被分成了左右两个区域
// · 将左右两个区域视为两个数组，重复前两个步骤，直到排序完成

// 快速排序的时间复杂度为 O(nlogn) ～ O(n^2)，平均时间复杂度为 O(nlogn)

// 最简单的分区算法

    public static void quickSort1(int[] arr){
        quickSort1(arr, 0, arr.length - 1);
    }
    public static void quickSort1(int[] arr, int start, int end){
        // 如果区域内的数字少于2个，退出递归
        if(start >= end) return;
        // 将数组分组，并获得中间值的下标
        int middle = partition1(arr, start, end);
        // 对左边区域快速排序
        quickSort1(arr, start, middle - 1);
        // 对右边区域快速排序
        quickSort1(arr, middle + 1, end);
    }
    // 将arr从start到end分区，左边区域比基数小，右边区域比基数大，然后返回中间值的下标
    public static int partition1(int[] arr, int start, int end){
        // 取第一个数为基数
        int pivot = arr[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        // left、right相遇时退出循环
        while(left < right){
            // 找出第一个大于基数的位置
            while(left < right && arr[left] <= pivot) left++;
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if(left != right){
                exchange1(arr, left, right);
                right--;
            }
        }
        // 如果left和right相等，单独比较arr[right]和pivot
        if(left == right && arr[right] > pivot) right--;
        // 将基数和中间数交换
        if(right != start) exchange(arr, start, right);
        // 返回中间值的下标
        return right;
    }
    private static void exchange1(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

// 双指针分区算法

    public static void quickSort2(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }
    public static void quickSort2(int[] arr, int start, int end) {
        // 如果区域内的数字少于 2 个，退出递归
        if (start >= end) return;
        // 将数组分区，并获得中间值的下标
        int middle = partition(arr, start, end);
        // 对左边区域快速排序
        quickSort2(arr, start, middle - 1);
        // 对右边区域快速排序
        quickSort2(arr, middle + 1, end);
    }
    // 将 arr 从 start 到 end 分区，左边区域比基数小，右边区域比基数大，然后返回中间值的下标
    public static int partition(int[] arr, int start, int end) {
        // 取第一个数为基数
        int pivot = arr[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        while (left < right) {
            // 找到第一个大于基数的位置
            while (left < right && arr[left] <= pivot) left++;
            // 找到第一个小于基数的位置
            while (left < right && arr[right] >= pivot) right--;
            // 交换这两个数，使得左边分区都小于或等于基数，右边分区大于或等于基数
            if (left < right) {
                exchange(arr, left, right);
                left++;
                right--;
            }
        }
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && arr[right] > pivot) right--;
        // 将基数和轴交换
        exchange(arr, start, right);
        return right;
    }
    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



// 归并排序


// 将 1 个数字组成的有序数组合并成一个包含 2 个数字的有序数组
// 再将 2 个数字组成的有序数组合并成包含 4 个数字的有序数组...直到整个数组排序完成
// 这就是归并排序（Merge Sort）的思想

    public static void mergeSort(int[] arr) {
        if (arr.length == 0) return;
        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
    }

    // 对 arr 的 [start, end] 区间归并排序
    private static void mergeSort(int[] arr, int start, int end, int[] result) {
        // 只剩下一个数字，停止拆分
        if (start == end) return;
        int middle = (start + end) / 2;
        // 拆分左边区域，并将归并排序的结果保存到 result 的 [start, middle] 区间
        mergeSort(arr, start, middle, result);
        // 拆分右边区域，并将归并排序的结果保存到 result 的 [middle + 1, end] 区间
        mergeSort(arr, middle + 1, end, result);
        // 合并左右区域到 result 的 [start, end] 区间
        merge(arr, start, end, result);
    }

    // 将 result 的 [start, middle] 和 [middle + 1, end] 区间合并
    private static void merge(int[] arr, int start, int end, int[] result) {
        int end1 = (start + end) / 2;
        int start2 = end1 + 1;
        // 用来遍历数组的指针
        int index1 = start;
        int index2 = start2;
        while (index1 <= end1 && index2 <= end) {
            if (arr[index1] <= arr[index2]) {
                result[index1 + index2 - start2] = arr[index1++];
            } else {
                result[index1 + index2 - start2] = arr[index2++];
            }
        }
        // 将剩余数字补到结果数组之后
        while (index1 <= end1) {
            result[index1 + index2 - start2] = arr[index1++];
        }
        while (index2 <= end) {
            result[index1 + index2 - start2] = arr[index2++];
        }
        // 将 result 操作区间的数字拷贝到 arr 数组中，以便下次比较
        while (start <= end) {
            arr[start] = result[start++];
        }
    }


/*

希尔排序
    希尔排序是一个承上启下的算法，通过交换间隔较远的元素，使得一次交换能消除一个以上的逆序对，打破了在空间复杂度为O(1)的情况下，时间复杂度O(n^2)的魔咒。它启发出了后续一系列时间复杂度为 O(n\log n)O(nlogn)，空间复杂度为 O(1)O(1) 的排序算法。
    希尔排序本质上是插入排序的优化，先对间隔较大的元素进行插入排序，完成宏观调控，然后逐步缩小间隔，最后一轮一定是间隔为 11 的排序，也就是插入排序。间隔在希尔排序中被称为「增量」，增量序列不同，希尔排序的效率也不同。

堆排序
    堆排序分为两步：初始化建堆、重建堆。

    排序过程是：
    用数列构建出一个大顶堆，取出堆顶的数字；
    调整剩余的数字，构建出新的大顶堆，再次取出堆顶的数字；
    循环往复，完成整个排序。

快速排序
    快速排序算法是面试中考察的重点，也是应用最广泛的排序算法。

    排序过程是：
    从数组中取出一个数，称之为基数（pivot）；
    遍历数组，将比基数大的数字放到它的右边，比基数小的数字放到它的左边。遍历完成后，数组被分成了左右两个区域；
    将左右两个区域视为两个数组，重复前两个步骤，直到排序完成。
    快速排序中最重要的是分区算法，最常用的分区算法是双指针分区算法，优点是一次交换可以完成两个数的分区。

归并排序
    归并排序分为两步：二分拆数组、不断合并两个有序列表。
    归并的优化主要在于减少临时空间的开辟。
    不存在空间复杂度为O(1)的归并排序。

相同点
    平均时间复杂度都在O(n)到 O(n^2)之间。

不同点
    希尔排序、堆排序、快速排序是不稳定的，归并排序是稳定的。
    希尔排序的平均复杂度界于 O(n)到 O(n^2)之间，普遍认为它最好的时间复杂度为 O(n^{1.3})，希尔排序的空间复杂度为 O(1)；堆排序的时间复杂度为 O(nlogn)，空间复杂度为 O(1)，快速排序的平均时间复杂度为O(nlogn)，平均空间复杂度为O(logn)；归并排序的时间复杂度是O(nlogn)，空间复杂度是 O(n)。

*/

/*
Arrays.sort():

    在对基本数据类型的数组排序时，Arrays.sort() 函数通过调用 DualPivotQuicksort.sort() 完成排序；
    当数组长度达到 286 ，并且不存在较多连续相等元素，并且「高度结构化」时，采用类似 TimSort 的算法进行排序；
    当数组长度小于 INSERTION_SORT_THRESHOLD（即 47）时，采用插入排序或双插入排序；
    否则采用双轴快排进行排序。

*/


}





//        Scanner input1 = new Scanner(System.in);
//        System.out.print("输入data:");
//        String data = input1.next();
//        System.out.println("data为: " + data);
//        input1.close();
//
//        // 写浮点数
//        String float_number = data.substring(4, 8) + data.substring(0, 4);
//        // 十六进制转换为十进制浮点数
//        Float float_value = Float.intBitsToFloat((new BigInteger(float_number, 16).intValue()));
//        System.out.println(float_value);

//         //写整数
//        String int_number = data;
//        // 十六进制转换为十进制整数
//        long int_num = Long.parseLong(int_number,16);
//        System.out.println(int_num);
//    }
//}

//        String function;
//        if(function_code.equals("03")){
//            System.out.println("读取整数或者浮点数：");
////            System.out.println(data);
//            String byte_number = data.substring(4,6);
//            if(byte_number.equals("04")){
//                System.out.println("浮点数：");
//                // 写浮点数
//                String float_number = data.substring(10,14) + data.substring(6,10);
//                // 十六进制转换为十进制浮点数
//                Float float_value = Float.intBitsToFloat((new BigInteger(float_number,16).intValue()));
//                System.out.println(float_value);
//            }
//            if(byte_number.equals("02")){
//                System.out.println("整数：");
//                // 写整数
//                String int_number = data.substring(6,10);
//                // 十六进制转换为十进制整数
//                long int_num = Long.parseLong(int_number,16);
//                System.out.println(int_num);
//            }
//        }
//    }
//}

/*
* 数据解析：
* if （功能码为03，为读取整数或者浮点数）{
*       if （寄存器编号为00 03，则为温度测量值）{
*               if （返回data字节数量为02，为整数测量值）{
*                       读取转换寄存器数据十六进制整数A B；
*              }
*              if （返回data字节数量为04，为浮点测量值）{
*                      读取转换寄存器数据十六进制浮点数C D A B；
*              }
*       }
*       else if (其他寄存器编号）{
*               读取处理其他测量值，补充；
*       }
* }
* if (功能码为10，为写浮点数）{
*       if （寄存器起始地址为00 12，为写测量值偏移量）{
*               字节数量为04时，写寄存器数据十六进制浮点数C D A B;
*       }
*       else if （其他情况，补充）{
*       }
* }
* if （功能码为06，为写整数）{
*       if （寄存器起始地址为00 19，为写设备地址）{
*               写寄存器数据十六进制整数A B;
*       }
*       else if（其他情况，补充）{
*       }
* }
*
*
*
* */







//package com.company;
//
//        import java.math.BigInteger;
//        import java.util.HashMap;
//        import java.util.Map;
//        import java.util.Scanner;
//
//
//class Solution{
//
//    public static void main(String[] args) {
//
//        Scanner input1 = new Scanner(System.in);
//        System.out.print("输入command:");
//        String command = input1.next();
//        System.out.println("command为: "+ command);
////        input1.close();
//
////        Scanner input2 = new Scanner(System.in);
//        System.out.print("输入data:");
//        String data = input1.next();
//        System.out.println("data为: "+ data);
//        input1.close();
//
//        String device_type = command.substring(0,2);
//        String device_name;
//        if(device_type.equals("03")){
//            device_name = "溶解氧传感器";
//        }
//
//        String function_code = command.substring(2,4);
//        String function;
//        if(function_code.equals("03")){
//            System.out.println("读取整数或者浮点数：");
////            System.out.println(data);
//            String byte_number = data.substring(4,6);
//            if(byte_number.equals("04")){
//                System.out.println("浮点数：");
//                // 写浮点数
//                String float_number = data.substring(10,14) + data.substring(6,10);
//                // 十六进制转换为十进制浮点数
//                Float float_value = Float.intBitsToFloat((new BigInteger(float_number,16).intValue()));
//                System.out.println(float_value);
//            }
//            if(byte_number.equals("02")){
//                System.out.println("整数：");
//                // 写整数
//                String int_number = data.substring(6,10);
//                // 十六进制转换为十进制整数
//                long int_num = Long.parseLong(int_number,16);
//                System.out.println(int_num);
//            }
//        }
//    }
//}
//
///*
// * 数据解析：
// * if （功能码为03，为读取整数或者浮点数）{
// *       if （寄存器编号为00 03，则为温度测量值）{
// *               if （返回data字节数量为02，为整数测量值）{
// *                       读取转换寄存器数据十六进制整数A B；
// *              }
// *              if （返回data字节数量为04，为浮点测量值）{
// *                      读取转换寄存器数据十六进制浮点数C D A B；
// *              }
// *       }
// *       else if (其他寄存器编号）{
// *               读取处理其他测量值，补充；
// *       }
// * }
// * if (功能码为10，为写浮点数）{
// *       if （寄存器起始地址为00 12，为写测量值偏移量）{
// *               字节数量为04时，写寄存器数据十六进制浮点数C D A B;
// *       }
// *       else if （其他情况，补充）{
// *       }
// * }
// * if （功能码为06，为写整数）{
// *       if （寄存器起始地址为00 19，为写设备地址）{
// *               写寄存器数据十六进制整数A B;
// *       }
// *       else if（其他情况，补充）{
// *       }
// * }
// *
// *
// *
// * */
