(ns day4
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.spec.alpha :as s]))

(s/def ::byr #(<= 1920 (Long/parseLong %) 2002))
(s/def ::iyr #(<= 2010 (Long/parseLong %) 2020))
(s/def ::eyr #(<= 2020 (Long/parseLong %) 2030))
(s/def ::hgt #(let [[_ hgt-str unit] (re-matches #"(\d+)(\w+)" %)
                    hgt (Long/parseLong hgt-str)]
                (case unit
                  "cm" (<= 150 hgt 193)
                  "in" (<= 59 hgt 76)
                  nil)))
(s/def ::hcl #(re-matches #"#[a-fA-F0-9]{6}" %))
(s/def ::ecl #(contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} %))
(s/def ::pid #(re-matches #"[0-9]{9}" %))
(s/def ::passport (s/keys :req-un [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid] :opt-un [::cid]))

(defn parse-input [input]
  (map
   (fn [pass-string]
     (reduce
      (fn [passport kv]
        (let [[_ k v] (re-matches #"(.+):(.+)" kv)]
          (assoc passport (keyword k) v)))
      {}
      (string/split pass-string #"[\n? ]")))
   (string/split input #"\n\n")))

(def input (-> (slurp (io/resource "day-4")) (parse-input)))

(def required-keys #{:byr :iyr :eyr :hgt :hcl :ecl :pid})

(defn has-required-keys [passport]
  (let [passport-keys (set (keys passport))]
    (or (= passport-keys required-keys) (= passport-keys (conj required-keys :cid)))))

(defn task1 []
  (count (filter has-required-keys input)))

(defn task2 []
  (count (filter #(s/valid? ::passport %) input)))

(defn -main []
  (prn (task1))
  (prn (task2)))
