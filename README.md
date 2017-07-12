# brooklyn-webapp-plugin
Example of how to add a webapp to the Brooklyn karaf server

Build the project by running:
```
mvn clean install
```

Add the feature to karaf (e.g. by using `./bin/client`) using the commands:
```
feature:repo-add mvn:io.cloudsoft.brooklyn/web-plugin-feature/1.0.0-SNAPSHOT/xml/features
feature:install brooklyn-web-plugin-example
```

You should now be able to reach the new endpoint:
```
ENTITY_ID=ei8ep32ggl
curl -u user:password http://localhost:8081/v1/plugin/${ENTITY_ID}
```

