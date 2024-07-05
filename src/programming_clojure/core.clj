(ns programming-clojure.core
  (:require [programming-clojure.demo :refer [fibinocci-series
                                              is-string-blank?]])
  (:gen-class))


(defn -main
  "main"
  [& args]
  (println "Created fibinocci series number using function programming style")
  (println (fibinocci-series 10))
  (println "Checks if this string is blank \"    \" : " (is-string-blank? "   "))
  (println "Check if this string is blanck \"this\" : " (is-string-blank? "this")))
