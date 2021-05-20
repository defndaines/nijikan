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
  "Age a cell based upon rules around current state and neighbors.
  Returns the new value for the cell."
  [grid pos]
  (let [near (neighbors grid pos)]
    (case (get-in grid pos)
      ;; Empty cells spawn newborns if exactly two adult neighbors
      0 (if (= [2 2] near)
          1
          0)

      ;; Newborn cells need at least 2 neighbors, but not more than 4 to grow to adulthood.
      1 (if (< 1 (count near) 5)
          2
          0)

      2 :adult
      3 :senior)))
