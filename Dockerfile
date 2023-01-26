FROM swr.cn-east-3.myhuaweicloud.com/upuphone-cloudplatform/maven-upuphone:3.8.3 as builder
COPY . /build/
WORKDIR /build
RUN mvn clean -U -f ./pom.xml clean package -Dmaven.test.skip=true

FROM swr.cn-east-3.myhuaweicloud.com/upuphone-cloudplatform/openjdk:1.8
MAINTAINER Upuphone
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
COPY --from=builder /build/cloud-authority-web/target/cloud-authority-web.jar  /app.jar
#COPY --from=builder /build/cloud-demo-web/target/cloud-demo-web.jar  /app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT java ${JAVA_OPTS} -jar -Dlog.path=logs/${POD_NAME} /app.jar