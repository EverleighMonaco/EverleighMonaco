(ns em.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[em started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[em has shut down successfully]=-"))
   :middleware identity})
