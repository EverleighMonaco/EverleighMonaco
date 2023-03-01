(ns em.view
  (:require
   [kee-frame.core :as kf]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [stylefy.core :as stylefy :refer [use-style]]
   ))

(defn scroll-to-link [{:keys [title target classes sp]}]
  "Smoothly scroll to the target"
  ;; re: https://stackoverflow.com/a/72225037
  [:a {:class classes :href (str "/#" target)
       :on-click #(.scrollIntoView
                   (.getElementById js/document target)
                   (js-obj  "block" sp) ; scroll-position to move scroll
                   (js-obj "behavior" "smooth"))} title])

(defn home-page []
  [:div.col-12 {:id "home"}
   [:img.image {:src "/images/pexels-jeffrey-czum-2501965-cropped.jpg"}]
   [:div.mb-2 (use-style {:text-align "right"}) [:small "Four Colorful Houses by " [:a {:href "https://www.pexels.com/photo/four-colourful-houses-2501965/"} "Jeffrey Czum"]]]
   [:div {:class "position-relative overflow-hidden text-center"}
    [:div {:class "col-md-8 p-lg-8 mx-auto my-8 mt-1 mb-1 p-4"}
     [:h1 {:class "display-4 fw-normal"}
      "A real estate technology platform built for the future."]
     [:p {:class "lead fw-normal"}
      "We're still in a very early, preliminary, and exploratory stage but you can jump into the " [:a {:href "https://github.com/EverleighMonaco/EverleighMonaco"} "source code"] " right now."]
     [:a.mt-2.mb-2 {:class "btn btn-outline-secondary btn-lg", :href "#"} "Coming soon"]]
    [:div {:class "product-device shadow-sm d-none d-md-block"}]
    [:div {:class "product-device product-device-2 shadow-sm d-none d-md-block"}]]

   [:div.row
    [:div-col-12 {:id "features"}
     [:hr.mt-0]
     [:h1.display-4.mb-2 "Features"
      [:i.fa.fa-lightbulb-o (use-style {:margin-left "20px"})]]]

    [:div.col-md-6.col-12
     [:h5.mt-1 "Everleigh starts with Erlang."][:p "Everleigh Monaco is built on top of the rock solid, distributed, soft real-time, fault-tolerant, high availability, and ultimately battle proven " [:a {:href "https://www.erlang.org/doc/system_architecture_intro/sys_arch_intro.html"} "Open Telecom Platform"] "."]
     [:p "Out of the box Everleigh can process an insane amount of data, run tons of different websites and APIs, and handle at least a million+ concurrent connections per second between the website(s) and API(s)."]]

    [:div.col-md-6.col-12
     [:h5.mt-1 "Everleigh plays well with others."]
     [:p "Everleigh was designed with integration, interop, and Erlang's "[:a {:href "https://www.erlang.org/doc/reference_manual/processes.html"} "processes"] " in mind to take advantage of libraries written in Java, Python, Elixir, JavaScript, Ruby etc. for data processing, smart contract integration, data visualization, artificial intelligence, machine learning, natural language processing, data transformation, enterprise integration, and many other things."]]
    [:div.col-md-6.col-12
     [:h5.mt-1 "Everleigh loves data."]
     [:p "Data coming in and out, getting stored, converted from one representation to another, passed from one function to another and then out the API. Once we can start converting this data into information that we can then immediately take business action on, now we're really talking!"]]

    [:div.col-md-6.col-12
     [:h5.mt-1 "Everleigh is primarily written in Lisp, AI's first language."]
     [:p "Back in 1958, " [:a {:href "https://en.wikipedia.org/wiki/John_McCarthy_(computer_scientist)"} "Dr. John McCarthy"] ", the literal father of " [:b "artificial intelligence"] " (who coined the phrase) created  " [:a {:href "https://history-computer.com/lisp-programming-language-guide/"} "the Lisp programming language"] " during his research into AI. For the last 65 years it's been nothing but galaxy brain wizard hat stuff which is magical and perfect for building these types of systems."]]

    [:div.col-12.col-md-12 {:id "roadmap"}
     [:hr.mt-4]
     [:h1.display-4.mb-3 "Road map"
      [:i.fa.fa-map-o (use-style {:margin-left "20px"})]]

     [:h5.mt-1 "Everleigh's game plan moving forward —"]
     [:p "We've really got lots to do but in the short term we're tackling:"]
     [:ul ; (use-style {:marign 0 :padding 0})
      [:li [:b "Add initial data storage system"]
       [:p "For now (i.e the next two weeks or more) we're either going to use " [:a {:href "https://www.erlang.org/doc/man/mnesia.html"} "mnesia"] " or " [:a {:href "https://docs.riak.com/riak/kv/latest/developing/getting-started/erlang/index.html"} "Riak"] " to store our data like our example brokerage, agents, and property listings."]]

      [:li [:b "Add initial data importing system"]
       [:p "We'll need to import all types of data into the system. For now lets start small and basic with listing data " [:a {:href "https://pierrecarapetian.com/how-to-read-mls-listing-sheet/"} "(i.e MLS®)"] " and go from there. Small here is totally fine, Everleigh's tech stack makes light work of data processing then transforming that data."]]

      [:li [:b "Add initial property listing system"]
       [:p "We'll need to be able to add, remove, update, and search/filter our listing data, and its going to be incredibly helpful to be able to do this ASAP."]
       ]

      [:li [:b "Update our API to handle this new functionality."]
       [:p "Once we add all this new stuff we can start connecting it to our ReactJS front end."]
       ]

      [:li [:b "Add system/admin dashboard"]
       [:p "All this new stuff we're adding, its important that you get to start to see how this stuff looks visually instead of just in the development console and source code."]]
      [:li [:b "Add initial data transforming facilities"]
       [:p "With listing data, CRM data, various different API data, we'll need to get a start on being able to take whatever is thrown at Everleigh and transform it into a representation we can then work with."]]

      [:li [:b "Add demo real estate brokerage site"]
       [:p "This will be our initial example of a business use-case for Everleigh Monaco."]]
      [:li [:b "Add demo real estate agent site"]
       [:p "This will be our second example of a business use-case for Everleigh Monaco that can either be part of a brokerage or stand on its own."]]]]

    [:div-col-12 {:id "rationale"}
     [:hr]
     [:h1.display-4.mb-2 "Rationale"
      [:i.fa.fa-balance-scale (use-style {:margin-left "20px"})]]]

    [:div.col-md-12.col-12
     [:h5.mt-1 "Everleigh's power comes from leverage."]
     [:p "By way of being an OTP application written in Erlang, Lisp Flavoured Erlang, Clojure, and ClojureScript, Everleigh can utilize not only Erlang's BEAM virtual machine but also the Java Virtual Machine. This became interesting to me while researching machine learning libraries to use like " [:a {:href "https://deeplearning4j.konduit.ai/"} "deeplearning4j"] " which we can take advantage of."]
     [:p "There's more! We get access instant access to stuff like developing desktop and mobile apps for free with " [:a {:href "https://github.com/Tensegritics/ClojureDart"} "ClojureDart"] " and "[:a {:href "https://cljsrn.org/"} "ReactNative"] ", access to "[:a {:href "https://pytorch.org/"} "pytorch"] " through " [:a {:href "https://github.com/clj-python/libpython-clj"} "libpython-clj"] ", " [:a {:href "https://www.tensorflow.org/jvm"} "TensorFlow"] " through Java, "[:a {:href "https://github.com/elixir-nx/axon"} "Axon"] " through Elixir and the list goes on with the possibilities being endless."]]

    [:div.col-12.col-md-12 {:id "contrib"}
     [:hr]
     [:h1.display-4.mb-3 "Contributing"
      [:i.fa.fa-code (use-style {:margin-left "20px"})]]
     [:h5.mt-1 "Everleigh Monaco is open source software."]
     [:p "We'll be putting together our contributing guidelines, code of conduct, and some other documentation in the very very near future."]
     [:p.mb-0 "Stay tuned."]]]])

