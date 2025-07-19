package org.example.test_atipera.controller;

import org.example.test_atipera.model.RepositoryResponse;
import org.example.test_atipera.service.GithubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public List<RepositoryResponse> getRepos(@PathVariable String username) {
        return githubService.getRepositories(username);
    }
}