(ns nijikan.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [nijikan.core :as njk]))

(deftest neighbors-test
  (let [grid [[0 1 0 0]
              [0 1 3 1]
              [0 0 1 0]
              [0 2 0 0]]]
    (testing "able to retrieve neighbors"
      (let [senior-pos [1 2]]
        (is (= 3 (get-in grid senior-pos))
            "sanity test to ensure position is correct")
        (is (= [1 1 1 1] (njk/neighbors grid senior-pos))
            "expected four newborn neighbors"))

      (let [adult-pos [3 1]]
        (is (= 2 (get-in grid adult-pos)))
        (is (= [1] (njk/neighbors grid adult-pos)))))))

(deftest age-test
  (testing "aging of empty cells"
    (is (= 0 (njk/age [[0]] [0 0])))
    (is (= 1 (njk/age [[0 2]
                       [2 0]] [0 0])))
    (is (= 0 (njk/age [[0 2]
                       [2 2]] [0 0]))))

  (testing "aging of newborn cells"
    (is (= 0 (njk/age [[1]] [0 0])))
    (is (= 0 (njk/age [[0 0 0]
                       [1 1 1]
                       [1 1 1]] [1 1])))
    (is (= 0 (njk/age [[0 0 0]
                       [0 1 0]
                       [0 1 0]] [1 1])))
    (is (= 2 (njk/age [[0 0 0]
                       [1 1 0]
                       [1 1 1]] [1 1])))
    (is (= 2 (njk/age [[1 1 0]
                       [0 1 0]
                       [0 0 0]] [1 1]))))

  (testing "aging of adult cells"
    (is (= 0 (njk/age [[2]] [0 0])))
    (is (= 0 (njk/age [[0 0 0]
                       [0 2 0]
                       [0 0 0]] [1 1])))
    (is (= 3 (njk/age [[0 0 0]
                       [0 2 0]
                       [0 1 0]] [1 1])))
    (is (= 3 (njk/age [[0 3 0]
                       [0 2 0]
                       [0 1 0]] [1 1])))
    (is (= 0 (njk/age [[0 0 0]
                       [0 2 2]
                       [0 1 3]] [1 1]))))

  (testing "aging of senior cells"
    (is (= 0 (njk/age [[3]] [0 0])))))

(def case-one
  [[0 0 2 2 0 0 0 0 0 0]
   [0 0 0 0 1 3 1 0 0 0]
   [0 0 0 2 0 3 0 0 1 3]
   [0 0 0 0 1 3 0 0 0 3]
   [0 2 2 0 0 0 1 3 1 0]
   [0 0 0 0 0 2 2 0 0 0]
   [0 0 2 0 2 0 0 0 0 0]
   [0 0 0 0 2 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def case-one-after
  [[0 0 3 3 0 0 0 0 0 0]
   [0 0 0 0 2 0 2 0 0 0]
   [0 0 0 3 0 0 0 0 2 0]
   [0 1 0 1 2 0 0 0 0 0]
   [0 3 3 0 0 1 2 0 2 0]
   [0 0 0 0 1 0 0 0 0 0]
   [0 0 0 0 3 0 1 0 0 0]
   [0 0 0 0 3 1 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def case-two
  [[0 0 0 0 0 0 0 0 0 0]
   [0 0 1 1 0 0 2 0 0 0]
   [0 0 3 3 2 0 0 0 2 0]
   [0 1 0 0 0 0 0 0 2 0]
   [0 0 3 0 0 1 2 0 0 0]
   [0 0 1 3 3 3 0 0 0 0]
   [0 0 0 1 0 1 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def case-two-after
  [[0 0 0 0 0 0 0 0 0 0]
   [0 0 2 2 0 1 0 1 0 0]
   [0 0 0 0 3 1 0 0 3 1]
   [0 2 0 0 0 1 0 0 3 1]
   [0 0 0 0 0 2 3 1 0 0]
   [0 0 2 0 0 0 0 0 0 0]
   [0 0 0 2 0 2 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def case-three
  [[0 0 0 0 1 3 1 0 0 0]
   [0 0 0 0 0 3 0 0 0 0]
   [0 0 2 0 0 0 0 3 1 0]
   [0 0 2 0 2 3 0 0 3 0]
   [0 0 2 0 0 0 0 3 0 0]
   [0 0 0 0 0 2 1 3 1 0]
   [0 0 0 0 2 2 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(def case-three-after
  [[0 0 0 0 2 0 2 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 1 3 0 0 0 0 0 2 0]
   [0 0 3 0 3 0 0 0 0 0]
   [0 1 3 0 1 1 0 0 0 0]
   [0 0 0 1 0 0 2 0 2 0]
   [0 0 0 0 3 0 1 0 0 0]
   [0 0 0 0 1 1 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(deftest generation-test
  (testing "test case one: expected grid after one generation"
    (is (= case-one-after
           (njk/generation case-one))))
  (testing "test case two: expected grid after one generation"
    (is (= case-two-after
           (njk/generation case-two))))
  (testing "test case three: expected grid after one generation"
    (is (= case-three-after
           (njk/generation case-three)))))

(def ten-ten
  "The 10x10 grid from the problem set we have to solve for."
  [[0 0 0 0 0 0 0 0 0 0]
   [0 0 1 1 0 0 0 0 0 0]
   [0 0 0 0 2 0 0 0 0 0]
   [0 0 0 1 2 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 1 0 0 0 0 0 0 0]
   [0 2 1 0 0 0 0 0 0 0]
   [0 2 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]
   [0 0 0 0 0 0 0 0 0 0]])

(deftest run-test
  (testing "able to run multiple generations of a grid"
    (is (= ten-ten
           (first (njk/run ten-ten))))
    ;; Per problem ask, the starting grid is the "1" generation.
    (is (not= ten-ten
              (nth (njk/run ten-ten) 1))
        "asking for first generation gives a changed grid")
    (is (= (njk/generation ten-ten)
           (second (njk/run ten-ten)))))
  (testing "generation 20"
    (is (= [[0 0 0 0 0 0 0 0 0 0]
            [0 0 0 0 0 0 0 0 0 0]
            [0 0 0 0 0 0 0 0 0 0]
            [0 2 2 0 2 2 0 0 0 0]
            [0 0 0 0 0 0 1 3 1 0]
            [0 0 2 0 0 0 0 3 0 0]
            [0 0 0 2 0 0 1 3 1 0]
            [0 0 0 0 0 0 2 0 0 0]
            [0 0 0 0 2 2 0 0 0 0]
            [0 0 0 0 0 0 0 0 0 0]]
           (first (drop 19 (njk/run ten-ten)))))))
