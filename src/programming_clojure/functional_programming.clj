(ns programming-clojure.functional-programming
  (:gen-class))


;; Variosu implementation of Fibonacci-series

;; tail recursion
(defn tail-fibo [n]
  (letfn [(fib [current next n]
            (if (zero? n)
              current
              (fib next (+ current next) (dec n))))]
    (fib 0N 1N n)))

;; tail recursion that doesn't consume stack
(defn tail-fibo [n]
  (letfn [(fib [current next n]
            (if (zero? n)
              current
              (fib next (+ current next) (dec n))))]
    (fib 0N 1N n)))

;; Lazy fib
(defn lazy-fib
  ([]
   (lazy-fib 1 1))
  ([a b]
   (lazy-seq (cons a (fib b (+ a b))))))


;; Sequence library fib
(defn fib [n]
  (take n (map first (iterate (fn [[a b]] [0 1])))))
