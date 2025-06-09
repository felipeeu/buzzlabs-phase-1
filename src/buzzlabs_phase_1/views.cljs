(ns buzzlabs-phase-1.views
  (:require
   [re-frame.core :as re-frame]
   [buzzlabs-phase-1.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     ]))
