#!/bin/sh
echo $USER
/bin/docker stop game_app || true && /bin/docker rm game_app || true