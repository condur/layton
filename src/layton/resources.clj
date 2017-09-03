(ns layton.resources
 (:require
    [yada.yada :as yada]
    [layton.responses :refer :all]
    [layton.transport-for-london :refer [get-bike-points]]
    [cheshire.core :as json]))

(def index-resource
  (yada/resource
   {:methods
     {:get
       {:produces "text/html"
        :response index-get-response}
      :post
       {:consumes "application/x-www-form-urlencoded"
        :produces "text/html"
        :parameters {:form {:latitude String :longitude String :radius String}}
        :response index-post-response}}}))

(def not-found-resource
  (yada/resource
   {:methods
     {:get
       {:produces "text/plain"
        :response (fn [ctx] "Not found")}}}))

(def bike-point-resource
  (yada/resource
    {:access-control
      {:realm "default"
       :scheme "Basic"
       :verify (fn [[user password]] nil)}
     :methods
      {:get
        {:produces "application/json"
         :parameters {:query {:lat String :lon String :radius String}}
         :response (fn [ctx]
                      (let [latitude (get-in ctx [:parameters :query :lat])
                            longitude (get-in ctx [:parameters :query :lon])
                            radius (get-in ctx [:parameters :query :radius])
                            bike-points (get-bike-points latitude longitude radius)]
                        (json/generate-string bike-points)))}}}))
