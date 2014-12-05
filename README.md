# omion
[Om](https://github.com/swannodette/om) for fun and profit!
Compare with [reapoc](https://github.com/Intelliplan/reapoc), which uses a similar technology.
Currently, this is a little scratchpad where you can build an html page.

 The project demonstrates:
 * basic ClojureScript development (sourcemaps, auto compilation etc.)
 * ClojureScript integration with javascript libraries (bootstrap and codemirror)
 * data model to HTML rendering
 * client side HTML generation
 * The application state model in Om

# Get started developing

 * install Java (on linux)
 
 ```bash
 sudo yum -y install java-1.7.0-openjdk-devel
 export JAVA_HOME=/etc/alternatives/jre
 export PATH=$PATH:$JAVA_HOME/bin
 ```
 * install [leiningen](http://leiningen.org/)
 * `lein cljsbuild auto` (from git repo root)
 * edit the source in `src/omion`
 * load `dev/index.html` in browser
