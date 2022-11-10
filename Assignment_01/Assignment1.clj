; Dustin Paltz


; Problem #1

(defn item-total
  [item]
  (* (:price item) (:quantity item)))

(defn bill-total
  [bill]
  (apply + (map item-total bill)))


; Problem #2

(defn items-match
  [item1 item2]
  (and (= (:name item1) (:name item2)) (= (:price item1) (:price item2))))


; does using cond clean this up at all?
(defn add-to-bill
  [bill items]
  (if (empty? items)
    bill
    (if (= 0 (apply + (map #(if (items-match % (first items)) 1 0) bill)))
      (add-to-bill (conj bill (first items)) (rest items))
      (add-to-bill (mapv #(if (items-match % (first items)) (assoc % :quantity (+ (% :quantity) ((first items) :quantity))) %) bill) (rest items)))))



; Problem #3

(defn make-poly
  [poly]
  #(reduce + (map (fn [term] (* (first term) (Math/pow % (second term)))) poly)))


; Problem #4

(defn differentiate
  [poly]
  (filterv #(not= (second %) -1) (mapv (fn [term] [(* (first term) (second term)) (dec (second term))]) poly)))


