(ns layton.system
 "A wrapper for system getting variables"
 (:require
   [environ.core :refer [env]]))

(def transport-for-london-app-id
  (env :transport-for-london-app-id))

(def transport-for-london-app-key
  (env :transport-for-london-app-key))
