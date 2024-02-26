package fi.moonglow.issuetracker;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Issue {
    private @Id @GeneratedValue Long id;
    private String type;
    private Integer priority;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    private String status;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    public Issue() {
    }

    public Issue(String type, Integer priority, String title, String description, User creator, User assignee,
            String status, Project project) {
        this.type = type;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.assignee = assignee;
        this.status = status;
        this.project = project;
    }

    /**
     * The natural key is a combination of the project abbreviation and the issue
     * id, e.g. "SIA-42". Ideally, we would calculate the hash from these, but the
     * issue id is not available until the issue is persisted (it is generated at
     * persist time). Therefore the id is not guaranteed to be available for use in
     * hash code calculation. More specifically, it changes from null to a number
     * after persisting, which is a problem if it the issue is added to a set before
     * the id is generated.
     * 
     * In this case we simply return a constant. This implementation is of course
     * inefficient for large sets. If a more efficient alternative was needed, we
     * could implement our own issue number generator, use a separate field for the
     * issue number, and create the hash code with Objects.hash(project,
     * issueNumber).
     */
    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        String creatorStr = creator != null ? creator.getUsername() : "";
        String assigneeStr = assignee != null ? assignee.getUsername() : "";
        String projectStr = project != null ? project.getAbbreviation() : "";
        return "Issue [id=" + id + ", type=" + type + ", priority=" + priority + ", title=" + title + ", description="
                + description + ", creator=" + creatorStr + ", assignee=" + assigneeStr + ", status=" + status
                + ", project=" + projectStr + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // Show this in place of a project object in the serialized JSON representation.
    // Consider refactoring this to use separate DTOs to keep entity classes
    // uncoupled from serialization concerns.
    @JsonProperty("projectAbbreviation")
    public String getProjectAbbreviation() {
        return project != null ? project.getAbbreviation() : null;
    }

}
