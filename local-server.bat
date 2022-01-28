echo "> todayworker-app docker image build(for windows)"

call ./gradlew clean bootBuildImage

echo "> docker-compose up"

call docker-compose -f ./dockerized/docker-compose.yml up -d
