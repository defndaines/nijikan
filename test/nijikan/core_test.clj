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

(deftest generation-test
  (testing "test case one: expected grid after one generation"
    (is (= case-one-after
           (njk/generation case-one)))))

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
