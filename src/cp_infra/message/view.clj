(ns cp-infra.message.view
  (:require [io.pedestal.http.route :refer [url-for]]
            [hiccup.page :refer [html5]]
            [hiccup.core :refer [html h]]
            [hiccup.form :as f]))

(defn delete-message-form [message]
  [:form
   {:action (url-for :message#delete
                     :params {:id (:db/id message)}
                     :method-param "_method")
    :method :post}
   [:button.btn.btn-danger {:type "submit"} "Delete"]])

(defn toggle-message-form [message]
  (let [current-status (:message/completed? message)
        class (str "btn " (if current-status
                            "btn-warning"
                            "btn-success"))
        label (if current-status "Uncomplete" "Complete")]
    [:form
     {:action (url-for :message#toggle
                       :params {:id (:db/id message)}
                       :method-param "_method")
      :method :post}
     [:input {:type :hidden :name "status" :value (str (not current-status))}]
     [:button {:type "submit" :class class} label]]))

(defn delete-all-form []
  [:form
   {:action (url-for :messages#delete-all
                     :method-param "_method")
    :method :post}
   [:button.btn.btn-danger {:type "submit"} "Delete All"]])

(defn message-form [] ;; Later, this could take an existing message...
  [:form.form-horizontal
   {:action (url-for :messages#create)
    :method :post}
   [:p.lead "Add message"]
   [:div.form-group
    [:label.control-label.col-sm-2 {:for "title-input"} "Title"]
    [:div.col-sm-10
     [:input.form-control {:type "text"
                           :name "title"
                           :id "title-input"
                           :placeholder "I need to..."
                           :required true}]]]
   [:div.form-group
    [:label.control-label.col-sm-2 {:for "description-input"} "Description"]
    [:div.col-sm-10
     [:input.form-control {:type "text"
                           :name "description"
                           :id "description-input"
                           :placeholder "because..."}]]]
   [:div.form-group
    [:div.col-sm-offset-2.col-sm-10
     [:button.btn.btn-default {:type "submit"} "Create"]]]])

(defn message-index [messages]
  (html5 {:lang "en"}
         [:head
          [:title "My messages"]
          [:meta {:name :viewport
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "/bootstrap/css/bootstrap.min.css"
                  :rel "stylesheet"}]]
         [:body
          [:div.container
           [:h1 "My messages"]
           [:div.row
            (if (seq messages)
              [:div
               [:table.table.table-striped
                [:thead
                 [:tr
                  [:th "Title"]
                  [:th "Description"]]]
                [:tbody
                 (for [message messages]
                   [:tr
                    [:td (:message/title message)]
                    [:td (:message/description message)]
                    [:td (toggle-message-form message)]
                    [:td (delete-message-form message)]])]]
               (delete-all-form)]
              [:p "All done!"])]
           (message-form)]
          [:script {:src "http://code.jquery.com/jquery-2.1.0.min.js"}]
          [:script {:src "/bootstrap/js/bootstrap.min.js"}]]))
