(ns omion.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:use            [cljs.core.async :only [chan  >! <!]])
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [omion.codemirror :as cm]))

(defmulti handle :type)

(defmethod handle :codemirror/value-poke [message]
  (let [bot (cljs.reader/read-string (:value message))]
    (.log js/console (str "got new value from codemirror: " (pr-str bot)))))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (html [:div [:h1 "My scratchboard"] (cm/markup "bot-mirror" {:anykey :anyval})]))
    om/IDidMount
    (did-mount [this]
      (let [ch (chan)]
        (cm/init! "bot-mirror" ch)
        (go-loop [message (<! ch)]
          (recur (do (handle message)
                     (<! ch))))))))

(om/root widget {:not :used} {:target (. js/document (getElementById "om-application-root"))})
