(ns layton.system
 "A wrapper for getting system variables"
 (:require
   [aero.core :refer [read-config]]))

(def ^:private system-variables
  (read-config ".boot-env"))

(defonce transport-for-london-app-id
  (get-in system-variables [:transport-for-london :app-id]))

(defonce transport-for-london-app-key
 (get-in system-variables [:transport-for-london :app-key]))
