(defproject omion "0.1.0-SNAPSHOT"
  :description "Om hacks for fun."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2277"]
                 [org.clojure/core.async "0.1.278.0-76b25b-alpha"]
                 [om "0.5.3"]
                 [sablono "0.2.14"]
                 [domina "1.0.2"]]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:output-to "dev/out/build/main.js"
                                   :output-dir "dev/out"
                                   :optimizations :none
                                   :source-map true}}]}
  :profiles {:dev {:plugins [[lein-cljsbuild "1.0.3"]]}})
