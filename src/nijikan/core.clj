(ns nijikan.core)

;; 1 (newborn), 2 (adult), or 3 (senior).

(defn neighbors [grid pos]
  (let [[x y] pos]
    (remove
      (fn [v] (or (nil? v) (zero? v)))
      (for [m [(dec x) x (inc x)]
            n [(dec y) y (inc y)]
            :when (not (and (= x m) (= y n)))]
        (get-in grid [m n])))))

(defn age
  "Age a cell based upon rules around current state and neighbors."
  [grid pos]
  (case (get-in grid pos)
    0 :empty
    1 :newborn
    2 :adult
    3 :senior))
