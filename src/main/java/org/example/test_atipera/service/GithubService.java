package org.example.test_atipera.service;

import org.example.test_atipera.exception.UserNotFoundException;
import org.example.test_atipera.model.RepositoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Collectors;

@Service
public class GithubService {

    private final RestTemplate restTemplate;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RepositoryResponse> getRepositories(String username) {
        try {
            GithubRepo[] repos = restTemplate.getForObject(
                    "https://api.github.com/users/" + username + "/repos",
                    GithubRepo[].class
            );

            if (repos == null) throw new UserNotFoundException("User not found");

            return Arrays.stream(repos)
                    .filter(repo -> !repo.isFork())
                    .map(repo -> {
                        Branch[] branches = restTemplate.getForObject(
                                "https://api.github.com/repos/" + username + "/" + repo.getName() + "/branches",
                                Branch[].class
                        );

                        assert branches != null;
                        List<RepositoryResponse.Branch> branchList = Arrays.stream(branches)
                                .map(b -> new RepositoryResponse.Branch(b.getName(), b.getCommit().getSha()))
                                .collect(Collectors.toList());

                        return new RepositoryResponse(repo.getName(), repo.getOwner().getLogin(), branchList);
                    }).collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("User not found");
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GithubRepo {
        private String name;
        private boolean fork;
        private Owner owner;

        public String getName() {
            return name;
        }

        public boolean isFork() {
            return fork;
        }

        public Owner getOwner() {
            return owner;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Owner {
            private String login;

            public String getLogin() {
                return login;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Branch {
        private String name;
        private Commit commit;

        public String getName() {
            return name;
        }

        public Commit getCommit() {
            return commit;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Commit {
            private String sha;

            public String getSha() {
                return sha;
            }
        }
    }
}
