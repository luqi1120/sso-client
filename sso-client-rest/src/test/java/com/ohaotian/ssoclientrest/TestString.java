package com.ohaotian.ssoclientrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created: luQi
 * Date:2018-5-17 17:28
 */
public class TestString {

    public static void main(String[] args) {
        String str = "fweavbc";
        String s = reverseStringBuilder(str);
        System.out.println(s);
        Arrays.sort(s.getBytes());
    }

    public static String reverseStringBuilder(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        return stringBuffer.reverse().toString();
    }
}
