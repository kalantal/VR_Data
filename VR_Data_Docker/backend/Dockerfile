FROM openjdk:8-jdk-alpine
RUN apk update && apk add curl && apk add git

#Add Maven
ENV MAVEN_VERSION 3.3.9

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

VOLUME /root/.m2

#Add Kotlin
RUN apk add --no-cache bash && \
    apk add --no-cache -t build-dependencies wget && \
    cd /usr/lib && \
    wget https://github.com/JetBrains/kotlin/releases/download/v1.2-rc1/kotlin-compiler-1.2.0-rc-39.zip && \
    unzip kotlin-compiler-*.zip && \
    rm kotlin-compiler-*.zip && \
    rm kotlinc/bin/*.bat && \
    apk del --no-cache build-dependencies

ENV PATH $PATH:/usr/lib/kotlinc/bin

#Run Application
WORKDIR /usr
ADD backend/ backend/
ADD start.sh backend/

#Create home directory
RUN mkdir /usr/share/home
EXPOSE 8010
ENV SDVR_ES_HOST es
ENV SDVR_ES_PORT 9300

WORKDIR /usr/backend

#Pull all dependencies before building docker image
RUN mvn dependency:go-offline 
CMD [ "sh", "/usr/backend/start.sh"]
