;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #1

(defn divisors
  [x]
  (filter #(= (mod x %) 0) (range 1 (inc x))))



(divisors 12)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #2

(defn abundance
  [x]
  (- (apply + (divisors x)) (* 2 x)))


; this assumes: " returns the sum of the proper divisors of the number minus <twice> the number itself
; make sure this is correct


(abundance 12)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #3


(defn find-abundant
  "Finds all the abundant numbers in the range [1 x)"
  [x]
  (filter #(> (abundance %) 0) (range 1 x)))


(find-abundant 300)
