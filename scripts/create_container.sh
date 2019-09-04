#!/bin/sh
docker run --name game_app -d -p 12001:8080 docker.io/shreys/gameoflife:${GO_PIPELINE_COUNTER}
echo "Please verify running application here: http://192.168.56.104:12001/gameoflife"