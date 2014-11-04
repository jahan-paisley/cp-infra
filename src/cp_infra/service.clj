(ns cp-infra.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as ring-resp]
            [ns-tracker.core :refer [ns-tracker]]
            [io.pedestal.interceptor :refer [defon-request defon-response]]
            [cp-infra.message :as message]
            [stencil.core :as stencil]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(def modified-namespaces (ns-tracker ["src" "resources"]))

(defon-response affix-custom-server [resp]
                (update-in resp [:headers "Server"] (constantly "Computron 9000")))

(defroutes routes
  [[["/"
     ["/messages" ^:interceptors [(body-params/body-params) bootstrap/html-body]
      {:get  [:messages message/index]
       :post [:messages#create message/create]
       :delete [:messages#delete-all message/delete-all]
       }]
     ;; Set default interceptors for /about and any other paths under /
     ;;^:interceptors [(body-params/body-params) bootstrap/html-body]
     ["/about" {:get about-page}]]]])

;; Consumed by cp-infra.server/create-server
;; See bootstrap/default-interceptors for additional options you can configure
(def service {:env :prod
              ;; You can bring your own non-default interceptors. Make
              ;; sure you include routing and set it up right for
              ;; dev-mode. If you do, many other keys for configuring
              ;; default interceptors will be ignored.
              ;; :bootstrap/interceptors []
              ::bootstrap/routes routes

              ;; Uncomment next line to enable CORS support, add
              ;; string(s) specifying scheme, host and port for
              ;; allowed source(s):
              ;;
              ;; "http://localhost:8080"
              ;;
              ;;::bootstrap/allowed-origins ["scheme://host:port"]

              ;; Root for resource interceptor that is available by default.
              ::bootstrap/resource-path "/public"

              ;; Either :jetty, :immutant or :tomcat (see comments in project.clj)
              ::bootstrap/type :jetty
              ;;::bootstrap/host "localhost"
              ::bootstrap/port 8080})