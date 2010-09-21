(ns TimeTool.core
  [:use [clojure.pprint]])

(defn formater [[date h m]]
  (let [time (str (+ (Float. h) (/ (Float. m) 60)))]
    (apply str date ": " time)))

(defn matcher [x]
  (let [timestring (apply str (take 5 (drop 69 x)))
        Data (take 11 x)]
    (formater (vec (map (partial apply str)
                        [Data (take 2 timestring) (drop 3 timestring)])))))

(defn main []
  (with-open [rdr (clojure.java.io/reader "/home/nick/zbinden.txt")]
    (doall (map matcher (drop 5 (line-seq rdr))))))
