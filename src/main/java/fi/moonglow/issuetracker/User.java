package fi.moonglow.issuetracker;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String username;
    private String fullName;
    @OneToMany(mappedBy = "creator")
    private List<Issue> createdIssues;
    @OneToMany(mappedBy = "assignee")
    private List<Issue> assignedIssues;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Project> projects;

    public User() {}
    
    public User(String username, String fullName, List<Issue> createdIssues, List<Issue> assignedIssues,
            Set<Project> projects) {
        this.username = username;
        this.fullName = fullName;
        this.createdIssues = createdIssues;
        this.assignedIssues = assignedIssues;
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((createdIssues == null) ? 0 : createdIssues.hashCode());
        result = prime * result + ((assignedIssues == null) ? 0 : assignedIssues.hashCode());
        result = prime * result + ((projects == null) ? 0 : projects.hashCode());
        return result;
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (createdIssues == null) {
            if (other.createdIssues != null)
                return false;
        } else if (!createdIssues.equals(other.createdIssues))
            return false;
        if (assignedIssues == null) {
            if (other.assignedIssues != null)
                return false;
        } else if (!assignedIssues.equals(other.assignedIssues))
            return false;
        if (projects == null) {
            if (other.projects != null)
                return false;
        } else if (!projects.equals(other.projects))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", createdIssues="
                + createdIssues + ", assignedIssues=" + assignedIssues + ", projects=" + projects + "]";
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

    // For managing the bidirectional relationship
    public void addProject(Project project) {
        this.projects.add(project);
        project.getUsers().add(this);
    }

    // For managing the bidirectional relationship
    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getUsers().remove(this);
    }

}
