echo "> todayworker-app docker image build (for OSX, Linux)"

./gradlew clean bootBuildImage

echo "> docker-compose up"

docker-compose -f ./dockerized/docker-compose.yml up -d