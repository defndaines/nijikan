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
