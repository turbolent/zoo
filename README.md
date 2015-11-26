# zoo

Attach a JavaScript (Nashorn) REPL to a JVM.

Consists of an agent and a loader. The agent is injected into the JVM, starts a web server,
creates a Nashorn JavaScript engine, and evaluates incoming requests. The loader injects 
the agent into a JVM using the Attach API, launches a console, sends entered JavaScript 
code to the agent's web server, and prints responses.


## Installation

Build the agent and the loader:

```sh
$ mvn -f pom.loader.xml compile assembly:single
$ mvn -f pom.agent.xml compile assembly:single
```

## Usage

Find the process ID of the running JVM:

```sh
$ jps
```

Launch the REPL:

  - `<port>` is a free port at which the agent's web server will be listening on
  - `<PID>` is the process ID of the JVM

```sh
$ ./zoo.sh <port> <PID>
```
