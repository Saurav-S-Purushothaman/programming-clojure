(ns programming-clojure.core
  (:require [programming-clojure.demo :refer [fibinocci-series
                                              is-string-blank?]])
  (:gen-class))


;; Defining recrod in clojure

(defrecord Person [first-name last-name])
(def foo (->Person "Saurav" "Sp"))
(:first-name foo)


(defn -main
  "main"
  [& args]
  (println "Created fibinocci series number using function programming style")
  (println (fibinocci-series 10))
  (println "Checks if this string is blank \"    \" : " (is-string-blank? "   "))
  (println "Check if this string is blanck \"this\" : " (is-string-blank? "this")))
