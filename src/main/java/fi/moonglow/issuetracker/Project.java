package fi.moonglow.issuetracker;

import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.NaturalId;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
    private @Id @GeneratedValue Long id;
    private String name;
    @NonNull
    @NaturalId
    private String abbreviation;
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Issue> issues;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_users", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "application_user_id"))
    private Set<User> users;

    public Project() {
        abbreviation = "";
    }

    public Project(String name, String abbreviation, List<Issue> issues, Set<User> users) {
        if (abbreviation == null) {
            throw new IllegalArgumentException("abbreviation cannot be null");
        }
        this.name = name;
        this.abbreviation = abbreviation;
        this.issues = issues;
        this.users = users;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(abbreviation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        return Objects.equals(abbreviation, other.getAbbreviation());
    }

    @Override
    public String toString() {
        int issueCount = issues.size();
        String usersStr = "";
        if (users != null) {
            usersStr = "[" + users.stream().map(User::getUsername).collect(Collectors.joining(", ")) + "]";
        }
        return "Project [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + ", issues: " + issueCount
                + ", users=" + usersStr + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        if (abbreviation == null) {
            throw new IllegalArgumentException("abbreviation cannot be null");
        }
        this.abbreviation = abbreviation;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // For managing the bidirectional relationship
    public void addUser(User user) {
        this.users.add(user);
        user.getProjects().add(this);
    }

    // For managing the bidirectional relationship
    public void removeUser(User user) {
        this.users.remove(user);
        user.getProjects().remove(this);
    }

}
