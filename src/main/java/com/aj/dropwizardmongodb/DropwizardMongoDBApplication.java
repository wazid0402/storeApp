package com.aj.dropwizardmongodb;

import com.aj.dropwizardmongodb.resource.DropwizardMongoDBHealthCheckResource;
import com.aj.dropwizardmongodb.resource.UserResource;
import com.aj.dropwizardmongodb.service.MongoService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DropwizardMongoDBApplication extends Application<DropwizardMongoDBConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardMongoDBApplication.class);

    public static void main(String[] args) throws Exception {
        new DropwizardMongoDBApplication().run("server", "dropwizardmongodb.yml");
    }

    @Override
    public void initialize(Bootstrap<DropwizardMongoDBConfiguration> b) {
    }

    @Override
    public void run(DropwizardMongoDBConfiguration config, Environment env)
            throws Exception {
        MongoClient mongoClient = new MongoClient(config.getMongoHost(), config.getMongoPort());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        env.lifecycle().manage(mongoManaged);
        MongoDatabase db = mongoClient.getDatabase(config.getMongoDB());
        MongoCollection<Document> collection = db.getCollection(config.getCollectionName());
        logger.info("Registering RESTful API resources");
        env.jersey().register(new UserResource(collection, new MongoService()));
        env.healthChecks().register("DropwizardMongoDBHealthCheck",
                new DropwizardMongoDBHealthCheckResource(mongoClient));
    }
}
