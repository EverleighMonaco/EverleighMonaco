(ns em.core
  (:require
   [kee-frame.core :as kf]
   [re-frame.core :as rf]
   [ajax.core :as http :refer [raw-response-format json-response-format]]
   [stylefy.core :as stylefy]
   [stylefy.reagent :as stylefy-reagent]
   [em.ajax :as ajax]
   [em.routing :as routing]
   [em.view :as view]))


(rf/reg-sub
  :listings
  (fn [db _]
    (:listings db)))

(rf/reg-sub
 :loading
 (fn [db _]
   (:loading db)))


;; TEMPORARY - figure out if we keep kee-frame or pull it.
;; Also we need events.cljs

(kf/reg-chain
 ::load-home-page
 (fn [{:keys [db]} _]
   {:http-xhrio {:method          :get
                 :uri             "http://localhost:9600/api/v1"

                 ;; :response-format (raw-response-format) ; {:keywords? true :raw false})
                 :response-format (json-response-format {:keywords? true :raw false})
                 :db              (-> db (assoc-in [:loading] true))
                 :on-success      [:import-listings]
                 :on-failure      [:api-error]}}))


;; TEMPORARY move to events.cljs
(rf/reg-event-db
 :import-listings
 (fn [db [_ result]]
   (-> db
       (assoc :listings (get result :listings))
       ;; (assoc :listings (get (js->clj (.parse js/JSON result) :keywordize-keys true) :listings))
       (assoc-in [:loading] false))))

;; TEMPORARY move to events.cljs
(rf/reg-event-db
 :api-error
 (fn [db [_]]
     (-> db (assoc-in [:loading] false))
     (js/alert "error connecting to api!")))


(kf/reg-controller
  ::home-controller
  {:params (constantly true)
   :start  [::load-home-page]})

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components
  []
  (rf/clear-subscription-cache!)
  (kf/start! {:routes         routing/routes
              :hash-routing?  true
              #_#_
              :log            {:level        :debug
                               :ns-blacklist ["kee-frame.event-logger"]}
              :initial-db     {}
              :root-component [view/root-component]}))

(defn init! []
  (ajax/load-interceptors!)
  (stylefy/init {:dom (stylefy-reagent/init)})
  (.log js/console "EVERLEIGH MONACO, made by Addison with ClojureScript!")
  (mount-components))
