sleep 45

java -jar /home/app/PostService.jar \
    --spring.data.mongodb.host=$MONGO \
    --server.ssl.key-store-password=$KEY_STORE_PASSWORD \
    --server.ssl.key-password=$KEY_PASSWORD \
    --server.jwt.secret=$JWT_SECRET
