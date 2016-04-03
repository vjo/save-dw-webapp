(ns dw-archivist-webapp.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[dw-archivist-webapp started successfully]=-"))
   :middleware identity})
