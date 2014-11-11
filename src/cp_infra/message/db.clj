(ns cp-infra.message.db
  (:require [datomic.api :as d]))

(defonce uri (str "datomic:mem://" (gensym "messages")))
(d/create-database uri)
(def conn (d/connect uri))

(def schema-tx (->> "messages.edn"
                    clojure.java.io/resource
                    slurp
                    (clojure.edn/read-string {:readers *data-readers*})))

@(d/transact conn schema-tx)

(defn message-tx [title body]
  (cond-> {:db/id (d/tempid :db.part/user)
           :message/title title
           :message/sent? false}
          body (assoc :message/body body)
          true vector))

(defn create-message [title body]
  @(d/transact conn (message-tx title body)))

(defn all-messages [db]
  (->> (d/q '[:find ?id
              :where [?id :message/title]]
            db)
       (map first)
       (map #(d/entity db %))))

(defn sent-messages [db]
  (->> (d/q '[:find ?id
              :where
              [?id :message/title]
              [?id :message/sent? true]]
            db)
       (map first)
       (map #(d/entity db %))))

(defn toggle-status [id status]
  @(d/transact conn [[:db/add id :message/sent? status]]))

(defn delete-message [id]
  @(d/transact conn [[:db.fn/retractEntity id]]))
