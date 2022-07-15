# corsproxy

## Build
run inside the project directory:

mvn clean install

## Run
run inside the project directory:

cd target
java -jar corsproxy-1.0-SNAPSHOT-exec.jar

## Test
run TestCORS in a JUnit environment, e.g. within your IDE

## Access Running status
in browser, open: 
http://localhost:8090/

(ports can be modified in the configuration section for the tomee-maven-plugin in pom.xml)

## Containerise
run inside the project directory:

cd containerise
sh ./containerise.sh

## Access containerised status
in browser, open: 
http://localhost:8093/

(the port for exposing the service can be modified in the containerise.sh, using the parameter -p, which specifies which container-internal port shall be mapped to which port of the host machine: HOST_PORT:INTERNAL_PORT. Note that INTERNAL_PORT needs to coincide with the port for which tomee is configured. If this port is changed, the EXPOSE statement in Dockerfile needs to be modified, as well) 

## Sample Request using fetch()

This shows how the CORS service can be accessed from a browser-side running application in case it needs CORS access to a resource the provider of which does not foresee this possibility:

        fetch("http://localhost:8090/api/cors", {
            // the PUT method is expected to be used for accessing the proxy service
            method: "PUT",
            // this specifies the resource and method to be accessed via CORS - note that the json object needs to be stringified
            body: JSON.stringify({
                method: "GET",
                url: "https://www.visitberlin.de/en/top-museums"
            }),
            // the content type header is required for specifying that we send a JSON object
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(resp => resp.text())
            .then(txt => console.log("got museum overview: ", txt));
