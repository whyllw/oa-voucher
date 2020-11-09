ARG IMG_BASE=openjdk:8-jre-alpine
FROM ${IMG_BASE}
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
    && apk add --no-cache bash wget curl ca-certificates tzdata jq\
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 设置时区。这对于日志、调用链等功能能否在 TSF 控制台被检索到非常重要。
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
# Java 程序及起始路径设为 /data/tsf_default/，是使用「默认日志配置项」的必要条件。
ENV workdir /data/tsf_default/
ENV jar ./target/voucher-oss-server-1.0-SNAPSHOT.jar
COPY ${jar} ${workdir}
WORKDIR ${workdir}
# JAVA_OPTS 环境变量的值为部署组的 JVM 启动参数，在运行时 bash 替换。使用 exec 以使 java 程序可以接收 SIGTERM 信号。
CMD ["sh", "-ec", "exec java ${JAVA_OPTS} -jar ${workdir}voucher-oss-server-1.0-SNAPSHOT.jar 2>&1"]
