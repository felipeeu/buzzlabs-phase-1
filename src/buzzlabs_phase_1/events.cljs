(ns buzzlabs-phase-1.events
  (:require
   [re-frame.core :as re-frame]
   [buzzlabs-phase-1.db :as db]
   [ajax.core :as ajax] 
   [day8.re-frame.http-fx]))

(def api-url "http://localhost:3000/")


(re-frame/reg-event-fx
 ::initialize-db
 (fn [{:keys [_]} [_ _]]
   {:db db/default-db
    :fx [[:dispatch [::http-get]]]}))



(defn set-count
  "Set the counter value based on function (inc , dec or reset)"
  [db func]
  (update db
          :count func))


(re-frame/reg-event-fx
 ::set-counter
 (fn [{:keys [db]} [_ func count]]
   {:db (-> db
            (set-count func))
    :fx [[:dispatch [::http-get (str (func count)) ]]]
    }))


(re-frame/reg-event-fx
 ::http-get
 (fn [{:keys [db]} [_ params]]
   (println "Fetching data from API with params: " (str api-url  params))
   {:db db
    :http-xhrio {:method          :get
                 :uri             (str api-url params)
                 :format          (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      [::success-get-result]
                 :on-failure      [::failure-get-result]}}))

(re-frame/reg-event-db
 ::success-get-result
 (fn [db [_ result]]
   (-> db
       (assoc  :count
                  result))))

(re-frame/reg-event-db
 ::failure-get-result
 (fn [db [_ result]]
   (assoc-in db [:failure] result)))