package com.example.job_portal.controller;

import com.example.job_portal.repository.JobPostRepository;
import com.example.job_portal.model.JobPost;
import com.example.job_portal.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class JobPostController {

    @Autowired
    private JobPostRepository repo;

    @Autowired
    private SearchRepository searchRepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobPosts() {
        return repo.findAll();
    }

    @GetMapping("/jobPosts/{keyword}")
    public List<JobPost> search(@PathVariable String keyword) {
        return searchRepo.findByText(keyword);
    }

    @PostMapping(value = "/addJobPost", consumes = "application/json")
    public JobPost addJobPost(@RequestBody JobPost jobPost) {
        return repo.save(jobPost);
    }

}
