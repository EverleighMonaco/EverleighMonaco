(ns em.core
  (:require
    [kee-frame.core :as kf]
    [re-frame.core :as rf]
    [ajax.core :as http]
    [stylefy.core :as stylefy]
    [stylefy.reagent :as stylefy-reagent]
    [em.ajax :as ajax]
    [em.routing :as routing]
    [em.view :as view]))

(rf/reg-sub
  :docs
  (fn [db _]
    (:docs db)))

(kf/reg-chain
  ::load-home-page
  (fn [_ _]))


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
