package fi.moonglow.issuetracker;

import java.util.List;

public interface IssueService {

    /**
     * Returns all the issues in a project.
     * 
     * @param project A project entity
     * @return All issues in the project
     */
    public List<Issue> getIssuesByProject(Project project);

    /**
     * Returns all the issues in a project.
     * 
     * @param projectId The id of a project entity
     * @return All issues in the project
     */
    public List<Issue> getIssuesByProjectId(Long projectId);

    /**
     * Returns all the issues in a project.
     * 
     * @param projectId The abbreviation of a project entity
     * @return All issues in the project
     */
    public List<Issue> getIssuesByProjectAbbreviation(String projectAbbreviation);

}
