(set-env!
 :source-paths #(into % ["src" "test"])
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.9.0-beta2"]
                 [aleph "0.4.3"]
                 [bidi "2.1.2"]
                 [yada/lean "1.2.9"]
                 [hiccup "1.0.5"]
                 [aero "1.1.2"]
                 [cheshire "5.8.0"]
                 [ring/ring-codec "1.0.1"]

                 ;REPL
                 [org.clojure/tools.namespace "0.3.0-alpha4"]
                 [proto-repl "0.3.1"]
                 [proto-repl-charts "0.3.2"]

                 ;TEST
                 [criterium "0.4.4" :scope "test"]
                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [metosin/boot-alt-test  "0.3.2" :scope "test"]])
(require
  '[metosin.boot-alt-test  :refer [alt-test]]
  'layton.core)

(deftask dev
  "Profile setup for development.
   Set the following on proto-repl boot arguments: dev repl --server wait
   Starting the repl with the dev profile...boot dev repl"
  []
  (println "Welcome to boot - DEV profile running")

  ;; Makes clojure.tools.namespace.repl work per https://github.com/boot-clj/boot/wiki/Repl-reloading
  (require 'clojure.tools.namespace.repl)
  (eval '(apply clojure.tools.namespace.repl/set-refresh-dirs
                (get-env :directories)))

  identity)

(deftask autotest
  "Profile setul for auto test development."
  []
  (println "Welcome to boot - AUTOTEST profile running")
  (comp
    (watch)
    (alt-test :report 'eftest.report.pretty/report)))

(deftask run
  "Profile setup for running the project from command line ... boot run"
  []
  ;; Run the main method from lauton.core
  (with-pass-thru _
    (layton.core/-main)))

(deftask build
  "Builds an uberjar of this project that can be run with java -jar"
  []
  (comp
   (aot :namespace #{'layton.core})
   (uber)
   (jar :file "layton.jar" :main 'layton.core)
   (sift :include #{#"layton.jar"})
   (target)))
