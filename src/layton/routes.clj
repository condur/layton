(ns layton.routes
  (:require
    [yada.yada :as yada]
    [bidi.ring :refer [make-handler]]))

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

(def app-routes
  (make-handler ["/" [["" index-resource]
                      [true not-found-resource]]]))
