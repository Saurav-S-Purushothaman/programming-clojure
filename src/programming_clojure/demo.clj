(ns programming-clojure.demo
  (:gen-class))

(defn fibinocci-series
  "Generates a fibinocci series starting from 0 till n"
  [n]
  (->> (iterate (fn [[a b]] [b (+ a b)]) [0 1])
       (map first)
       (take n)))
