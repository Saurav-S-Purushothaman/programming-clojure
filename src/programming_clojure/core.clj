(ns programming-clojure.core
  (:require [programming-clojure.demo :refer [fibinocci-series]])
  (:gen-class))

(defn -main
  "main"
  [& args]
  (println "Created fibinocci series number using function programming style")
  (println (fibinocci-series 10)))
