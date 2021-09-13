package com.company.sort;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Solution {

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
