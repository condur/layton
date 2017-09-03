(defproject layton "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha19"]
                 [aleph "0.4.3"]
                 [bidi "2.1.2"]
                 [yada/lean "1.2.8"]
                 [hiccup "1.0.5"]
                 [environ "1.1.0"]
                 [cheshire "5.8.0"]
                 [ring/ring-codec "1.0.1"]]

  :main ^:skip-aot layton.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev     {:plugins        []
                       :resource-paths ["resources"]
                       :dependencies   [[proto-repl "0.3.1"]]}}
  :jvm-opts ["-server"])
