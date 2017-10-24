FROM openjdk:8-alpine
LABEL layton.maintainer="dima@condur.ca"

ENV BOOT_VERSION=2.7.2
ENV BOOT_INSTALL=/usr/local/bin/
ENV BOOT_CLOJURE_NAME=org.clojure/clojure
ENV BOOT_CLOJURE_VERSION=1.9.0-beta2

RUN apk add --update bash openssl && rm -rf /var/cache/apk/*

# NOTE: BOOT_VERSION tells the boot.sh script which version of boot to install
# on its first run. We always download version 2.7.2 of boot.sh because it is
# just the installer script. When/if the boot project releases a new installer
# script we will update this to use it.
RUN mkdir -p $BOOT_INSTALL \
  && wget -q https://github.com/boot-clj/boot-bin/releases/download/2.7.2/boot.sh \
  && echo "Comparing installer checksum..." \
  && echo "f717ef381f2863a4cad47bf0dcc61e923b3d2afb *boot.sh" | sha1sum -c - \
  && mv boot.sh $BOOT_INSTALL/boot \
  && chmod 0755 $BOOT_INSTALL/boot

ENV PATH=$PATH:$BOOT_INSTALL
ENV BOOT_AS_ROOT=yes

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app

RUN boot build

EXPOSE 3000

CMD ["java", "-jar", "target/layton.jar"]
