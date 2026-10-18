.PHONY: install run-1 run-2 clean test

install:
	./mvnw clean install

run-1:
	./mvnw -pl service-1 spring-boot:run

run-2:
	./mvnw -pl service-2 spring-boot:run

clean:
	./mvnw clean

test:
	./mvnw test
