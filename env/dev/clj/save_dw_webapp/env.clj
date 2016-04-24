(ns save-dw-webapp.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [save-dw-webapp.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[save-dw-webapp started successfully using the development profile]=-"))
   :middleware wrap-dev})
