(ns layton.routes
  (:require [compojure.route :as route]
            [compojure.core :refer [GET PUT POST DELETE defroutes context]]))



(defroutes app-routes
           (GET "/" [] "Hello World")

           ;; Anything not matching the routes above will match
           ;; the / route. Treat requests not matching routes above
           ;; as requests for static resources. These come out of
           ;; the resources/public directory.
           (route/resources "/")

           ;; If we got a request that did not match any of the
           ;; routes defined above, and could not be located under
           ;; resources/public, send the not found response.
           (route/not-found "Page not found"))
