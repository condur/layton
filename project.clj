(defproject layton "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha19"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]

                 [aleph "0.4.3"]

                 [compojure "1.5.1"]]
  :main ^:skip-aot layton.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev     {:plugins        []
                       :resource-paths ["resources"]
                       :dependencies   [[proto-repl "0.3.1"]]}}
  :jvm-opts ["-server"])
