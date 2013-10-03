/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.sugen.beans;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Koukou
 */
public class SugenHelper {

    public static ArrayList<String> string2Array(String str) {
        ArrayList<String> result = new ArrayList<String>();
        if (str != null && str.length() > 0) {
            StringTokenizer st = new StringTokenizer(str, "|");
            if (st.hasMoreTokens()) {
                result.add(st.nextToken());
            }
        }
        return result;
    }

    public static String array2String(ArrayList<String> arr) {
        StringBuffer sb = new StringBuffer("");

        if (arr != null && arr.size() > 0) {
            for (int i = 0; i < arr.size(); i++) {
                sb.append("|");
                sb.append(arr.get(i));
            }
        }
        return sb.toString();
    }
}
