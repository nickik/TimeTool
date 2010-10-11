(ns TimeTool.core
    (:gen-class)
    (:use clojure.contrib.command-line)
    (:use clojure.pprint))

(def sepcounter (atom 0))

(defn formater [[date h m]]
  (let [time (str (+ (Float. h) (/ (Float. m) 60)))]
    (apply str date ": " time)))

(defn cout? [c] (if (= @sepcounter 4) nil (if (= c \|) (swap! sepcounter inc) true)))	
	
(defn matcher [x]
  (let [timestring (apply str (take 5 (drop 1 (drop-while cout? x))))
        Data (take 11 x)]
    (do (reset! sepcounter 0) (formater (vec (map (partial apply str)
                        [Data (take 2 timestring) (drop 3 timestring)]))))))
	
(defn -main []
  (with-open [rdr (clojure.java.io/reader "D:/zbinden.txt")]
     (pprint (doall (map matcher (line-seq rdr))))))

