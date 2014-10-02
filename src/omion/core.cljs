(ns omion.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:use            [cljs.core.async :only [chan  >! <!]])
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [omion.codemirror :as cm]))

(def state (atom nil))

(defmulti handle :type)

(defmethod handle :codemirror/value-poke [message]
  (let [cm-value (cljs.reader/read-string (:value message))]
    (swap! state #(assoc % :scratchpad cm-value))))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (html [:div
              [:div
                [:h1 "My scratchpad"] [:p "ctrl+enter to update"]
                (cm/markup "bot-mirror" [:p "My scratchings"])]
              [:div.scratchpad (:scratchpad @state)]]))
    om/IDidMount
    (did-mount [this]
      (let [ch (chan)]
        (cm/init! "bot-mirror" ch)
        (go-loop [message (<! ch)]
          (recur (do (handle message)
                   (om/refresh! owner)
                   (<! ch))))))))

(om/root widget {:not :used} {:target (. js/document (getElementById "om-application-root"))})
