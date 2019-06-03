package com.github.skyisbule.chain.common;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.util.Random;

public class Security {

    //密钥
    private static byte[] key = "smkldospdosldaaasmkldospdosldaaa".getBytes();

    public static String encode(String str) {
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密为16进制表示
        return aes.encryptHex(str);
        //解密为字符串

    }

    public static String decode(String str) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //解密
        return aes.decryptStr(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] a){
        String as = "asdfdsafdsaf";
        System.out.println(encode(as));
        System.out.println(decode(encode(as)));
    }

    public static String getHash(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789000";
        Random random=new Random();
        StringBuilder sb=new StringBuilder("0x");
        for(int i=0;i<40;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getTransactionHash(){
        String str="01234560123456abcdefghijklmnopqrstuvwxyz0000000000000000000000000000000000";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<40;i++){
            int number=random.nextInt(72);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
