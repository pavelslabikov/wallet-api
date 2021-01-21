FROM maven:latest
COPY . /usr/src/wallet-api/
WORKDIR /usr/src/wallet-api/
EXPOSE 8080
RUN ["mvn", "install"]
ENTRYPOINT ["./entrypoint.sh"]
CMD ["wallet-api"]