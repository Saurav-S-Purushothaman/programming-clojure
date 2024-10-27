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

;; Registry - central registry for global reusable specs.
;; Associate a namespace keyword with a spec
;; Why we need namespace, because we can use reusable non conflicting
;; specs across libraries

(s/def :order/date inst?)
(s/def :deck/suit #{:club :diamond :heart :spade})

;; Now we can use the regirested spec instead of the predicate

(s/valid? :order/date (Date.))
(s/conform :deck/suit :club)

;; Composing predicate
(s/def :num/big-even (s/and int? even? #(> % 1000)))
(s/valid? :num/big-even :foo)
(s/valid? :num/big-even 10)
(s/valid? :num/big-even 1000000)
(s/conform :num/big-even 1000000)

(s/def :domain/name-or-id (s/or :name string?
                                :id int?))
;; Or expects keywords and map like thing

(s/valid? :domain/name-or-id "abc")
(s/conform :domain/name-or-id "abc")

;; If we want nil as a valid value
(s/valid? string? nil)
(s/valid? (s/nilable string?) nil)

;; Explain out ot why a value does not conform to a spec.

(s/explain :deck/suit 42)
(s/explain-str :deck/suit 42)

;; Entity map
;; Entity maps in spec are defined with keys
(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def ::email-type (s/and string? #(re-matches email-regex %)))
(s/def ::email ::email-type)
(s/def ::first-name string?)
(s/def ::last-name string?)

(s/def ::person (s/keys :req-un [::first-name ::last-name ::email]
                            :opt-un [::phone]))
(s/valid? ::person
          {:first-name "Bugs"
           :last-name "Bunny"
           :email "bugs@example.com"})

(s/explain ::person {:first-name "Bugs"
                     :last-name "Bunny"
                     :email "bugs@example.com"})

;; Spec can also be used to for records
;; Especially the unqualified keys

(defrecord Person [first-name last-name email phone])

(s/valid? ::person (->Person "Bugs" nil nil nil))
(s/explain ::person (->Person "Bugs" nil nil nil))

;; Declaring entity maps in parts
(s/def :animal/kind string?)
(s/def :animal/says string?)
(s/def :animal/common (s/keys :req [:animal/kind :animal/says]))
(s/def :dog/tail? boolean?)
(s/def :dog/breed string?)
(s/def :animal/dog (s/merge :animal/common
                            (s/keys :req [:dog/tail? :dog/breed])))
(s/valid? :animal/dog
          {:animal/kind "dog"
           :animal/says "woof"
           :dog/tail? true
           :dog/breed "retriever"})
