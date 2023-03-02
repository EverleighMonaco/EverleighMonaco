(ns em.view
  (:require
   [kee-frame.core :as kf]
   [reagent.format :as ruf :refer [format]]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [clojure.pprint :as pp :refer [cl-format]]
   [stylefy.core :as stylefy :refer [use-style]]))

(defn scroll-to-link [{:keys [title target classes sp]}]
  "Smoothly scroll to the target"
  ;; re: https://stackoverflow.com/a/72225037
  [:a {:class classes :href (str "/#" target)
       :on-click #(.scrollIntoView
                   (.getElementById js/document target)
                   (js-obj  "block" sp) ; scroll-position to move scroll
                   (js-obj "behavior" "smooth"))} title])

(defn inst-to-symbol [inst]
  "Convert loot insturment to a currency symbol"
  (case inst
    "CAD" "$"
    "USD" "$"
    "BTC" [:span (use-style {:color ""}) "₿"]))

(defn sexy-listing-text [{:keys [listing price what address]}]
  "Clean me up plz. Refactor asap. No need to rush!"
  (case (listing :loot-insturment)
    "BTC" [:div [:h2.mb-0.mt-2>b.text-primary price]
           [:h2.mt-1.mb-0 address]
           [:div
            [:small (use-style {:font-size "0.8rem"}) (ruf/capitalize-words what) " in Crypto " [:a {:href "#"} "[Learn more]"]]]]
    ; cash money currency on the else case
    [:div
     [:h2.mb-0.mt-2>b.text-danger price]
     [:h2.mt-1.mb-0 address]
     [:div [:small (use-style {:font-size "0.8rem"}) (ruf/capitalize-words what)]]]))

(defn sexy-listing [listing]
  "A listing."
  (r/as-element ; wrap.
   (let [
         first-image (first (listing :photos))
         image_url (first-image :image_url)
         what (listing :what)
         price (pp/cl-format nil "~:d" (listing :loot))
         display-price [:span (inst-to-symbol (listing :loot-insturment)) (str " " price)]
         attrib-name (first-image :attrib_name)
         attrib-url (first-image :attrib_url)
         address (str (listing :address))]

     [:div.col-6.mb-4
      [:img.image.rounded {:src image_url}]
      [:div.mb-0.mt-1 (use-style {:text-align "right"})
       [:small>a {:href attrib-url} "Photo by " attrib-name]]
       (sexy-listing-text {:listing listing :price display-price :what what :address address})
      [:hr]])))

(defn listings []
  "All the listings from the API"
  (when-let [listings @(rf/subscribe [:listings])]
    [:div.row
     (for [listing listings]
       ^{:key (:listing listing)} (sexy-listing listing))]))

(defn home-page []
  "Our home page"
  [:div
   [:div.jumbotron.p-3.p-md-5.text-white.rounded.bg-dark.jumbo
    [:div.col-md-6.px-0.bg-white.text-dark.px-4.rounded (use-style {:opacity 0.8})
     [:br]
     [:h1.display-4.mt-2.font-italic.opacity-100.text-black "A bright future..."]
     [:p.lead.my-3.text-black "This is only an example brokerage for everleighmonaco.com but can you imagine just buying a house out right for btc?!"]
     [:p.lead.mb-4
      [:a.text-blackfont-weight-bold.link {:href "#"} "Continue reading..."] [:br] [:br]]]]
   [:div.mt-1 (use-style {:text-align "right"})
    [:small "Photo by " [:a {:href "https://stocksnap.io/author/dahlhousedesign"} "Dahl House Design"]]]
   [:h3.mt-0.mb-4 "NEW LISTINGS"]
   [:hr.mb-4]
   [listings]])

(defn footer []
  [:footer.mb-4
   ;; [:hr.mb-4]
   [:div.row
    [:div.col-12.mb-4
     [scroll-to-link {:title [:img.footer-logo.mb-1 {:src "/images/everleigh.png"}] :target "home" :sp "start" :classes ""}]
     [:small {:class "d-block mb-3 text-muted"} "© 2023 everleighmonaco.com,  licensed under the " [:a {:href "https://github.com/EverleighMonaco/EverleighMonaco/blob/main/LICENSE"} "GNU Affero General Public License v3.0"]]]]])

(defn root-component []
  [:div.container>div.row>div.col-12

   [:header {:class "site-header sticky-top py-1 bg-white mt-2 mb-2"}
    [:div.row
     [:div.col-5
      [:img.image.mb-2 {:src "/images/logo.png"}]]
     [:div.col-6
      [:nav.mt-4.d-flex
       [:a.p-4.py-4.d-none.d-md-inline-block.link {:href "#"} "BUY"]
       [:a.p-4.py-4.d-none.d-md-inline-block.link {:href "#"} "SELL"]
       [:a.p-4.py-4.d-none.d-md-inline-block.link {:href "#"} "FIND AN AGENT"]
       [:a.p-4.py-4.d-none.d-md-inline-block.link {:href "#"} "ABOUT OUR EXAMPLE BROKERAGE"]]]]]

   [kf/switch-route (fn [route] (get-in route [:data :name]))
    :home home-page
    nil [:div ""]]
   [footer]])


