(ns cp-infra.service
  (:require [io.pedestal.http :as bootstrap]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ns-tracker.core :refer [ns-tracker]]
            [ring.util.response :refer [response redirect]]
            [io.pedestal.interceptor :as interceptor :refer [interceptor definterceptorfn defon-request defon-response defmiddleware]]
            ;; requires below were added for sample
            [cemerick.friend :as friend]
            [cemerick.friend [credentials :as creds] [workflows :as workflows]]
            [io.pedestal.http.ring-middlewares :as middlewares]
            [ring.middleware.session.cookie :as cookie]
            [cp-infra.message :as message]
            [cp-infra.login.view :as login-view]
            ;[stencil.core :as stencil]
            ))

(defn about-page
  [request]
  (response (format "Clojure %s - served from %s"
                    (clojure-version)
                    (route/url-for ::about-page))))

(def modified-namespaces (ns-tracker ["src" "resources"]))

(definterceptorfn friend-authenticate-interceptor
                  "Creates a friend/authenticate interceptor for a given config."
                  [auth-config]
                  (interceptor
                    :error
                    (fn [{:keys [request] :as context} exception]
                      ;; get exception details without Slingshot in this sample
                      (let [exdata (ex-data exception)
                            extype (-> exdata :object :cemerick.friend/type)]
                        (if (#{:unauthorized} extype)
                          ;; unauthorized errors should generate a response using catch handler
                          (let [handler-map (:friend/handler-map request)
                                response ((:catch-handler handler-map) ;handler to use
                                          (assoc (:request handler-map) ;feed exception back in
                                                 :cemerick.friend/authorization-failure exdata))]
                            ;; respond with generated response
                            (assoc context :response response))
                          ;; re-throw other errors
                          (throw exception))))
                    :enter
                    (fn [{:keys [request] :as context}]
                      (let [response-or-handler-map
                            (friend/authenticate-request request auth-config)]
                        ;; a handler-map will exist in authenticated request if authenticated
                        (if-let [handler-map (:friend/handler-map response-or-handler-map)]
                          ;; friend authenticated the request, so continue
                          (assoc-in context [:request :friend/handler-map] handler-map)
                          ;; friend generated a response instead, so respond with it
                          (assoc context :response response-or-handler-map))))
                    :leave
                    ;; hook up friend response handling
                    (middlewares/response-fn-adapter friend/authenticate-response)))

(def users
  "root/clojure login using bcrypt."
  (let [password (creds/hash-bcrypt "clojure")]
    {"root" {:username "root" :password password :roles #{::admin}}}))

(def friend-interceptor
  (friend-authenticate-interceptor
    {:allow-anon?             true
     :unauthenticated-handler #(workflows/http-basic-deny "Pedestal demo" %)
     :workflows               [(workflows/http-basic
                                 :credential-fn #(creds/bcrypt-credential-fn users %)
                                 :realm "Pedestal demo")]}))

;(definterceptorfn friend-authorize-interceptor
;                  "Creates a friend interceptor for friend/authorize."
;                  [roles]
;                  (interceptor
;                    :enter
;                    (fn [{:keys [request] :as context}]
;                      (let [auth (:auth (:friend/handler-map request))]
;                        ;; check user has an authorized role
;                        (if (friend/authorized? roles auth)
;                          ;; authorized, so continue
;                          context
;                          ;; unauthorized, so throw exception for authentication interceptor
;                          (friend/throw-unauthorized auth {:cemerick.friend/required-roles
;                                                           roles}))))))



(def friend-config
  "A friend config for interactive form use."
  {:login-uri           "/login"
   :allow-anon? false
   :default-landing-uri "/messages"
   :workflows           [(workflows/interactive-form)]
   :credential-fn       (partial creds/bcrypt-credential-fn users)})

(defn logout-page [_]
  ;; redirect home and have your friend logout the user
  (-> (redirect "/")
      friend/logout*))

;; stub login action needed for routing purposes.
(def login-action (constantly nil))

(defn login-page [_]
  (response (login-view/login-page)))

(defroutes routes
           [[["/"
              ^:interceptors [(body-params/body-params)
                              bootstrap/html-body           ;; fix for interactive-form workflow
                              middlewares/keyword-params
                              ;; session is required by interactive-form workflow
                              (middlewares/session {:store (cookie/cookie-store)})
                              ]
              ["/messages" ^:interceptors [(friend-authenticate-interceptor friend-config)]
               {:get    [:messages message/index]
                :post   [:message#create message/create]
                :delete [:messages#delete-all message/delete-all]}
               ["/:id" {:delete [:message#delete message/delete]}]
               ["/toggle" {:put [:message#toggle message/toggle]}]]
              ;; Set default interceptors for /about and any other paths under /
              ;; ^:interceptors [(body-params/body-params) bootstrap/html-body]
              ["/about" {:get about-page}]
              ["/login" ^:interceptors [(friend-authenticate-interceptor friend-config)]
                        {:get  [:login login-page]
                         :post [:login#post login-action]}]
              ["/logout" {:get logout-page}]
              ]]])

;; Consumed by cp-infra.server/create-server
;; See bootstrap/default-interceptors for additional options you can configure
(def service {:env                      :prod
              ;; You can bring your own non-default interceptors. Make
              ;; sure you include routing and set it up right for
              ;; dev-mode. If you do, many other keys for configuring
              ;; default interceptors will be ignored.
              ;; :bootstrap/interceptors []
              ::bootstrap/routes        routes

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
              ::bootstrap/type          :jetty
              ;;::bootstrap/host "localhost"
              ::bootstrap/port          8080})