cp ./../target/corsproxy-1.0-SNAPSHOT-exec.jar .

docker build -t org.dieschnittstelle.ess/corsproxy  .

docker stop corsproxy
docker rm corsproxy

docker run -itd -p 8093:8090 --name corsproxy org.dieschnittstelle.ess/corsproxy


