sleep 60

java -jar /home/app/PostService-$SERVICE_VERSION.jar \
    --spring.data.mongodb.host=$MONGO \
    --server.ssl.key-store-password=$KEY_STORE_PASSWORD \
    --server.ssl.key-password=$KEY_PASSWORD \
    --server.jwt.secret=$SECRET
