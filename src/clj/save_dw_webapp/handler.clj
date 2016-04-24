(ns save-dw-webapp.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [save-dw-webapp.layout :refer [error-page]]
            [save-dw-webapp.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [save-dw-webapp.middleware :as middleware]))

(def app-routes
  (routes
    (wrap-routes #'home-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))

(def app (middleware/wrap-base #'app-routes))
