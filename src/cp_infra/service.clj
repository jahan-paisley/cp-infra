(ns cp-infra.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.http.ring-middlewares :as middlewares]
            [io.pedestal.interceptor :as interceptor :refer [interceptor definterceptorfn defon-request
                                                             defon-response defmiddleware]]
            [ring.util.response :refer [response redirect]]
            [ring.middleware.session.cookie :as cookie]
            [cemerick.friend :as friend]
            [cemerick.friend [credentials :as creds] [workflows :as workflows]]
            [cp-infra.authen :refer :all]
            [cp-infra.message :as message]
            [cp-infra.login.view :as login-view]))

(defn about-page
  [request]
  (response (format "Clojure %s - served from %s"
                    (clojure-version)
                    (route/url-for ::about-page))))

(defn logout-page [_]
  (-> (redirect "/")
      friend/logout*))

(def login-action
  (constantly nil))

(defn login-page [_]
  (response (login-view/login-page)))

(defroutes routes
           [[["/" ^:interceptors [(body-params/body-params)
                                  middlewares/keyword-params
                                  bootstrap/html-body
                                  (middlewares/session {:store (cookie/cookie-store)})
                                  (friend-authenticate-interceptor friend-config)]
              ["/messages"
               ^:interceptors [(friend-authorize-interceptor #{:cp-infra.authen/admin})]
               {:get    [:messages message/index]
                :post   [:message#create message/create]
                :delete [:messages#delete-all message/delete-all]}
               ["/:id" {:delete [:message#delete message/delete]}]
               ["/toggle" {:put [:message#toggle message/toggle]}]]

              ["/about" {:get about-page}]
              ["/login" {:get [:login login-page] :post [:login#post login-action]}]
              ["/logout" {:get logout-page}]]]])

(def service {:env                      :prod
              ::bootstrap/routes        routes
              ::bootstrap/resource-path "/public"
              ::bootstrap/type          :jetty
              ::bootstrap/port          8080})