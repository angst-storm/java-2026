.PHONY: install run clean test

install:
	./mvnw clean install

run:
	./mvnw spring-boot:run

clean:
	./mvnw clean

test:
	./mvnw test
