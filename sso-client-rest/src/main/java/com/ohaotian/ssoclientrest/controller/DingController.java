package com.ohaotian.ssoclientrest.controller;

import org.springframework.stereotype.Controller;

/**
 * Created: luQi
 * Date:2018-5-16 14:42
 */
@Controller
public class DingController {

    public static String reverse(String old) {
        String result = "";
        for (int i = old.length() - 1; i >= 0; i--) {
            result += String.valueOf(old.charAt(i));
        }
        return result;
    }

    public static void main(String[] args) {

        String result = DingController.reverse("hello");
        System.out.println(result);
    }
}