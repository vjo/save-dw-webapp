(ns save-dw-webapp.utils
  (:require [clj-time.core :as t]
            [clj-time.format :as f]))

(defn get-monday-from-week [date]
  "Return Monday from a day of a week"
  (let [monday 1 ; Monday is the first day of the week
        today (t/day-of-week date)
        diff (- monday today)]
    (t/plus date (t/days diff))))

(defn create-playlist-name []
  "Create a playlist name based on current week's Monday
   e.g. \"2016-03-21 Discover Weekly\"
  "
  (str (#(f/unparse (f/formatters :year-month-day) %) (get-monday-from-week (t/now))) " Discover Weekly"))
