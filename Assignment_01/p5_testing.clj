(defn make-poly
  [poly]
  #(reduce + (map (fn [term] (* (first term) (Math/pow % (second term)))) poly)))



(defn differentiate
  [poly]
  (filterv #(not= (second %) -1) (mapv (fn [term] [(* (first term) (second term)) (dec (second term))]) poly)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




(defn converge-eval-poly
  [g p]
  (- g (/ ((make-poly p) g) ((make-poly (differentiate p)) g))))





; t = tolerance
; p = polynomial
; g = guess for x0
(defn find-root
  [t p g]
  (let [xn (converge-eval-poly g p)]
    (if (<= (Math/abs (- xn g)) t)
      xn
      (recur t p xn))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




;(x+1)(x+1) so root = -1
(def poly1 [[1 2] [2 1] [1 0]])


; (x+1)(x-1)  so roots are 1, -1
(def poly2 [[1 2] [-1 0]])


; (2x+1)(3x-1) so roots are -1/2 and 1/3
(def poly3 [[6 2] [1 1] [-1 0]])







; returns -0.999832
(find-root 0.0001 poly1 10)


; returns 1.000005
(find-root 0.0001 poly2 10)


; returns -1.000005
(find-root 0.0001 poly2 -10)


; returns 0.3333333
(find-root 0.0001 poly3 10)







