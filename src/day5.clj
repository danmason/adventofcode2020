(ns day5
  (:require [util :as util]
            [clojure.set :as cs]))

(defn parse-pass [seat-string]
  (let [[row-chars col-chars] (split-at 7 seat-string)
        row (first
             (reduce
              (fn [rows row-char]
                (if (= row-char \F)
                  (take (/ (count rows) 2) rows)
                  (drop (/ (count rows) 2) rows)))
              (range 128)
              row-chars))
        col (first
             (reduce
              (fn [cols col-char]
                (if (= col-char \L)
                  (take (/ (count cols) 2) cols)
                  (drop (/ (count cols) 2) cols)))
              (range 8)
              col-chars))]
    {:row row
     :col col
     :seat-id (+ (* row 8) col)}))

(def input (map parse-pass (util/get-day-input 5)))

(defn task1 []
  (apply max (map :seat-id input)))

(defn task2 []
  (let [seat-ids (sort (map :seat-id input))
        full-seats (range (first seat-ids) (+ (last seat-ids) 1))]
    (first (cs/difference (set full-seats) (set seat-ids)))))

(defn -main []
  (prn (task1))
  (prn (task2)))
