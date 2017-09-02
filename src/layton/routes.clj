(ns layton.routes
  (:require
    [bidi.ring :refer [make-handler]]
    [ring.util.response :as res]))

(defn index-handler
  [request]
  (res/response "Hello World"))

(defn not-found-handler
  [request]
  (res/not-found "Page not found"))

(def app-routes
  (make-handler ["/" [["" index-handler]
                      [true not-found-handler]]]))
