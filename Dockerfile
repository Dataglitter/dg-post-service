FROM java:8-jre-alpine

LABEL maintainer="Sangram Reddy <reddy.horcrux@gmail.com>"
LABEL application="dg-post-service"

COPY ./target/PostService-*.jar /home/app/

COPY ./scripts/button.sh /home/app/

RUN apk --no-cache add curl && chmod +x /home/app/button.sh

WORKDIR /home/app/

EXPOSE 8443 11011

CMD [ "sh", "/home/app/button.sh" ]
