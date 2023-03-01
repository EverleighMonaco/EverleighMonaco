(ns monaco.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[monaco started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[monaco has shut down successfully]=-"))
   :middleware identity})
