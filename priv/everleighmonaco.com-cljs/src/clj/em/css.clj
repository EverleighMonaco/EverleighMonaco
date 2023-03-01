(ns em.css
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]))


(defn default-ul [] {:list-style "none" :padding "0" :margin "0"})

(defstyles screen
  [:body
   [:img.logo {:width "100%" :max-width "200px"}]
   [:img.footer-logo {:width "100%" :max-width "600px"}]
   [:img.image {:width "100%"}]])
