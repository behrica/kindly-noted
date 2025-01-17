;; # Kind compatibility matrix

^:kindly/hide-code
(ns kind-compatibility
  (:require [scicloj.kindly.v4.api :as kindly]
            [scicloj.kindly.v4.kind :as kind]
            [tablecloth.api :as tc]
            [clojure.string :as str]
            
            ))
^:kindly/hide-code
(def kind-status
  [
   {:clojupyter :u, :description :kind/edn, :clay :c, :clerk :u, :kind :kind/edn}
   {:clojupyter :r, :description :kind/code, :clay :c, :clerk :u, :kind :kind/code}
   {:clojupyter :u, :description :kind/vega, :clay :c, :clerk :u, :kind :kind/vega}
   {:clojupyter :u, :description :kind/smile-model, :clay :c, :clerk :u, :kind :kind/smile-model}
   {:clojupyter :c, :description :kind/image, :clay :c, :clerk :u, :kind :kind/image}
   {:clojupyter :c, :description :kind/plotly, :clay :c, :clerk :u, :kind :kind/plotly}
   {:clojupyter :c, :description :kind/echarts, :clay :c, :clerk :u, :kind :kind/echarts}
   {:clojupyter :u, :description :kind/map, :clay :c, :clerk :u, :kind :kind/map}
   {:clojupyter :n, :description :kind/portal, :clay :c, :clerk :u, :kind :kind/portal}
   {:clojupyter :u, :description :kind/test, :clay :c, :clerk :u, :kind :kind/test}
   {:clojupyter :c, :description :kind/dataset, :clay :c, :clerk :u, :kind :kind/dataset}
   {:clojupyter :c, :description :kind/vega-lite, :clay :c, :clerk :u, :kind :kind/vega-lite}
   {:clojupyter :c, :description :kind/html, :clay :c, :clerk :u, :kind :kind/html}
   {:clojupyter :c, :description :kind/cytoscape, :clay :c, :clerk :u, :kind :kind/cytoscape}
   {:clojupyter :u, :description :kind/set, :clay :c, :clerk :u, :kind :kind/set}
   {:clojupyter :n, :description :kind/reagent, :clay :c, :clerk :u, :kind :kind/reagent}
   {:clojupyter :u, :description :kind/var, :clay :c, :clerk :u, :kind :kind/var}
   {:clojupyter :c, :description :kind/hidden, :clay :c, :clerk :u, :kind :kind/hidden}
   {:clojupyter :c, :description :kind/hiccup, :clay :c, :clerk :u, :kind :kind/hiccup}
   {:clojupyter :c, :description :kind/md, :clay :c, :clerk :u, :kind :kind/md}
   {:clojupyter :r, :description :kind/tex, :clay :c, :clerk :u, :kind :kind/tex}
   {:clojupyter :u, :description :kind/seq, :clay :c, :clerk :u, :kind :kind/seq}
   {:clojupyter :u, :description :kind/htmlwidgets-plotly, :clay :c, :clerk :u, :kind :kind/htmlwidgets-plotly}
   {:clojupyter :n, :description :kind/video, :clay :c, :clerk :u, :kind :kind/video}
   {:clojupyter :n, :description :kind/observable, :clay :c, :clerk :u, :kind :kind/observable}
   {:clojupyter :u, :description :kind/emmy-viewers, :clay :c, :clerk :u, :kind :kind/emmy-viewers}
   {:clojupyter :r, :description :kind/pprint, :clay :c, :clerk :u, :kind :kind/pprint}
   {:clojupyter :c, :description :kind/highcharts, :clay :c, :clerk :u, :kind :kind/highcharts}
   {:clojupyter :r, :description :kind/table, :clay :c, :clerk :u, :kind :kind/table}
   {:clojupyter :e, :description :kind/fn, :clay :c, :clerk :u, :kind :kind/fn}
   {:clojupyter :u, :description :kind/vector, :clay :c, :clerk :u, :kind :kind/vector}
   {:clojupyter :u, :description :kind/htmlwidgets-ggplotly, :clay :c, :clerk :u, :kind :kind/htmlwidgets-ggplotly}
   {:clojupyter :n, :description :kind/fragment, :clay :c, :clerk :u, :kind :kind/fragment}
   {:clojupyter :u, :description :kind/scittle, :clay :c, :clerk :u, :kind :kind/scittle}
   {:clojupyter :u, :description :kind/test-last, :clay :c, :clerk :u, :kind :kind/test-last}


   ])

^:kindly/hide-code
(defn status->upper [m k]
  
  (update m k (fn [v] 
                (case v 
                  :c  (kind/hiccup [:div {:style "background-color:green"} (-> v name str/upper-case)])
                  :u (kind/hiccup [:div {:style "background-color:grey"} (-> v name str/upper-case)])
                  :n (kind/hiccup [:div {:style "background-color:red"} (-> v name str/upper-case)])
                  :e (kind/hiccup [:div {:style "background-color:red"} (-> v name str/upper-case)])
                  :r (kind/hiccup [:div {:style "background-color:yellow"} (-> v name str/upper-case)])
                  ;(-> v name str/upper-case)

                  )
                
                )
    
          ))

^:kindly/hide-code
(def t
  (-> 
   (map
    #(-> % 
         (status->upper :clay)
         (status->upper :clerk)
         (status->upper :clojupyter)
         (update :kind (fn [v] (str v)))
         
         
         )
    kind-status
    )
 (tc/dataset)
 (tc/reorder-columns [:kind :description :clay :clojupyter :clerk])
 (tc/order-by [:kind])  
 ;(tech.v3.dataset.print/print-range  :all)
  (tc/rows) 
   ))

^:kindly/hide-code
{:c :compatible
 :u :unknown
 :n :not-implemented
 :e :exception
 :r :rendering-differs-to-clay} 


^:kindly/hide-code
(kind/table 
 {:row-vectors t
  :column-names [:kind :description :clay :clojupyter :clerk]
  }
 
 )