(ns programming-clojure.core
  (:require [programming-clojure.demo :refer [fibinocci-series
                                              is-string-blank?]])
  (:gen-class))


;; Defining recrod in clojure

(defrecord Person [first-name last-name])
(def foo (->Person "Saurav" "Sp"))
(:first-name foo)


;; ref function creates transactionally protected reference.
(def accounts (ref #{}))
(defrecord Account [id balance])

;; Updating the state
(dosync
 (alter accounts conj (->Account "CLJ" 1000.00)))
;; dosync causes the update to accounts to execute inside a
;; transaction which makes it thread safe

;; "hello".getClass().getProtectionDomain()
(.. "hello" getClass getProtectionDomain)
(doto "Hello"
  (.getClass)
  (.getProtectionDomain))


(defn -main
  "main"
  [& args]
  (println "Created fibinocci series number using function programming style")
  (println (fibinocci-series 10))
  (println "Checks if this string is blank \"    \" : " (is-string-blank? "   "))
  (println "Check if this string is blanck \"this\" : " (is-string-blank? "this")))
