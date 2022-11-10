(let [[a b c] (range 5)]
  (println "a b c are: " a b c))



(let [[a b c :as all] [1 2 3 4 5]]
  (println "a b c are:" a b c)
  (println "all is:" all))



(let [[a b c & more :as all] (range 5)]
  (println "a b c are:" a b c)
  (println "more is:" more))



(let [[a b c & more :as all] (range 5)]
  (println "a b c are:" a b c)
  (println "more is:" more)
  (println "all is:" all))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(let [{first 0, third 2, last 4} [1 2 3 4 5]]
  [first third last])




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def guys-name-map {:first-name "Guy" :middle-name "Lewis" :last-name "Steele"})


(let [{l-name :last-name, f-name :first-name} guys-name-map]
  (str f-name " "  l-name))


(let [{:keys [last-name first-name]} guys-name-map]
  (str first-name " " last-name))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(let [{l-name :last-name, f-name :first-name :as whole-name} guys-name-map]
  (println f-name " "  l-name)
  whole-name)


(let [{f-name :first-name, l-name :last-name, title :title, :or {title "Mr."}} guys-name-map]
  (str title " " f-name " "  l-name))
