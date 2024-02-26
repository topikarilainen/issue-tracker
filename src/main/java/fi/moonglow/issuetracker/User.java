package fi.moonglow.issuetracker;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_user")
public class User {
    private @Id @GeneratedValue Long id;
    @NonNull
    @NaturalId
    private String username;
    private String fullName;
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<Issue> createdIssues;
    @OneToMany(mappedBy = "assignee")
    @JsonIgnore
    private List<Issue> assignedIssues;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Project> projects;

    public User() {
        this.username = "";
    }

    public User(String username, String fullName, List<Issue> createdIssues, List<Issue> assignedIssues,
            Set<Project> projects) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        this.username = username;
        this.fullName = fullName;
        this.createdIssues = createdIssues;
        this.assignedIssues = assignedIssues;
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(username, other.getUsername());
    }

    @Override
    public String toString() {
        int createdIssueCount = createdIssues != null ? createdIssues.size() : 0;
        int assignedIssueCount = assignedIssues != null ? assignedIssues.size() : 0;
        String projectsStr = "";
        if (projects != null) {
            projectsStr = "[" + projects.stream().map(Project::getAbbreviation).collect(Collectors.joining(", ")) + "]";
        }
        return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", created issues: "
                + createdIssueCount + ", assignedIssues: " + assignedIssueCount + ", projects=" + projectsStr + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Issue> getCreatedIssues() {
        return createdIssues;
    }

    public void setCreatedIssues(List<Issue> createdIssues) {
        this.createdIssues = createdIssues;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}
