(ns day3
  (:require [util :as util]))

(def input (map vec (util/get-day-input 3)))

(defn check-slope [right down]
  (count
   (filter
    #(= \# (nth (cycle (nth input %)) (/ (* right %) down)))
    (range 0 (count input) down))))

(defn task1 []
  (check-slope 3 1))

(defn task2 []
  (* (check-slope 1 1)
     (check-slope 3 1)
     (check-slope 5 1)
     (check-slope 7 1)
     (check-slope 1 2)))

(defn -main []
  (prn (task1))
  (prn (task2)))
