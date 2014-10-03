# omion
 [Om](https://github.com/swannodette/om) for fun and profit!
 Currently, this is a little scratchpad where you can build an html page.

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
