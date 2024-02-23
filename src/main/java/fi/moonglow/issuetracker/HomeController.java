package fi.moonglow.issuetracker;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
    
    // Dependency managed by constructor injection
    private final IssueService issueService;
    private final ProjectService projectService;

    // Implicitly @Autowired as the only constructor method
    public HomeController(IssueService issueService, ProjectService projectService) {
        this.issueService = issueService;
        this.projectService = projectService;
    }

    @GetMapping("/status")
    public StatusResponse getStatus() {
        return new StatusResponse("Service running");
    }
    
    @GetMapping("/issues")
    public List<Issue> getIssuesByProjectAbbreviation(@RequestParam String projectAbbreviation) {
        return issueService.getIssuesByProjectAbbreviation(projectAbbreviation);
    }
    
    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }
    
}
