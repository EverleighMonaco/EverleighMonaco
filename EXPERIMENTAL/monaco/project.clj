;; I started writing this at 5:30 in the morning after I woke up saying SCALA.
;; We're using lein here to grab our java/scala stuff... if we go more scala we'll switch to sbt
;; ALSO I'm running this on my laptop because its got 64gb of ram and 2 different nvidia gpus.

(defproject monaco "0.1.0-SNAPSHOT"

  :description "A formula one racing car with an emphasis on performance over everything, made from Java'ish"
  :url "https://everleighmonaco.com"

  :dependencies [[ch.qos.logback/logback-classic "1.4.4"]
                 [cljs-ajax "0.8.4"]
                 [clojure.java-time "1.1.0"]
                 [com.google.javascript/closure-compiler-unshaded "v20220803"]
                 [com.xtdb/xtdb-core "1.19.0-beta1"]
                 [com.xtdb/xtdb-rocksdb "1.19.0-beta1"]
                 [cprop "0.1.19"]
                 [day8.re-frame/http-fx "0.2.4"]
                 [expound "0.9.0"]
                 [funcool/struct "1.4.0"]
                 [json-html "0.4.7"]
                 [kee-frame "1.1.2" :exclusions [metosin/reitit-core org.clojure/core.async]]
                 [luminus-http-kit "0.2.0"]
                 [luminus-transit "0.1.5"]
                 [luminus/ring-ttl-session "0.3.3"]
                 [markdown-clj "1.11.3"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.18"]
                 [metosin/ring-http-response "0.9.3"]
                 [mount "0.1.16"]
                 [nrepl "1.0.0"]
                 [org.clojure/clojure "1.11.1"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/tools.cli "1.0.214"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.webjars.npm/bulma "0.9.4"]
                 [org.webjars.npm/material-icons "1.10.8"]
                 [org.webjars/webjars-locator "0.45"]
                 [re-frame "1.2.0"]
                 [reagent "1.1.1"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.9.6"]
                 [ring/ring-defaults "0.3.4"]
                 [selmer "1.12.55"]
                 [thheller/shadow-cljs "2.20.3" :scope "provided"]
                 ;; literal cowboy shit but lets do it
                 [io.github.erp12/fijit "1.0.8"]

                 [top.tangyh.basic/lamp-core "3.8.2"] ; read https://github.com/pityka/lamp
                 [org.webjars.npm/tensorflow "0.7.0"] ; tensorflow_scala is from forever ago re: https://github.com/eaplatanios/tensorflow_scala

                 ;; https://mvnrepository.com/artifact/ai.djl/api
                 [ai.djl/api "0.21.0"] 

                 ;; https://mvnrepository.com/artifact/ai.djl.pytorch/pytorch-engine
                 [ai.djl.pytorch/pytorch-engine "0.21.0"]

                 ; re: https://github.com/sbrunk/storch
                 [dev.storch/core_3 "0.0-fa334c2-SNAPSHOT"
                  :git/url "https://github.com/sbrunk/storch.git"
                  :git/sha "bd2ea952704d2438a29f117781bf27de0abf8fce"]
                 ; re: https://storch.dev/installation.html#adding-the-native-pytorch-libraries
                 [org.bytedeco/pytorch-platform-gpu "1.12.1-1.5.8"]
                 [org.bytedeco/cuda-platform-redist "11.8-8.6-1.5.8"]]

  :min-lein-version "2.0.0"
  
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot monaco.core

  :plugins [
            [lein-shadow "0.3.1"]
            [lein-garden "0.3.0"]
            [lein-shell "0.5.0"]
            [lein-npm "0.6.2"]
            [wfxr/lein-project "1.0.1"]]

  ;; :plugins [[reifyhealth/lein-git-down "0.4.1"]]
  ;; :repositories [["public-github" {:url "git://github.com"}]]

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "monaco.jar"
             :source-paths ["env/prod/clj"  "env/prod/cljs" ]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn" ]
                  :dependencies [[org.clojure/tools.namespace "1.3.0"]
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
                  :resource-paths ["env/test/resources"] }
   :profiles/dev {}
   :profiles/test {}})
