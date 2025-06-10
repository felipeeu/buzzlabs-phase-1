(ns buzzlabs-phase-1.events
  (:require
   [re-frame.core :as re-frame]
   [buzzlabs-phase-1.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))


(defn set-count
  "Set the counter value based on function (inc or dec)"
  [db func]
  (update db
         :count func))


(re-frame/reg-event-fx
 ::set-counter
 (fn [{:keys [db]} [_ func]]
   {:db (-> db
            (set-count func)
            )}))