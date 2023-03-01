![Project Logo][logo]

# Welcome to Everleigh Monaco

## What is Everleigh Monaco?

Everleigh Monaco is real estate platform made of an Erlang/OTP application server, an API, and clients.

Everleigh Monaco is currently experimental, so put on your hardhat and expect lots and lots of changes.

The application server is written in Erlang and Lisp Flavoured Erlang. The ReactJS web front-end client is written in Clojure and ClojureScript. The cross-platform mobile client is also written in Clojure, leveraging ClojureDart and the Flutter framework.

Requirements (experimental)
* Erlang/OTP 24
* Rebar3
* Clojure 1.11
* Leiningen
* Java 16
* NodeJS v12


## Setting up

Make a local copy of the repo and create a log directory.

```
$ git clone https://github.com/EverleighMonaco/EverleighMonaco.git
$ cd EverleighMonaco
$ mkdir log
```

Get the deps and compile.

```
$ rebar3 deps
$ cd priv/everleighmonaco.com-cljs
$ lein deps

# compile the css
$ lein garden once

# compile the front end
$ npx shadow-cljs compile app
```

## Acknoledgements

[Robert Virding](https://github.com/rvirding), [Duncan McGreggor](https://github.com/oubiwann), and the late [Joe Armstrong](https://en.wikipedia.org/wiki/Joe_Armstrong_(programmer)), along with the entire LFE community. You all sold me on the power of OTP, Erlang and LFE. Also The countless LFE libraries that Duncan made in particular [(we're using a bunch)](https://github.com/lfex).

re:

* [LFE: A Real Lisp in the Erlang Ecosystem by Robert Virding](https://www.youtube.com/watch?v=x2ysisqgd2g)

* [Rackspace takes a look at the ERLANG programming language for distributed computing](https://www.youtube.com/watch?v=u41GEwIq2mE)

## License [&#x219F;](#table-of-contents)

Everleigh Monaco is released under the [GNU Affero General Public License v3.0](https://github.com/EverleighMonaco/EverleighMonaco/blob/main/LICENSE)

<!-- Named page links below: /-->

[logo]: https://everleighmonaco.com/favicon.png
