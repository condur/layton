
(set-env!
 :source-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.9.0-alpha19"]
                 [aleph "0.4.3"]
                 [bidi "2.1.2"]
                 [yada/lean "1.2.8"]
                 [hiccup "1.0.5"]
                 [environ "1.1.0"]
                 [cheshire "5.8.0"]
                 [ring/ring-codec "1.0.1"]])


(deftask dev
  "Profile setup for development.	Starting the repl with the dev profile...boot dev repl"
  []
  (println "Welcome to boot - DEV profile running")
  (set-env!
   :init-ns 'user
   :source-paths #(into % ["test"])
   :dependencies #(into % '[[org.clojure/tools.namespace "0.2.11"]
                            [proto-repl "0.3.1"]
                            [proto-repl-charts "0.3.2"]
                            [org.clojure/test.check "0.9.0" :scope "test"]]))

  ;; Makes clojure.tools.namespace.repl work per https://github.com/boot-clj/boot/wiki/Repl-reloading
  (require 'clojure.tools.namespace.repl)
  (eval '(apply clojure.tools.namespace.repl/set-refresh-dirs
           (get-env :directories)))

  identity)

;;Load the main namespace that is needed for "run" task
(require 'layton.core)

(deftask run
  "Profile setup for running the project from command line ... boot run"
  []
  ;; Run the main method from lauton.core
  (with-pass-thru _
    (layton.core/-main)))
