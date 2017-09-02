(ns layton.routes
  (:require
    [bidi.ring :refer [make-handler]]
    [layton.resources :refer :all]))

(def bidi-routes ["/"
                  [["" index-resource]
                   ["api"
                    [["/bike-point" bike-point-resource]]]
                   [true not-found-resource]]])

(def app-routes
  (make-handler bidi-routes))
