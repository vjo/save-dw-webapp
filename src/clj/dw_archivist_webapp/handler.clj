(ns dw-archivist-webapp.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [dw-archivist-webapp.layout :refer [error-page]]
            [dw-archivist-webapp.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [dw-archivist-webapp.middleware :as middleware]))

(def app-routes
  (routes
    (wrap-routes #'home-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))

(def app (middleware/wrap-base #'app-routes))
