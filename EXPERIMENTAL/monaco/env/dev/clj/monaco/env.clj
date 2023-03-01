(ns monaco.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [monaco.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[monaco started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[monaco has shut down successfully]=-"))
   :middleware wrap-dev})
