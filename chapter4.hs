module Chapter4 where

import Data.Char

type Writer a = (a, String)

(>=>) :: (a -> Writer b) -> (b -> Writer c) -> (a -> Writer c)
f >=> g = \ a ->
  let
    (b, str1) = f a
    (c, str2) = g b
    in (c, str1 ++ str2)

return :: a -> Writer a
return a = (a, "")

upCase :: String -> Writer String
upCase s = (toUpper <$> s, "upCase")

toWords :: String -> Writer [String]
toWords s = (words s, "toWords")

process :: String -> Writer [String]
process = upCase >=> toWords

-- Challenges

-- We can use Haskell's Maybe to represent partial functions.

-- Exercise 1
(>==>) :: (a -> Maybe b) -> (b -> Maybe c) -> (a -> Maybe c)
f >==> g = \ a -> case f a of
                    Just b -> g b
                    Nothing -> Nothing

return' :: a -> Maybe a
return' = Just

-- Exercise 2
safeReciprocal :: (Eq a, Fractional a) => a -> Maybe a
safeReciprocal 0 = Nothing
safeReciprocal n = Just $ 1 / n

-- Exercise 3
safeRoot :: (Ord a, Floating a) => a -> Maybe a
safeRoot n | n < 0 = Nothing
safeRoot n | otherwise = Just $ sqrt n

safeRootReciprocal :: Double -> Maybe Double
safeRootReciprocal = safeRoot >==> safeReciprocal
