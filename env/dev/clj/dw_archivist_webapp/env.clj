(ns dw-archivist-webapp.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [dw-archivist-webapp.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[dw-archivist-webapp started successfully using the development profile]=-"))
   :middleware wrap-dev})
