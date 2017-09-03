(ns layton.transport-for-london
 (:require
   [layton.system :as system]
   [aleph.http :as http]
   [byte-streams :as bs]
   [cheshire.core :as json]
   [ring.util.codec :refer [url-encode]]
   [clojure.string :as string]))

(defn- get-bike-points-ids
  [latitude longitude radius]
  (let [url (str "https://api.tfl.gov.uk/BikePoint"
                 "?lat=" latitude
                 "&lon=" longitude
                 "&radius=" radius
                 "&app_id=" system/transport-for-london-app-id
                 "&app_key=" system/transport-for-london-app-key)
        places  (-> @(http/get url)
                    :body
                    bs/to-string
                    json/parse-string
                    (get "places"))]

    (map #(get % "id") places)))

(defn- get-bike-points-occupancy
  [bike-points-ids]
  (let [joined-ids (url-encode (string/join "," bike-points-ids))
        url (str "https://api.tfl.gov.uk/Occupancy/BikePoints/" joined-ids)]

    (-> @(http/get url)
        :body
        bs/to-string
        json/parse-string)))

(defn get-bike-points
  [latitude longitude radius]
  (->> (get-bike-points-ids latitude longitude radius)
       (get-bike-points-occupancy)))
