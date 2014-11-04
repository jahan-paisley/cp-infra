(ns cp-infra.message
  (:require [io.pedestal.interceptor :refer [defhandler]]
            [io.pedestal.http.route :refer [url-for]]
            [ring.util.response :refer [response redirect]]
            [stencil.core :refer [render-file]]
            [datomic.api :as d]
            [cheshire.core :refer :all]
            [cp-infra.message.db :as db]
            [cp-infra.message.view :as v]
            [io.pedestal.http :refer :all]
            ))

(defhandler index [req]
            (let [messages (db/all-messages (d/db db/conn))]
              (response (v/message-index messages))
              ;;(response (render-file "create-message" {:company "paitakht iran zamin" :messages messages}))
              ))

(defhandler create [req]
            (let [title (get-in req [:form-params "title"])
                  desc (get-in req [:form-params "description"])]
              (when title
                (db/create-message title desc))
              (redirect (url-for :messages))))

(defn id [request]
  (some-> request
          (get-in [:path-params :id])
          Long/parseLong))

(defhandler delete [req]
            (when-let [id (id req)]
              (db/delete-message id))
            (redirect (url-for :messages)))

(defhandler toggle [req]
            (let [id (id req)
                  status (some-> req
                                 (get-in [:form-params "status"])
                                 Boolean/parseBoolean)]
              (when (and id
                         (some? status))
                (db/toggle-status id status)))
            (redirect (url-for :messages)))

(defhandler delete-all [req]
            (let [messages (db/all-messages (d/db db/conn))
                  ids (map :db/id messages)]
              (doall
                (map db/delete-message ids)))
            (redirect (url-for :messages)))
