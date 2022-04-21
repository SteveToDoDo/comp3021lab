package comp3021lab;

import java.util.function.Predicate;
public class HelloWorld {
    public static void pred(int number1, Predicate<Integer> predicate)
    {
        if (predicate.test(number1)) {
            System.out.println(predicate.test(null));
        }
    }
    public static void main(String[] args)
    {
    	int k =10;
        pred(k, (i) -> i > k);
    }
}
