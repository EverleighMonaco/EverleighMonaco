(defmodule em-app
  (behaviour application)
  ;; app implementation
  (export
   (start 2)
   (stop 0)))

;;; --------------------------
;;; application implementation
;;; --------------------------

(defun start (_type _args)
  (logger:set_application_level 'em 'all)
  (logger:info "Starting EVERLEIGH MONACO...")
  (em-sup:start_link))

(defun stop ()
  (em-sup:stop)
  'ok)
