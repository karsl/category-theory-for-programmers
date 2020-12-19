import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class chapter2<A, B> {

    /*
     * Exercise 1
     */
    Map<A, B> ret = new HashMap<>();

    Function<A, B> memoize(Function<A, B> f) {
        return a -> {
            B previousResult = ret.get(a);

            if (previousResult == null) {
                previousResult = f.apply(a);
                ret.put(a, previousResult);
            }

            return previousResult;
        };
    }

    Integer sum(Integer n) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += i;
        }

        return sum;
    }

    void testExercise1() {
        chapter2 chapter2 = new chapter2();

        Function<Integer, Integer> memoizedSum = chapter2.memoize((Function<Integer, Integer>) chapter2::sum);

        Integer param = Integer.MAX_VALUE;

        long millis = System.currentTimeMillis();
        memoizedSum.apply(param);
        System.out.println("Delta time first run: " + (System.currentTimeMillis() - millis));

        millis = System.currentTimeMillis();
        memoizedSum.apply(param);
        System.out.println("Delta time first run: " + (System.currentTimeMillis() - millis));
    }

    public static void main(String[] args) {
        new chapter2<>().testExercise3();
    }

    /*
     * Exercise 2
     * No, it wouldn't work. Because the function isn't pure (In Java, the random function gets this "random" value
     * from the machine's uptime value) so for same arguments result might be different.
     */

    /*
     * Exercise 3
     * As we get the seed from the parameter, not from the outside, the function is pure now. So, it always generates
     * the same result for the same input and memoize works.
     */
    Integer rng(Integer seed) {
        return new Random(seed).nextInt();
    }

    void testExercise3() {
        chapter2 chapter2 = new chapter2();


        Function<Integer, Integer> f = chapter2::rng;
        Function<Integer, Integer> memoizedRandom = (Function<Integer, Integer>) memoize((Function<A, B>) f);

        Integer seed = 50;
        for (int i = 0; i < 10; i++) {
            System.out.println("Result: " + memoizedRandom.apply(seed));
        }
    }

    /*
     * Exercise 4
     * Only (a) is pure.
     * (b) isn't pure as it does I/O operation. Moreover, the user may press different buttons on each call so we may
     * get different result for the same arguments and so it can't be memoized.
     * (c) isn't pure as it does I/O operation. Although it's return value doesn't vary (it's always true), it has
     * side effects: it writes something to the standard output. After being memoized, it'll never print something
     * to the standard output.
     * (d) isn't pure as y's value many be different for each function call as it's a static variable.
     */

    /*
     * Exercise 5
     * There can be 4 different Bool -> Bool functions in total.
     * b1 maps true -> true, false -> true.
     * b2 maps true -> true, false -> false.
     * b3 maps true -> false, false -> true.
     * b4 maps true -> false, false -> false.
     */
    boolean b1(boolean b) {
        return true;
    }

    boolean b2(boolean b) {
        return b;
    }

    boolean b3(boolean b) {
        return !b;
    }

    boolean b4(boolean b) {
        return false;
    }

    /*
     * Exercise 6
    digraph G {

    Void;
    Unit;
    Bool;

    Bool -> Bool[label="b1, id, b3, b4"];
    Unit -> Unit[label="id"]
    Void -> Void[label="id"]

    Void -> Unit[label="absurd"]
    Void -> Bool[label="absurd"]

    Unit -> Bool[label="true, false"]

    Bool -> Unit[label="unit"]
    }
     */

}
