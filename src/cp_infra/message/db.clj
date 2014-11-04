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

(defn message-tx [title desc]
  (cond-> {:db/id (d/tempid :db.part/user)
           :message/title title
           :message/completed? false}
          desc (assoc :message/description desc) ;; Add description if not nil
          true vector))                       ;; Wrap in vector

(defn create-message [title desc]
  @(d/transact conn (message-tx title desc)))

(defn all-messages [db]
  (->> (d/q '[:find ?id
              :where [?id :message/title]]
            db)                 ; #{[12341123] [12357223] [134571345]}
       (map first)              ; (12341123 12357223 134571345)
       (map #(d/entity db %)))) ; ({:db/id 12341123} ...)

(defn completed-messages [db]
  (->> (d/q '[:find ?id
              :where
              [?id :message/title]
              [?id :message/completed? true]]
            db)
       (map first)
       (map #(d/entity db %))))

(defn toggle-status [id status]
  @(d/transact conn [[:db/add id :message/completed? status]]))

(defn delete-message [id]
  @(d/transact conn [[:db.fn/retractEntity id]]))
