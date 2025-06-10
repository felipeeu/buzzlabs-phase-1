(ns buzzlabs-phase-1.views
  (:require
   [re-frame.core :as re-frame]
   [buzzlabs-phase-1.subs :as subs]
   [buzzlabs-phase-1.events :as events]
   ))

(def styles {:button {:background "gray"  
                      :border "none" 
                      :font-size "24px" 
                      :border-radius "20%"}
             :counter {:font-weight "bold" 
                       :font-size "30px" 
                       :text-align "center" 
                       }
             :container {:display "flex" 
                         :flex-direction "column" 
                         :justify-content "space-between" 
                         :width "40px"}})


(defn counter 
  []
  (let [count (re-frame/subscribe [::subs/count])]
    [:div {:style (:container styles)}
     [:button  {:on-click (fn [] (re-frame/dispatch [::events/set-counter inc])) 
                :style (:button styles)} "+"]
     [:p {:style (:counter styles)} @count ]
     [:button  {:on-click (fn [] (re-frame/dispatch [::events/set-counter dec]))
                :disabled (= @count 0) 
                :style (:button styles)} "-"]
     ]))


(defn main-panel [] 
  [:div 
   (counter)])
