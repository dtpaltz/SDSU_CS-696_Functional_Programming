(def account1 {:id 1 :pin 1234 :transactions [0]})

(defn deposit
  [account amount]
  (assoc account :transactions (conj (account :transactions) amount)))

(defn withdrawal
  [account amount]
  (deposit account (- amount)))

(defn balance
  [account]
  (reduce + (:transactions account)))





(def account1 (deposit account1 125))

(withdrawal account1 35)

(balance (withdrawal (deposit account1 125) 35))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(def account2 {:id 2 :pin 1234 :balance 0})

(defn deposit2
  [account amount]
  (assoc account :balance (+ (account :balance) amount)))

(defn withdrawal2
  [account amount]
  (deposit2 account (- amount)))


(def account2 (deposit2 account2 100))

(println account2)

(def account2 (withdrawal2 account2 50))

(println account2)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



