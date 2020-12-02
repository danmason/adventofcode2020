(ns util
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn get-day-input [day]
  (-> (slurp (io/resource (format "day-%s" day)))
      string/split-lines))
