(ns save-dw-webapp.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[save-dw-webapp started successfully]=-"))
   :middleware identity})
