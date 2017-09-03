(ns layton.responses
 (:require
   [hiccup.core :refer [html]]
   [layton.transport-for-london :refer [get-bike-points]]))

(defn index-get-response
  [ctx]
  (html
    [:form {:method :post}
      [:table
        [:tr [:th "Find bike points by:"]]
        [:tr [:td "Latitude:"] [:td [:input {:name "latitude" :type :text :value "51.556638"}]]]
        [:tr [:td "Longitude:"] [:td [:input {:name "longitude" :type :text :value "-0.005575"}]]]
        [:tr [:td "Radius:"] [:td [:input {:name "radius" :type :text :value "1550"}]]]]
      [:input {:type :submit}]]))

(defn index-post-response
 [ctx]
 (let [{:keys [latitude longitude radius]} (get-in ctx [:parameters :form])
       bike-points (get-bike-points latitude longitude radius)]
  (html
    [:table {:border 1 :cellpadding 10}
     [:tr [:th "Id"] [:th "Name"] [:th "Bikes Count"] [:th "Empty Docks"] [:th "Total Docks"]]
     (for [bike-point bike-points]
      [:tr [:td (get bike-point "id")]
           [:td (get bike-point "name")]
           [:td (get bike-point "bikesCount")]
           [:td (get bike-point "emptyDocks")]
           [:td (get bike-point "totalDocks")]])])))
