#!/usr/bin/env racket
#lang clojure
(require
 rash
 charterm
 rackjure/egal
 racket/list)

; EXPERIMENTAL. DO NOT USE.
;
; Putting this here for safe keeping as we'll need this later for
; building and assembling.
;
; Right now theres no need to actually use it.
;
; In the unlikely case, this requires racket-lang along with
; rash, charterm, and rackjure which all facilitate writing
; scheme-and-clojure'ish racket-lang shell code (which brings me joy).
;
; After you install racket do this:
;
; $ raco pkg install charterm
; $ raco pkg install rackjure
; $ raco pkg install rash
;

(defn bold [text]
  "charterm gods plz give us BOLD terminal text"
(with-charterm
  (charterm-bold)
  (charterm-display text)
  (charterm-normal))"") ; return nothing.

(defn display-help []
  "How did MIT switch from teaching SICP to Python?! WHAT?!"
  (do
      (displayln "\nusage: $ ./<everleigh | everleigh.scm> cmd")
      (for-each
       (lambda (everleigh-commands)
         (cond
           (eq? true (hash-has-key? everleigh-commands :example)) (display (str "\t" (get everleigh-commands :example) "\n"))
           (eq? true (hash-has-key? everleigh-commands :welcome)) (display (str "\n" (get everleigh-commands :welcome) "\n"))
           (eq? true (hash-has-key? everleigh-commands :title)) (displayln (str "\n" (get everleigh-commands :title)))
           (eq? true (hash-has-key? everleigh-commands :cmd)) (displayln (do
                                                                         (bold (get everleigh-commands :cmd))
                                                                         (str "\t\t" (get everleigh-commands :usage))))))
       (list
        {:welcome "The EverleighMonaco.com advanced compiler, build tool, and automator."}
        {:title "<cmd> can be the following:\n"}
        ;; {:cmd "clean" :usage "Destructively and recursively cleans the entire working tree of anything build related for *EVERYTHING*."}
        {:cmd "setup" :usage "Setups up a new Everleigh Monaco system (run me first!)."}
        {:cmd "deps" :usage "Gets deps from rebar3, leiningen, npm, etc."}
        ;; {:cmd "assets" :usage "Compiles assets (i.e css w/ garden)"}
        {:cmd "compile" :usage "Compiles everything."}
        ;; {:cmd "kill" :usage "Kill all running instances of beam.smp AND Java."}
        ;; {:cmd "\nedit" :usage "Open Emacs with EverleighMonaco as the project."}
        {:example "\nExample usage:\n"}
        {:example "% raco exe everleigh.scm && ./everleigh # compile to bytecode + run"}
        {:example "% racket everleigh.scm deploy # invoke from racket because reasons"}
        {:example "% ./everleigh.scm deps"}))
    ;; (display (str "\n\nRacket-lang, THE unsung hero.\n\n"))
    (displayln "\n(c) 2023 everleighmonaco.com\n")))

(def first-cmd-arg
  (let [args-list (vector->list (current-command-line-arguments))]
    (cond
      (not (empty? args-list)) (str (first args-list))
      :else "empty")))

(defn parse-cmd [cmd command]
  (egal? cmd command))

(defn everleigh-deps []
  "Get all our dependencies. This will be important later w/ experiemtnal"
  (do
    (putenv "DEBUG" "y")
    (displayln "\n- everleighmonaco.com -")
    (displayln "\n🆗 DEPS")
    (rash "cd priv/everleighmonaco.com-cljs;")
    (do
        (if (file-exists? "package.json")
            (rash "rm package.json;"))
        (if (file-exists? "shadow-cljs.edn")
            (rash "rm shadow-cljs.edn;")))
    (display (rash "lein deps :tree;"))
    (displayln "\n✅ clojure")
    (rash "cd ../../")
    (display (rash "rebar3 deps; rebar3 lfe deps;"))
    (displayln "\n✅ erlang")
    (displayln "🆗 Complete!!\n")))

(defn everleigh-compile []
  "Compile everything. This will be important later w/ experiemtnal.
  This also needs to be split to compile each individual part and
  We can call them all here."
  (do
    (displayln "\n- everleighmonaco.com -")
    (displayln " ")
    (displayln "Compile %")
    (display (rash "rebar3 compile;"))
    (displayln "Compile %")
    (rash "cd priv/everleighmonaco.com-cljs;")
    (display (rash "lein shadow compile app;"))
    (displayln "\n\n🆗 Complete!!\n")))

(define (everleigh-setup)
  (do
    (displayln "\n- everleighmonaco.com -\n")
    (displayln " ")
    (displayln "\n- Thank you! -\n")
    (displayln "✅ Creating log directory so yaws doesn't bark.")
    (display (rash "mkdir log"))
    (everleigh-deps)
    (displayln "\n\n🆗 SETUP is Complete!!\n")))

(let [cmd first-cmd-arg]
  (cond
    (parse-cmd cmd "setup") (everleigh-setup)
    (parse-cmd cmd "c") (everleigh-compile)
    (parse-cmd cmd "compile") (everleigh-compile)
    (parse-cmd cmd "deps") (everleigh-deps)
    (parse-cmd cmd "d") (everleigh-deps)
    (parse-cmd cmd "help") (display-help)
    (parse-cmd cmd "empty") (display-help) ; theres gotta be a more elegant way to do this.
    :else (do
              (display (str "Sorry but " cmd " is isn't a valid command!\n\n"))
              (display-help))))
