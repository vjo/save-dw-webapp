(ns user
  (:require [mount.core :as mount]
            dw-archivist-webapp.core))

(defn start []
  (mount/start-without #'dw-archivist-webapp.core/repl-server))

(defn stop []
  (mount/stop-except #'dw-archivist-webapp.core/repl-server))

(defn restart []
  (stop)
  (start))


