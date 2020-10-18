FROM java:8
MAINTAINER hackerstudy <1791133899@qq.com>
ENV TZ='Asia/Shanghai'
WORKDIR /home/server/
ADD target/admin-client.jar /home/server/app.jar
VOLUME /home/server
ENV JAVA_OPTS="-Dfile.encoding=UTF-8"
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/server/app.jar" ]