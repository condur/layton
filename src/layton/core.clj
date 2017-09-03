(ns layton.core
  "Main/starting file, define middleware settings using app routes and run the HTTP Server on specific port"
  (:gen-class)
  (:require
    [aleph.http :as http]
    [aleph.netty :as netty]
    [layton.routes :refer [app-routes]]))

(def app
  "Define middleware settings using app routes"
  (-> app-routes))

(defn -main
  "Run the HTTP Server on specific port using defined ring middleware settings"
  []
  (let [port 3000]
    (println "Http Server has started on port:" port)
    (netty/wait-for-close (http/start-server app {:port port}))))
