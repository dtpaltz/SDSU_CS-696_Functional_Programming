
;(def average (fn average [numbers] (/ (apply + numbers) (count numbers))))

;(println (average [1 2 5 4]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;
;(def example (make-poly [[1 2] [3 1] [-1 0]]))
;(example 2) ;will return 9.0
;(map example [0 1 2 3 4 5]) ;will return (-1.0 3.0 9.0 17.0 27.0 39.0)
;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(defn adder
;  [n]
;  #(+ n %))
;
;(def add-5 (adder 5))
;
;(print (map add-5 [1 2 3 4 5]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;(def expoly [[1 2] [3 1] [-1 0]])
;
;
;(defn make-poly [poly]
;
;
;polynomial
;
;
;(println expoly)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(defn expt
;  "Returns a specified number raised to the specified power"
;  [x n]
;  (reduce * (repeat n x)))
;
;
;(defn make-poly
;  [poly]
;  #(reduce + (map (fn [term] (* (first term) (expt % (second term)))) poly)))
;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(Math/pow 2 5.4)

(defn make-poly
  [poly]
  #(reduce + (map (fn [term] (* (first term) (Math/pow % (second term)))) poly)))



(def example (make-poly [[1 2] [3 1] [-1 0]]))



; will return 9.0
(println (example 2))

(println ((make-poly [[1 2] [3 1] [-1 0]]) 2))

; will return (-1.0 3.0 9.0 17.0 27.0 39.0)
(println (map example [0 1 2 3 4 5]))








