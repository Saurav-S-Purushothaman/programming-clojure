(ns programming-clojure.specs
  (:require [clojure.spec.alpha :as s]))

;; Predicate that only takes single value can be used as a spec function

;; General
(comment
  (s/conform even? 1000)
  ;; This will return the conformed, if you don't want to do that, you
  ;; can use s/valid? - this will return truthy and not the data itself.
  (s/valid? even? 1000)
  (s/valid? nil? nil) ;; true
  (s/valid? string? "string") ;; true
  (import java.util.Date)
  (s/valid? inst? (Date.))
  (s/conform inst? (Date.))
  (def spec-fn (partial some)))
