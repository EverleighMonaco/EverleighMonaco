(ns ^:dev/once em.app
  (:require
    [em.core :as core]
    [cljs.spec.alpha :as s]
    [expound.alpha :as expound]
    ;; NOTE: this is temp btw
    ;; [devtools.core :as devtools]
    ))

(extend-protocol IPrintWithWriter
  js/Symbol
  (-pr-writer [sym writer _]
    (-write writer (str "\"" (.toString sym) "\""))))

(set! s/*explain-out* expound/printer)

(enable-console-print!)

;; (devtools/install!)

(core/init!)
