FROM frolvlad/alpine-oraclejdk8:slim

COPY /build/libs/app.jar /opt/app/lib/

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/app/lib/app.jar"]

EXPOSE 8888