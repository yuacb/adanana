package com.adanana;

import java.math.BigDecimal;
import java.util.Scanner;

public class ChangePhoneNumbe {



    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("电话号码：");
//        String name = sc.nextLine();
        BigDecimal divisor =  new BigDecimal(10000000000D);
        BigDecimal phoneNumbe = new BigDecimal(18069447352D)   ;
        BigDecimal foo[] ;
        BigDecimal result;
        for(int i=0;i<11;i++){
            System.out.println("第一"+i+"次");
            foo = changeNumber(phoneNumbe,divisor);
            phoneNumbe = foo[1];
            System.out.println(phoneNumbe);
            System.out.println(foo[0]);
            phoneNumbe.add(foo[0]);
            divisor=divisor.divide(new BigDecimal(10));
            }
    }
    public static BigDecimal[] changeNumber(BigDecimal number,BigDecimal divisor){

        return  number.divideAndRemainder(divisor);
    }
}

 