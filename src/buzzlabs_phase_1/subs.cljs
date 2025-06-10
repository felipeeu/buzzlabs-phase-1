(ns buzzlabs-phase-1.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::count
 (fn [db]
   (:count db)))