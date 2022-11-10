(def bill [{:name "Green Tea Ice Cream" :price 2.5 :quantity 2} {:price 1.0 :name "Sticky Rice" :quantity 1}])


; v1
;(defn bill-total [bill]
;  (if (empty? bill)
;    0
;    (+ (item-cost (first bill)) (bill-total (rest bill)))))


(defn item-total
  [item]
  (* (:price item) (:quantity item)))

(defn bill-total
  [bill]
  (apply + (map item-total bill)))




(println (bill-total bill))
