(ns save-dw-webapp.oauth
  (:require [clj-http.client :as client]
            [ring.util.codec :as codec]))

(def oauth2-params
  {:client-id (System/getenv "SPOTIFY_CLIENT_ID")
   :client-secret (System/getenv "SPOTIFY_CLIENT_SECRET")
   :authorize-uri  "https://accounts.spotify.com/authorize"
   :redirect-uri (str (System/getenv "APP_HOST") "/success")
   :access-token-uri "https://accounts.spotify.com/api/token"
   :scope "user-read-email playlist-modify-public playlist-modify-private"})

(defn authorize-uri [client-params csrf-token]
  (str
    (:authorize-uri client-params)
    "?response_type=code"
    "&client_id="
    (codec/url-encode (:client-id client-params))
    "&redirect_uri="
    (codec/url-encode (:redirect-uri client-params))
    "&scope="
    (codec/url-encode (:scope client-params))
    "&state="
    (codec/url-encode csrf-token)))

(defn get-authentication-response [csrf-token response-params]
   (if (= csrf-token (:state response-params))
     (try
       (-> (client/post (:access-token-uri oauth2-params)
                      {:form-params {:code (:code response-params)
                                     :grant_type "authorization_code" ; or see how to use it with "refresh_token"
                                     :client_id (:client-id oauth2-params)
                                     :redirect_uri (:redirect-uri oauth2-params)}
                       :basic-auth [(:client-id oauth2-params) (:client-secret oauth2-params)]
                       :as :json})
          :body)
      (catch Exception _ nil))
    nil))
