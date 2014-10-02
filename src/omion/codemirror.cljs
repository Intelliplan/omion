(ns omion.codemirror
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:use            [cljs.core.async :only [chan  >! <!]])
  (:require [domina :as dom]
            [domina.css :as css]
            [cljs.reader]))

(def  cm (atom nil))
(defn cd [] (.getDoc @cm))
(defn cv [& [new-value]] (if new-value (do (.setValue (cd) new-value) new-value) (.getValue (cd))))

(defn markup [id init-content]
  [:div {:id id :style {:margin-top 20 :margin-bottom 20}}
   [:textarea {:value (prn-str init-content)}]])

(defn init! [id cm-out-queue]
  (reset! cm (.fromTextArea js/CodeMirror (dom/single-node (css/sel (str "#" id " textarea")))
                            (clj->js {;;:theme "xq-light"
                                      :lineNumbers true
                                      :autofocus true
                                      :extraKeys {"Ctrl-Enter" (fn [] (go (>! cm-out-queue {:type :codemirror/value-poke
                                                                                            :value (cv)})))}}))))
