(defn sub-blocks
  "Returns a collection of sequential sub-string blocks in s of size n"
  [s n]
  (map #(subs s % (+ % n)) (range (- (count s) (dec n)))))



(defn equal?
  [s & more]
  (every? #(= (compare s %) 0) more))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #4



(defn pattern-count
  "Returns a count of the occurrences of ptrn in s"
  [s ptrn]
  (apply + (map #(if (equal? % ptrn) 1 0) (sub-blocks s (count ptrn)))))





(defn most-frequent-word
  [s n]
  (let [word-freq-map (frequencies (sub-blocks s n))
        max-occur (apply max (vals word-freq-map))]
    (sort (keys (filter (fn [[_ val]] (= val max-occur)) word-freq-map)))))


; returns ("CTAG" "GCTA")
(most-frequent-word "TCGAAGCTAGACGCTAGTAGCTAGTGTGCA" 4)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn str-blocks
  "Returns a collection of sequential sub-string word blocks of size N found in txt"
  [txt n]
  (map #(subs txt % (+ % n)) (range (inc (- (count txt) n)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; #4

(defn pattern-count
  "Returns an occurence count of string ptrn in a string txt"
  [txt ptrn]
  (let [ct (count txt) cp (count ptrn)]
   (if (< ct cp)
     0
     (apply + (map #(if (= 0 (compare % ptrn)) 1 0) (str-blocks txt cp))))))


;-> 3
(pattern-count "abababa" "aba")


;-> 4
(pattern-count "aaaaa" "aa")


;-> 0
(pattern-count "Abcde" "abc")


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

