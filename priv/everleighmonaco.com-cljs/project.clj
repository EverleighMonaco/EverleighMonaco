(defproject em "0.1.0-SNAPSHOT"
  :description "Real Estate, on STERIODS."
  :url "https://everleighmonaco.com/"

  :dependencies [[ch.qos.logback/logback-classic "1.4.4"]
                 [cljs-ajax "0.8.4"]
                 [clojure.java-time "1.1.0"]
                 [com.cognitect/transit-clj "1.0.329"]
                 [com.cognitect/transit-cljs "0.8.280"]
                 [com.google.javascript/closure-compiler-unshaded "v20220803"]
                 [cprop "0.1.19"]
                 [day8.re-frame/http-fx "0.2.4"]
                 [expound "0.9.0"]
                 [funcool/struct "1.4.0"]
                 ;; [json-html "0.4.7"]
                 [kee-frame "1.1.2" :exclusions [metosin/reitit-core org.clojure/core.async]]
                 [luminus-transit "0.1.5"]
                 [luminus-undertow "0.1.16"]
                 [luminus/ring-ttl-session "0.3.3"]
                 ;; [markdown-clj "1.11.3"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.18"]
                 [metosin/ring-http-response "0.9.3"]
                 [mount "0.1.16"]
                 ;; [nrepl "1.0.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.60" :scope "provided"]
                 [org.clojure/core.async "1.5.648"]
                 ;; [org.clojure/tools.cli "1.0.214"]
                 [org.clojure/tools.logging "1.2.4"]
                 ;; [org.webjars.npm/bulma "0.9.4"]
                 ;; [org.webjars.npm/material-icons "1.10.8"]
                 [org.webjars/webjars-locator "0.45"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [re-frame "1.2.0"]
                 [reagent "1.1.1"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.9.6"]
                 [ring/ring-defaults "0.3.4"]
                 [selmer "1.12.55"]
                 [thheller/shadow-cljs "2.20.3" :scope "provided"]
                 [garden "1.3.10"
                  ;; FIXME: sort out the garden warning. its only a warning but
                  ;; its annoying always seeing it in the stdout 
                  :git/url "https://github.com/noprompt/garden.git"
                  :git/sha "05590ecb5f6fa670856f3d1ab400aa4961047480"]
                 ;; [bidi "2.1.5"]
                 ;; [kibu/pushy "0.3.8"]
                 [stylefy "3.2.0"]
                 [stylefy/reagent "3.0.0"]

                 ]

  :min-lein-version "2.0.0"

  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s/"
  :main ^:skip-aot em.core

  :plugins [
            [lein-shadow "0.3.1"]
            [lein-garden "0.3.0"]
            [lein-shell "0.5.0"]
            [lein-npm "0.6.2"]
            [wfxr/lein-project "1.0.1"]]

  :npm {
        :dependencies [
                       ["create-react-class" "15.7.0"]
                       ["react" "17.0.2"]
                       ["react-dom" "17.0.2"]
                       ["font-awesome" "4.2.0"]
                       ["shadow-cljs" "2.19.9"]
                       ["bootstrap" "5.2.3"]
                       ]}

  :clean-targets ^{:protect false}
  [:target-path "target/cljsbuild"]

  :shadow-cljs {
                :builds
                {:app {:target     :browser
                       :output-dir "../sites/everleighmonaco.com-dev/assets"
                       :asset-path "../sites/everleighmonaco.com-dev/assets"
                       :modules    {:everleighmonaco {:entries [em.app]}}
                       :devtools   {:watch-dir "..sites/everleighmonaco-dev/assets"
                                    :preloads  [re-frisk.preload cljs-devtools.preload ]}
                       :dev        {
                                    :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}}}
                 :release
                 {:target     :browser
                  :output-dir "../sites/everleighmonaco.com-release/assets"
                  :asset-path "../sites/everleighmonaco.com-release/assets"
                  :modules    {:everleighmonaco {:entries [em.app]}}
                  :devtools   {:preloads []}
                  :compiler-options {:optimizations :advanced
                                     :source-map false
                                     :stable-names true
                                     :verbose false
                                     :anon-fn-naming-policy :unmapped
                                     :browser-repl false
                                     :closure-defines {"goog.DEBUG" false}
                                     :compiler-stats true
                                     :fingerprint true
                                     :infer-externs true
                                     :optimize-constants true
                                     :output-wrapper :string
                                     :parallel-build true
                                     :print-input-delimiter false
                                     :pseudo-names true
                                     :rename-prefix-namespace "everleighmonaco"}}

                 }}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   em.css/screen
                     :compiler     {:output-to     "../sites/everleighmonaco.com-dev/css/everleighmonaco.css"
                                    :pretty-print? true}}]}



  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" ["run" "-m" "shadow.cljs.devtools.cli" "release" "app"]]
             :aot :all
             :uberjar-name "em.jar"
             :source-paths ["env/prod/clj"  "env/prod/cljs" ]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn" ]
                  :dependencies [[binaryage/devtools "1.0.6"]
                                 [cider/piggieback "0.5.3"]
                                 [org.clojure/tools.namespace "1.3.0"]
                                 [pjstadig/humane-test-output "0.11.0"]
                                 [prone "2021-04-23"]
                                 [re-frisk "1.6.0"]
                                 [ring/ring-devel "1.9.6"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                 [jonase/eastwood "1.2.4"]
                                 [cider/cider-nrepl "0.26.0"]] 
                  
                  
                  :source-paths ["env/dev/clj"  "env/dev/cljs" "test/cljs" ]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user
                                 :timeout 120000}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn" ]
                  :resource-paths ["env/test/resources"] 
                  
                  
                  }
   :profiles/dev {}
   :profiles/test {}})
