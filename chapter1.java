import java.util.function.Function;

public class chapter1 {

    /*
     * Exercise 1
     */
//    <T> T identity(T t) {
//        return t;
//    }
    <A> Function<A, A> identity() {
        return (A a) -> a;
    }

    /*
     * Exercise 2
     */
    <A, B, C> Function<A, C> compose(Function<A, B> f1, Function<B, C> f2) {
        return (A a) -> f2.apply(f1.apply(a));
    }

    /*
     * Exercise 3
     */
    boolean testCompositionWithIdentity() {
        boolean rv = true;

        Function<Integer, Integer> f1 = (Integer i) -> i + 1;
        Function<Integer, Integer> composedF1 = compose(f1, identity());
        rv &= composedF1.apply(1) == 2;

        Function<String, Integer> f2 = String::length;
        Function<String, Integer> composedF2 = compose(f2, identity());
        Function<String, Integer> composedF22 = compose(identity(), f2);
        rv &= composedF2.apply("Hello") == 5 && composedF22.apply("Hello") == 5;

        return rv;
    }

    public static void main(String[] args) throws Exception {
        if (!new chapter1().testCompositionWithIdentity())
            throw new Exception("Failed to test composition with identity.");
    }

    /*
     * Exercise 4
     * World Wide Web is a category where each website is an objects and the links are morphisms.
     *
     * 0. Composition? For all links from website A to B and B to C, is there a link from A to C?
     * No, not necessary. So I wouldn't say it's a category.
     *
     * However, it's possible to do that. Think like when website B add a link to their website that takes user to website C,
     * we can notify website As (all the website that has a link to website B) to add links to website C.
     * So let's assume we do really notify websites like that (or we have that rule for the world wide web)
     *
     * 1. Are compositions are associative?
     * There is link l1 from website A to website B.
     * There is link l2 from website B to website C.
     * There is link l3 from website C to website D.
     *
     * Is (l1 . l2) . l3 == l1 . (l2 . l3) ?
     *
     * If it's left associative:
     * composing links l1 and l2 makes a link, say l4, from website A to C. Composing this link with l3 makes a link from website A to D.
     *
     * If it's right associative:
     * composing links l2 and l3 makes a link, say l4, from website B to D. Composing this link with l1 makes a link from website A to D.
     *
     * So the compositions are associative.
     *
     * 2. For every website, is a there link whose source and target is the same website?
     * Yes, this is possible.
     */

    /*
     * Exercise 5
     * As a person can't be in friendship with himself/herself (there is no identity morphism), Facebook can't be a category.
     */

    /*
     * Exercise 6
     * A directed graph is a category when:
     * + For each vertex (node), there is self-loop (that is an edge that starts and ends on the same vertex).
     * + For each edge (v1, v2) and (v2, v3), there is an edge (v1, v3).
     */
}
