package com.example.job_portal.repository;

import com.example.job_portal.model.JobPost;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepository implements ISearchRepository {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<JobPost> findByText(String keyword) {

        final List<JobPost> jobPosts = new ArrayList<>();

        // Fetching database details...
        MongoDatabase db = mongoClient.getDatabase("demo");
        MongoCollection<Document> collection = db.getCollection("JobPosts");

        AggregateIterable<Document> result = collection.aggregate(
                Arrays.asList(new Document("$search",
                                new Document("index", "job_posts_idx")
                                        .append("text",
                                                new Document("query", keyword)
                                                        .append("path", Arrays.asList("profile", "desc", "techs")))),
                        new Document("$sort",
                                new Document("exp", -1L))));

        result.forEach(doc -> jobPosts.add(mongoConverter.read(JobPost.class, doc)));

        return jobPosts;
    }
}
