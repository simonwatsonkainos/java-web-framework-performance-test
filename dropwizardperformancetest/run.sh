echo "Killing previous running MySQL database containers..."
docker kill performance-test-mysql

echo "Starting new MySQL database container..."
./mysql-docker.sh
sleep 7

echo "Building dropwizard-performance-test jar file..."
mvn clean install

echo "Starting dropwizard-performance-test and running migrations (if required)..."
java -jar target/dropwizard-performance-test-1.0-SNAPSHOT.jar server config.yaml