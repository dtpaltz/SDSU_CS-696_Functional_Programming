(defn day-datum
  [lines day]
  (filter #(let [fword (first (re-seq #"\w+" %))] (and (not (empty? fword)) (= day (read-string fword)))) lines))




(defn day-num
  [datum]
  (if (empty? datum)
    nil
    (let [cols (re-seq #"\w+" datum)]
      (read-string (nth cols 0)))))


(defn day-max-min
  [datum]
  (if (empty? datum)
    [0 0]
    (let [cols (re-seq #"\w+" datum)]
      [(read-string (nth cols 1)) (read-string (nth cols 2))])))



(defn day-data
  [datum]
  (if (empty? datum)
    nil
    (let [cols (re-seq #"\w+" datum)
          dnum (read-string (nth cols 0))
          dmax (read-string (nth cols 1))
          dmin (read-string (nth cols 2))]
      (if (and (number? dnum) (number? dmax) (number? dmin))
        [dnum dmax dmin]
        nil))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defn day-spread
  "Given day datum containing valid data, returns the day temperature spread in the form [day-num day-temp-spread]"
  [datum]
  (if (empty? datum)
    nil
    (let [cols (re-seq #"\w+" datum)
          dnum (read-string (nth cols 0))
          dmax (read-string (nth cols 1))
          dmin (read-string (nth cols 2))]
      (if (and (number? dnum) (number? dmax) (number? dmin))
        [dnum (- dmax dmin)]
        nil))))



(defn days-compare
  "Compares two day spreads and returns the day with the max spread"
  [d1 d2]
  (if (> (second d1) (second d2)) d1 d2))



(defn maximum-spread
  [path]
  (let [lines (clojure.string/split-lines (slurp path))]
    (first (reduce days-compare (remove nil? (map #(day-spread %) lines))))))




(maximum-spread "F:\\Clojure\\Assignment_2\\weather.dat")



