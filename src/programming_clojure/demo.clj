(ns programming-clojure.demo
  (:gen-class))

;; Some useful functions in clojure


(defn fibinocci-series
  "Generates a fibinocci series starting from 0 till n"
  [n]
  (->> (iterate (fn [[a b]] [b (+ a b)]) [0 1])
       (map first)
       (take n)))


(defn is-string-blank?
  [str]
  (every? #(Character/isWhitespace %) str))


(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})


;; select
(clojure.set/select #(= 1 (count %)) languages)

;; Relational algebra using set theory

;; A composition relational table implemented using sets.
(def compositions
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})


(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})


(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})


;; To change column name use the rename function.
(clojure.set/rename compositions {:name :title})

;; select * from compositions where name = "Requiem";
(clojure.set/select #(= (:name %) "Requiem") compositions)

;; select name from composition;
(clojure.set/project compositions [:name])

;; cross produce or corss join
(for [m compositions c composers]
  (concat m c))

;; join
(clojure.set/join compositions composers)

;; join (if key name in relation don't match, the we can pass a keymap)
(clojure.set/join composers nations {:country :nation})

;; Combine relational primitives.
(clojure.set/project
 (clojure.set/join
  (clojure.set/select #(= (:name %) "Requiem") compositions) composers)
 [:country])
