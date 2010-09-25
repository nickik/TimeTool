(ns TimeTool.core
    (:gen-class)
    (:use clojure.contrib.command-line)
    (:use clojure.pprint))

(defn -main []
  (with-open [rdr (clojure.java.io/reader "/home/user/test.txt")]
     (pprint (doall (map matcher (line-seq rdr))))))

(defn formater [[date h m]]
  (let [time (str (+ (Float. h) (/ (Float. m) 60)))]
    (apply str date ": " time)))

(defn matcher [x]
  (let [timestring (apply str (take 5 (drop 69 x)))
        Data (take 11 x)]
    (formater (vec (map (partial apply str)
                        [Data (take 2 timestring) (drop 3 timestring)])))))

