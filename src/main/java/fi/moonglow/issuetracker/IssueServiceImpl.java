package fi.moonglow.issuetracker;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public IssueServiceImpl(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Issue> getIssuesByProject(Project project) {
        // TODO implement
        return Collections.emptyList();
    }

    @Override
    public List<Issue> getIssuesByProjectId(Long projectId) {
        // TODO implement
        return Collections.emptyList();
    }

    @Override
    public List<Issue> getIssuesByProjectAbbreviation(String projectAbbreviation) {
        return projectRepository.findIssuesByProjectAbbreviation(projectAbbreviation);
    }

}
