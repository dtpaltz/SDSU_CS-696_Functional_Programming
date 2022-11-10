




(defn read-turtle-command
  [cmd]
  (let [cols (re-seq #"\w+" (clojure.string/lower-case cmd))]
    (case (first cols)
      "up" {:move-type 0 :text cmd}
      "down" {:move-type 1 :text cmd}
      "move" {:move-type 2 :distance (read-string (nth cols 1)) :text cmd}
      "turn" {:move-type 3 :offset (read-string (nth cols 1)) :text cmd})))


(defn read-turtle-program
  [path]
  (let [lines (clojure.string/split-lines (slurp path))]
    (for [cmd lines] (read-turtle-command cmd))))








(read-turtle-program "J:\\Clojure\\Assignment_3\\TurtleProgram.txt")



(with-open [rdr (clojure.java.io/reader "TurtleProgram.txt")]
    (reduce conj [] (line-seq rdr)))
