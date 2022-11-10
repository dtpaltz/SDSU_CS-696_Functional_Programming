(defn sub-blocks
  "Returns a collection of sequential sub-string blocks in s of size n"
  [s n]
  (map #(subs s % (+ % n)) (range (- (count s) (dec n)))))

(defn equal?
  "Returns true if s equals all the elements in more, false otherwise"
  [s & more]
  (every? #(= (compare s %) 0) more))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #1

(defn divisors
  "Returns a sequence of the divisors of x"
  [x]
  (filter #(= (mod x %) 0) (range 1 (inc x))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #2

(defn abundance
  "Returns the sum of the proper divisors of x minus 2x"
  [x]
  (- (apply + (divisors x)) (* 2 x)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #3

(defn find-abundant
  "Finds all the abundant numbers in the range [1 x)"
  [x]
  (filter #(> (abundance %) 0) (range 1 x)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #4

(defn pattern-count
  "Returns a count of the occurrences of ptrn in s"
  [s ptrn]
  (apply + (map #(if (equal? % ptrn) 1 0) (sub-blocks s (count ptrn)))))








;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #5


; do we need to account for whitespace????


(defn most-frequent-word
  [txt n]
  (let [word-freq-map (frequencies (str-blocks txt n)) max-occur (apply max (vals word-freq-map))]
    (sort (keys (filter (fn [[key val]] (= val max-occur)) word-freq-map)))))


; returns ("CTAG" "GCTA")
(most-frequent-word "TCGAAGCTAGACGCTAGTAGCTAGTGTGCA" 4)














;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #6

(defn find-clumps
  [txt k l t]
  (distinct (for [word (str-blocks txt k) block (str-blocks txt l) :when (= t (pattern-count block word))] word)))


(def text "CGGACTCGACAGATGTGAAGAAATGTGAAGACTGAGTGAAGAGAAGAGGAAACACGACACGACATTGCGACATAATGTACGAATGTAATGTGCCTATGGC")


; returns ("CGACA" "GAAGA" "AATGT")
(find-clumps text 5 75 4)


































