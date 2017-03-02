package md5;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by Administrator on 2017/2/17.
 */
public class Exercise {
    public static void main(String[] args) {
        Vector<String> strings = new Vector<>();
        Set<String> set = new HashSet<>();
        String p1 = "123";
        String p2 = "456";
        String p3 = "789";
        String p4 = "123";

        strings.add(p1);
        strings.add(p2);
        strings.add(p3);
        strings.add(p4);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        System.out.println(strings.size());
        System.out.println(set.size());
    }
}
