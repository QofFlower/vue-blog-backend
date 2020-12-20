FROM java:8

EXPOSE 8130

#将本地文件夹挂载到当前容器
VOLUME  /tmp

ADD oauth2-server-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Dfile.encoding=UTF-8", "-jar", "/app.jar"]
