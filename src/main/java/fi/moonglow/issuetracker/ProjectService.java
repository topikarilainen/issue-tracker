package fi.moonglow.issuetracker;

import java.util.List;

public interface ProjectService {

    /**
     * Returns all projects.
     * 
     * @return All projects
     */
    public List<Project> getAllProjects();

}
