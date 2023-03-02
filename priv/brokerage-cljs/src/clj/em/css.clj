(ns em.css
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]))


(defn default-ul [] {:list-style "none" :padding "0" :margin "0"})

(defstyles screen
  [:body
   [:div.jumbo
    {
     :opacity 1.0
     :height "600px"
     :background-image "url(/images/stock/StockSnap_GTTRWBKT3H-1080p.jpg)"
     :background-position "center"
     :background-repeat "no-repeat"
     :background-size "cover"}]
   [:a.link {:color "black !important" :text-decoration "none"}]
   [:img.logo-small {:width "100%" :max-width "300px"}]
   [:img.logo {:width "100%" :max-width "200px"}]
   [:img.footer-logo {:width "100%" :max-width "600px"}]
   [:img.image {:width "100%"}]])
