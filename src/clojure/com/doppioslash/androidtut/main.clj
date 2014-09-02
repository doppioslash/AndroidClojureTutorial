(ns com.doppioslash.androidtut.main
  (:use [neko.activity]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]]))

(defactivity com.doppioslash.androidtut.Reader
  :def a
  :on-create
  (fn [this bundle]
    (on-ui
     (set-content-view! a
      (make-ui [:linear-layout {}
                [:text-view {:text "Hello from Clojure!"}]])))))
