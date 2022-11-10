
(def not-nil? (complement nil?))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def bill [{:name "Green Tea Ice Cream" :price 2.5 :quantity 2} {:price 1.0 :name "Sticky Rice" :quantity 1}])

(def items [{:price 2.1 :name "Mango" :quantity 1} {:quantity 1 :price 1.0 :name "Sticky Rice"}])


;
;
;;;; Returns the vector index for the item matching item found in bill
;(defn find-item
;  ([bill item] (find-item bill item 0))
;  ([bill item index]
;   (if (or (empty? bill) (items-match (first bill) item))
;     index
;     (find-item (rest bill) item (inc index)))))
;
;
;
;
;(defn add-to-bill
;  [bill items]
;  (if (empty? items)
;    bill
;    (let [bill-item-index (find-item bill (first items))]
;      (if (nil? (nth bill bill-item-index nil))
;        (add-to-bill (conj bill (first items)) (rest items))
;        (add-to-bill (update-in bill [bill-item-index :quantity] + (:quantity (first items))) (rest items))))))
;
;
;
;(println (add-to-bill bill items))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn items-match
  [item1 item2]
  (and (= (:name item1) (:name item2)) (= (:price item1) (:price item2))))



(defn add-to-bill
  [bill items]
  (if (empty? items)
      bill
      (if (= 0 (apply + (map #(if (items-match % (first items)) 1 0) bill)))
        (add-to-bill (conj bill (first items)) (rest items))
        (add-to-bill (mapv #(if (items-match % (first items)) (assoc % :quantity (+ (% :quantity) ((first items) :quantity))) %) bill) (rest items)))))




(println bill)
(println (add-to-bill bill items))


