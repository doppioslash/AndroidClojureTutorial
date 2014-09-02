(ns com.doppioslash.androidtut.main
  (:use [neko.activity]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui config]]))

(declare ^android.widget.LinearLayout mylayout)
(declare add-text)

(def lines  (atom ""))

(defn get-elmt [elmt]
  (str (.getText (elmt (.getTag mylayout)))))

(defn set-elmt [elmt s]
  (on-ui (config (elmt (.getTag mylayout)) :text s)))

(defn update-ui []
  (set-elmt ::lines @lines)
  (set-elmt ::edit ""))

(defn add-text []
  (swap! lines str (get-elmt ::edit) "\n")
  (update-ui))


(def main-layout [:linear-layout {:orientation :vertical
                                 :id-holder true 
                                 :def `mylayout}
                  [:edit-text {:hint "Write here",:id ::edit}]
                  [:button {:text "Add text" :on-click (fn [_](add-text))}]
                  [:text-view {:text @lines,:id ::lines}]])

(defactivity com.doppioslash.androidtut.Reader
  :def a
  :on-create
  (fn [this bundle]
    (on-ui
     (set-content-view! a
      (make-ui main-layout)))))
