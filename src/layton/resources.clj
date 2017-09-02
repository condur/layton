(ns layton.resources
 (:require
    [yada.yada :as yada]))

(def index-resource
  (yada/resource
   {:produces
    {:media-type "text/plain"}
    :methods
     {:get
       {:response (fn [ctx] "Hello World from Yada!")}}}))

(def not-found-resource
  (yada/resource
   {:produces
     {:media-type "text/plain"}
    :methods
     {:get
       {:response (fn [ctx] "Not found")}}}))

(def bike-point-resource
  (yada/resource
    {:produces
       {:media-type "text/plain"}
     :methods
       {:get
        {:parameters {:query {:id String}}
         :response (fn [ctx] (str "Bike point resource: " (get-in ctx [:parameters :query :id])))}}}))
