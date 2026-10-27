build-and-run:
	mvn clean package
	cp $(PWD)/target/spring-mvc-lab.war $(CATALINA_HOME)/webapps/spring-mvc-lab.war
	bash "$(CATALINA_HOME)/bin/catalina.sh" run
