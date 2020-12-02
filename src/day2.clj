(ns day2
  (:require [util :as util]
            [clojure.string :as string]))

(defn task1 [input]
  (count
   (filter
    (fn [{:keys [x y char password]}]
      (<= x (get (frequencies password) char 0) y))
    input)))

(defn task2 [input]
  (count
   (filter
    (fn [{:keys [x y char password]}]
      (let [x-pos? (= char (get password (- x 1)))
            y-pos? (= char (get password (- y 1)))]
        (and (or x-pos? y-pos?) (not (and x-pos? y-pos?)))))
    input)))

(defn parse-line [line]
  (let [[x-y char password] (string/split line #" ")
        [x y] (string/split x-y #"-")]
    {:x (Long/parseLong x)
     :y (Long/parseLong y)
     :char (first char)
     :password password}))

(defn -main []
  (let [input (map parse-line (util/get-day-input 2))]
    (prn (task1 input))
    (prn (task2 input))))
