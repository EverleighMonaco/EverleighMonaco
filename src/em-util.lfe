(defmodule em-util
  (export all))

;; (include-lib "lfe/include/clj.lfe")

(defun meta-out (arg-data router)
  "This function can be called by all other out functions, as it handles the
  method name parsing. YAWS cannot use this function directly."
  (let ((method-name (lfest:get-http-method arg-data))
        (path-info (lfest:parse-path arg-data)))
    (funcall router path-info method-name arg-data)))

(defun get-version ()
  (lutil:get-app-version 'em))

(defun get-versions ()
  (++ (lutil:get-versions)
      `(#(yaws ,(lutil:get-app-version 'yaws)))
      `(#(em ,(get-version)))))
