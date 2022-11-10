(get {:a 1 :b 2} :b)
(get {:a 1 :b 2} :b 10)
({:a 1 :b 2} :b)
({:a 1 :b 2} :b 10)
(:b {:a 1 :b 2})
(:b {:a 1 :b 2} 10)
(= 2 2.0)
(== 2 2.0)
(first '(1 2 3))
(first [1 2 3])
(pop '(1 2 3))
(pop [1 2 3])
(cons :a '(1 2 3))
(cons :a [1 2 3])
(conj '(1 2 3) :a)
(conj [1 2 3] :a)
(number? :a)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn sdsu-last
  [& more]
  (if (<= (count more) 1)
    (first more)
    (recur (rest more))))


(sdsu-last nil)

(sdsu-last 5)

(sdsu-last 1 2 3 4 5)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn isSumOrProduct
  [coll x]
  (if (empty? coll)
    (or (= x 0) (= x 1))
    (or (= x (apply + coll)) (= x (apply * coll)))))



(isSumOrProduct [2 2 3] 7)

(isSumOrProduct [2 3] 6)

(isSumOrProduct [2 3] 4)

(isSumOrProduct [] 0)

(isSumOrProduct [] 1)

(isSumOrProduct [] 6)

(isSumOrProduct [] 4)

(isSumOrProduct [2 3] :a)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn max-tree-height
  [tree]
  (if (empty? tree)
    0
    (inc (max (max-tree-height (tree :left)) (max-tree-height (tree :right))))))



(def trtest {:key 5 :left: nil :right {:key 10 :left {:key 7 :left nil :right nil} :right nil}})




(max-tree-height trtest)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;






(defn sdsu-partial
  [f x]
  #(f x %))



(def g (sdsu-partial + 5))

(g 3)





(pop ["a" "b" "c"])

(conj [1 2 3]  4)




(empty? nil)



(max 0 0)








