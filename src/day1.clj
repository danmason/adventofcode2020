(ns day1
  (:require [util :as util]))

(def input (map #(Long/parseLong %) (util/get-day-input 1)))

(defn task1 []
  (loop [xs input]
    (when-let [x (first xs)]
      (if-let [product (some (fn [y]
                               (when (= 2020 (+ x y))
                                 (* x y)))
                             (rest xs))]
        product
        (recur (rest xs))))))

(defn task2 []
  (loop [xs input]
    (when-let [x (first xs)]
      (if-let [product (loop [ys (rest xs)]
                         (when-let [y (first ys)]
                           (if-let [product (some (fn [z]
                                                    (when (= 2020 (+ x y z))
                                                      (* x y z)))
                                                  (rest ys))]
                             product
                             (recur (rest ys)))))]
        product
        (recur (rest xs))))))

(defn -main []
  (prn (task1))
  (prn (task2)))
