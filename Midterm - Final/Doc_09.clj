(def bmath (juxt + - * /))

(bmath 4 9)


(def msplit (juxt take drop))

(msplit 4 (range 9))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


((juxt :last :first) {:last "Adams" :first "Zak"})




(sort-by (juxt :last :first) [{:last "Adams" :first "Zak"}
                              {:last "Zen" :first "Alan"}
                              {:last "Smith" :first "Alan"}])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def fourth (comp first rest rest rest))

(fourth [:a :b :c :d :e])



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn fnth
  [n]
  (apply comp
         (cons first
               (take (dec n) (repeat rest)))))


((fnth 1) [:a :b :c :d :e])

((fnth 3) [:a :b :c :d :e])

