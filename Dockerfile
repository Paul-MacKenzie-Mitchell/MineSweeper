FROM maven
Copy . /app
WORKDIR /app
CMD maven build