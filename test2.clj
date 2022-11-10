(def guys-name-map {:first-name "Guy" :middle-name "Lewis"
                    :last-name "Steele"})


(let [{f-name :first-name, l-name :last-name, title :title,
               :or {title "Mr."}} guys-name-map]
  (str title " " f-name " "  l-name))
