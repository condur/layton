(ns layton.resources
 (:require
    [yada.yada :as yada]
    [layton.responses :refer :all]))

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
         :response bike-point-response}}}))
