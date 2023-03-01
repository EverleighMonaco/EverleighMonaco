(ns em.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [em.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[em started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[em has shut down successfully]=-"))
   :middleware wrap-dev})
