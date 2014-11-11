(ns cp-infra.login.view
  (:require [io.pedestal.http.route :refer [url-for]]
            [hiccup.page :refer [html5]]
            [hiccup.core :refer [html h]]
            [hiccup.form :as f]))

(defn login-form []                                         ;; Later, this could take an existing message...
  [:form.form-horizontal
   {:action (url-for :login#post)
    :method :post}
   [:p.lead "login"]
   [:div.form-group
    [:label.control-label.col-sm-2 {:for "username-input"} "username"]
    [:div.col-sm-10
     [:input.form-control {:type        "text"
                           :name        "username"
                           :id          "username-input"
                           :placeholder "Username ..."
                           :required    true}]]]
   [:div.form-group
    [:label.control-label.col-sm-2 {:for "password-input"} "password"]
    [:div.col-sm-10
     [:input.form-control {:type     "password"
                           :name     "password"
                           :id       "password-input"
                           :required true}]]]
   [:div.form-group
    [:div.col-sm-offset-2.col-sm-10
     [:button.btn.btn-default {:type "submit"} "Login"]]]])

(defn login-page []
  (html5 {:lang "en"}
         [:head
          [:title "Login"]
          [:meta {:name    :viewport
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "/bootstrap/css/bootstrap.min.css"
                  :rel  "stylesheet"}]]
         [:body
          [:div.container
           [:h1 "Login"]
           [:div.row
            (login-form)]
           [:script {:src "/js/jquery-2.1.0.min.js"}]
           [:script {:src "/bootstrap/js/bootstrap.min.js"}]]]))

(defn logout []
  [:a {:href "/logout"} "Logout"])