package com.example.job_portal.repository;

import com.example.job_portal.model.JobPost;

import java.util.List;

public interface ISearchRepository {

    List<JobPost> findByText(String keyword);
}
