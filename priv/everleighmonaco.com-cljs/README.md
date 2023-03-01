# EverleighMonaco-Clojure

The EverleighMonaco.com website source code.

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

   $ lein deps
   $ lein lein compile app
   $ npx shadow-cljs watch app

We're using lein-npm and lein-shadow meaning recompiling after updating npm deps requires removing package.json and shadow-cljs.edn:

    $ rm package.json
    $ rm shadow-cljs.edn
    $ lein compile ; creates an updated shadow-cljs.end

To compile CSS once:

    $ lein garden once

To compile CSS while developing:

    $ lein garden auto


## License [&#x219F;](#table-of-contents)

Everleigh Monaco is released under the [GNU Affero General Public License v3.0](https://github.com/EverleighMonaco/EverleighMonaco/blob/main/LICENSE)
