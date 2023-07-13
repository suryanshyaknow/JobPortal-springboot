package com.example.job_portal.repository;

import com.example.job_portal.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobPostRepository extends MongoRepository<JobPost, String> {

}
