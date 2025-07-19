package org.example.test_atipera.model;

import java.util.List;

public class RepositoryResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branches;

    public RepositoryResponse(String repositoryName, String ownerLogin, List<Branch> branches) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public static class Branch {
        private String name;
        private String lastCommitSha;

        public Branch(String name, String lastCommitSha) {
            this.name = name;
            this.lastCommitSha = lastCommitSha;
        }

        public String getName() {
            return name;
        }

        public String getLastCommitSha() {
            return lastCommitSha;
        }
    }
}
