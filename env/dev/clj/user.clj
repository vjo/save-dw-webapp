(ns user
  (:require [mount.core :as mount]
            save-dw-webapp.core))

(defn start []
  (mount/start-without #'save-dw-webapp.core/repl-server))

(defn stop []
  (mount/stop-except #'save-dw-webapp.core/repl-server))

(defn restart []
  (stop)
  (start))
