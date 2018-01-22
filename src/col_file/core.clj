(ns col-file.core
	(:require [camel-snake-kebab.core :as csk]))

	
(defn serpent-talk [input]
	(str "Serpent!  You said: "
	  (csk/->snake_case input)))

(def geraete_vec [[:art :ph , :typ :mx , :ort "fil" :nr 0 :serv-id 123] [:art :ax , :typ :rt , :ort "fil" :nr 1 :serv-id 123] [:art :bf , :typ :fw , :ort "tav" :nr 2 :serv-id 234]])

(def kw_vec [:alk :fmm :kwm :kll :ahs :kvr :kwi :kwloe :kwe])

(def kw-ger_vec [[5 0][5 2][6 0][6 1]])
	  
	  
	  
(defn list-kw-ger
	"listet die geraete eines KWs"
	[kw-keyword]
	;(println kw-keyword)
	(doall (map println 
	(for [x kw-ger_vec
		:let [display-str
			(if (= (nth x 0) (.indexOf kw_vec kw-keyword) )  ;wenn die nr im sub-vector gleich der kw nr ist
			(str (nth geraete_vec (nth x 1)))
			;(str "-no- ")
		)]]
	display-str
	)
	))
)

	  
	  
	  
(defn arten
  "returns the arten of the device x."
  [x]
  (cond
	(= x :ph) [:referenz 1 , :txt "ph" , :text "SDH/PDH"]
	(= x :ax) "aXbone"
	(= x :bf) "Betriebsfuehrungssysteme"
	(= x :zaehler) "Mess und Zaehlersysteme"
	:else "not defined")
)

	  
(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!")
)
	  
	  
(defn ms-punkte
  "returns the maintenance punkte of the device x."
  [x]
  (cond
	(= x :mx-gross) "18 pkt"
	(= x :mx-mittel) "15 pkt"
	(= x :mx-standard) "12 pkt"
	(= x :rt-gross) "15 pkt"
	(= x :fw-standard) "12 pkt"
	:else "not defined")
)
	  
	  
(defn auflisten  ;ist nicht fertig, ist nur ein Versuch von map und loop
  "I list ourfolio collections see P51."
  [input]
  (println input)
  (doall (map #(println %) (range 1 4)))
  (doall (map println ["aaaa" "bbbb" "cccc"]))
  (doall (map println input))
  (println "now the loooooop:")
  (loop [in input
         out []]
    (if (empty? in)
	 out
	 (recur (rest in)
	        (conj out
				(println "KW name is: " (first in))))
	)
  )
)

(defn selektor1
  "je nach args wird eine Fkt selektiert."
  [input]
  (let [x (first input)]
	(cond
		(= x "kw_vec") (auflisten kw_vec)
		(= x "kw") (println "args kw")
		(= x "geraeteliste") (auflisten :fmm :geräte)
		(= x "kundenliste") (auflisten :kunden :alle)
		(= x "kw-nr") (println (.indexOf kw_vec (keyword (first (rest input))) ))
		(= x "list-kw-ger") (list-kw-ger (keyword (first (rest input))) )
		(= x "help") (doall (map println '("available Commands:" "kw_vec  listet die KWs" , "list-kw-ger <kw>   listet die Geräte eines KWs" , "kw-nr <kw>   Zeigt den IndexOf KW", "kw    macht nichts schlaues")))
		:else (println "----> not defined"))
	)
)
	  

  
(defn -main [& args]
	 (selektor1 args)	
)
