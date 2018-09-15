
import org.apache.tomcat.jni.Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class NamingTests {
    public static void main(String[] args) {
//        ArrayList<Integer> a_list = new ArrayList<>();
//        for(int i=0;i<20;i++){
//            a_list.add(i+1);
//        }
//        System.out.println(Arrays.toString(a_list.toArray()));
//        Collections.shuffle(a_list);
//        System.out.println(Arrays.toString(a_list.toArray()));

//        Random rand = new Random();
//        System.out.println(rand.nextInt(100));

        String hello = "hello";
        String world = "world";

        System.out.println(hello.concat(" ").concat(world));
        hello.concat(world);
        System.out.println(hello);
    }
}
