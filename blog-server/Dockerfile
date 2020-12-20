FROM java:8

EXPOSE 8100

#将本地文件夹挂载到当前容器
VOLUME  /tmp

ADD blog-server-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'

ENTRYPOINT ["JAVA", "-jar", "/app.jar"]