# Brooklyn Web Plug-in

This project is an example of how to add an additional web API to the Brooklyn karaf server.

Build the project by running:

```
mvn clean install
```

The next step is to add the feature to karaf. You can start a new Brooklyn instance in the console using `./bin/karaf` 
or `./bin/client` to connect to an already running one. Then use the following commands to install the new feature:

```
feature:repo-add mvn:io.cloudsoft.brooklyn/web-plugin-feature/1.0.0-SNAPSHOT/xml/features
feature:install brooklyn-web-plugin-example
```

You should now be able to reach the new endpoint:

```
ENTITY_ID=ei8ep32ggl
curl -u user:password http://localhost:8081/v1/plugin/${ENTITY_ID}
```

Where `ENTITY_ID` specifies the id of an entity running in the Brooklyn instance.

You can then modify and extend the pattern used in [WebPluginApi.java](web-plugin-endpoint/src/main/java/io/cloudsoft/brooklyn/web/plugin/WebPluginApi.java) 
to produce an API which can use the Brooklyn [ManagementContext](https://brooklyn.apache.org/v/latest/concepts/lifecycle-managementcontext.html) to interact 
with the running Brooklyn server.