(defn footer []
  [:footer.py-5.text-center
   [:hr.mb-4]
   [:div.row
    [:div.col-12
     [scroll-to-link {:title [:img.footer-logo.mt-4.mb-1 {:src "/images/everleigh-monaco.png"}] :target "home" :sp "start" :classes ""}]
     [:small {:class "d-block mb-3 text-muted"} "© 2023 everleighmonaco.com,  licensed under the " [:a {:href "https://github.com/EverleighMonaco/EverleighMonaco/blob/main/LICENSE" } "GNU Affero General Public License v3.0"]]]]])

(defn root-component []
  [:div.container>div.row>div.col-12
   [:header {:class "site-header sticky-top py-1 bg-white mt-2 mb-2"}
    [:nav {:class "container d-flex flex-column flex-md-row justify-content-between"}
     [:a {:class "py-2", :href "#", :aria-label "Product"}
      [:img.logo {:src "/images/everleigh.png"}]]
     [scroll-to-link {:title "Home" :target "home" :sp "start" :classes "py-2 d-none d-md-inline-block"}]
     [scroll-to-link {:title "Roadmap" :target "roadmap" :sp "center" :classes "py-2 d-none d-md-inline-block"}]
     [scroll-to-link {:title "Rationale" :target "rationale" :sp "start"  :classes "py-2 d-none d-md-inline-block"}]
     [scroll-to-link {:title "Contributing" :target "contrib" :sp "start" :classes "py-2 d-none d-md-inline-block"}]
     [:a.py-2.d-none.d-md-inline-block {:href "https://github.com/EverleighMonaco/EverleighMonaco"} "Source Code"]]]

   [kf/switch-route (fn [route] (get-in route [:data :name]))
    :home home-page
    nil [:div ""]]
   [footer]
   ])
