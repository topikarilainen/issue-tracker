package fi.moonglow.issuetracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    @Override
    public List<Issue> getIssues(Project project) {
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
        // TODO implement
        Issue dummyIssue = new Issue(IssueConstants.FEATURE, 3, "Placeholder issue",
        "Placeholder issue generated in service", null, null, IssueConstants.IN_PROGRESS, null);
        List<Issue> list = new ArrayList<>();
        list.add(dummyIssue);
        return list;
    }


}
