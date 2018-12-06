sleep 60

java -jar /home/app/PostService-$SERVICE_VERSION.jar \
    --spring.data.mongodb.host=$MONGO
