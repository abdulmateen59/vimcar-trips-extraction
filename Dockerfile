FROM maven:3.6.3-jdk-8
COPY . /home/WORKSPACE
WORKDIR /home/WORKSPACE


# $ docker build -t vimcar . && docker run -it --rm vimcar /bin/bash 
# $ python3 main.py