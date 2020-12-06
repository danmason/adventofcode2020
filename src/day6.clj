(ns day6
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.set :as cs]))

(defn parse-input [input]
  (map #(string/split % #"\n") (string/split input #"\n\n")))

(def input (-> (slurp (io/resource "day-6")) (parse-input)))

(defn task1 []
  (reduce
   (fn [answer-count group-responses]
     (-> (apply str group-responses)
         set
         count
         (+ answer-count)))
   0
   input))

(defn task2 []
  (reduce
   (fn [answer-count group-responses]
     (->>
      (map set group-responses)
      (apply cs/intersection)
      count
      (+ answer-count)))
   0
   input))

(defn -main []
  (prn (task1))
  (prn (task2)))
