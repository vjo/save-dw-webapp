(ns dw-archivist-webapp.routes.home
  (:require [dw-archivist-webapp.layout :as layout]
            [dw-archivist-webapp.oauth :as oauth]
            [dw-archivist-webapp.utils :as utils]
            [compojure.core :refer [defroutes GET]]
            [clj-spotify-playlist-copier.core :as sptfy-playlist-copier]
            [clj-spotify.core :as sptfy]))

(defn home-page []
  (layout/render "home.html" {:url (oauth/authorize-uri oauth/oauth2-params "hell0")}))

(defn success-page []
  (fn [req]
    (let [{:keys [access_token]} (oauth/get-authentication-response "hell0" (:params req))
          {:keys [id]} (sptfy/get-current-users-profile {} access_token)
          playlist-name (utils/create-playlist-name)]
    (sptfy-playlist-copier/do-copy :user-id id :token access_token :playlist-name-to-copy "Discover Weekly" :playlist-name-new playlist-name :public? true)
    (layout/render "success.html" {:playlist-name playlist-name}))))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/success" [] (success-page)))
