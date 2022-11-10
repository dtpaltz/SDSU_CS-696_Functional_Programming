
(defn differentiate
  [poly]
  (let [ans (mapv (fn [term] [(* (first term) (second term)) (dec (second term))]) poly)]
    (if (= (second (last ans)) -1)
      (pop ans)
      ans)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn differentiate2
  [poly]
  (let [ans (mapv (fn [term] [(* (first term) (second term)) (dec (second term))]) poly)]
    (filterv #(not= (second %) -1) ans)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn differentiate3
  [poly]
  (filterv #(not= (second %) -1) (mapv (fn [term] [(* (first term) (second term)) (dec (second term))]) poly)))




; returns [[2 1] [3 0]]
(println (differentiate3 [[1 2] [3 1] [-1 0]]))



; returns [[12 3] [10 1] [6 0]]
(println (differentiate3 [[3 4] [5 2] [6 1]]))