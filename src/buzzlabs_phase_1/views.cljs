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
                       :text-align "center"}
             :container {:display "flex"
                         :flex-direction "column"
                         :justify-content "space-between"
                         :width "40px"
                         :padding-bottom "20px"}
             :reset {:background "red"
                     :border "none"
                     :padding "10px"
                     :color "white"
                     :font-weight "bold" 
                     :border-radius "10%"}})

(defn counter 
  "Counter with increment and decrement"
  []
  (let [count (re-frame/subscribe [::subs/count])]
    [:div {:style (:container styles)}
     [:button  {:on-click (fn [] (re-frame/dispatch [::events/set-counter inc @count])) 
                :style (:button styles)} "+"]
     [:p {:style (:counter styles)} @count ]
     [:button  {:on-click (fn [] (re-frame/dispatch [::events/set-counter dec @count]))
                :disabled (= @count 0) 
                :style (:button styles)} "-"]
     ]))

(defn reset
  "Button to reset the counter value"
  []
  [:div 
   [:button  {:on-click (fn [] (re-frame/dispatch [::events/set-counter (fn[] 0) 0]))
             :style (:reset styles)} "Reset" ]])


(defn main-panel [] 
  [:div 
   (counter)
   (reset)])
