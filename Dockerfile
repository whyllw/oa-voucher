ARG IMG_BASE=openjdk:8-jre-alpine
FROM ${IMG_BASE}

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
    && apk add --no-cache bash wget curl ca-certificates tzdata jq\
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN apk add --no-cache tzdata bash ttf-dejavu fontconfig \
    && fc-cache --force
#  为了解决openjdk:8-jre-alpine不支持font的问题
COPY docker /docker

COPY voucher-core-server/target/*.jar /
COPY voucher-oss-server/target/*.jar /

RUN chmod +x /docker/docker-entrypoint.sh \
    && echo "${IMG_BASE} "
    
ENTRYPOINT ["/docker/docker-entrypoint.sh"]


EXPOSE 8051
EXPOSE 8052
