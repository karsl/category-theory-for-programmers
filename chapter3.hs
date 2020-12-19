module Chapter3 where

{-
Exercise 1:
a.
digraph G {

    A;

    A -> A[label = "id"]
}

b.
Here we have a function from A to A (like a (+1) for integers or (++ "a") for strings).

This function is composable with itself. As we have a function f :: A -> A and (again) f :: A -> A,
there should also be function f . f :: A -> A .

Moreover, as we have f . f :: A -> A and f :: A -> A, these should be composable too.
Now we also have (f . f) . f = f . f . f :: A -> A.

This continues infinitely many times.

digraph G {

    A;

    A:e -> A:w[label = "id"]
    A:e -> A:w[label = "f"]
    A:e -> A:w[label = "f . f"]
    A:e -> A:w[label = "f . f . f"]
}

c.
digraph G {

    A;
    B;

    A -> A[label = "id"]
    B -> B[label = "id"]

    A -> B[label = "f"]
}

Let's consider the composition of arrows:
id . f = f
f . id = f
id . id = id
f . f doesn't compose.

d.
digraph G {

    rankdir="RL"

    A;

    A -> A[label = "a"]
    A -> A[label = "b"]
    A -> A[label = "c"]
    A -> A[label = "..."]
    A -> A[label = "z"]

    A:s -> A:s[label = "a.b"]
    A:s -> A:s[label = "a.b.c"]
    A:s -> A:s[label = "b.c.d"]
    A:s -> A:s[label = "a.b.c"]
}

There are infinitely many compositions again as in (b). Some of them are: a.a, a.b, a.c, ..., a.a.a, a.b.a, ...
-}

{-
Exercise 2
a.
Check for preorder:
+ Reflexivity: A ⊆ A
+ Transitivity: A ⊆ B ∧ B ⊆ C → A ⊆ C

Check for partial order:
+ Antisymmetry: A ⊆ B ∧ B ⊆ A → A = B

Check for total order:
- Connexity: A, B ⊆ C → A ⊆ B ∨ B ⊆ A ≡ ⊥
  Counterexample: A = {1}, B = {2}, C = {1, 2}

b.
Check for preorder:
+ Reflexivity: T1 ≼ T2
+ Transitivity: T1 ≼ T2 ∧ T2 ≼ T3 → T1 ≼ T3
  (Think that types are sets and T3 is the set of all integers and say our function expects an integer.
   T2 is the set of positive integers and T1 is the set of integers within the range [0, 9])

Check for partial order:
+ Antisymmetry: T1 ≼ T2 ∧ T2 ≼ T1 → T1 = T2
  This again holds. T1 is the set A, and T2 is the set B and the relation ≼ is the relation ⊆ in the part (a).

Check for total order:
- Connexity: We can have types that has no subtype relation between them.
  Think like two different structs or a struct and a boolean.
-}

-- Exercise 3
-- instance Semigroup Bool where
--   (<>) = (&&)

-- instance Monoid Bool where
--   mempty = True

-- b1 :: Bool
-- b1 = mempty <> (True <> False)

instance Semigroup Bool where
  (<>) = (||)

instance Monoid Bool where
  mempty = False
