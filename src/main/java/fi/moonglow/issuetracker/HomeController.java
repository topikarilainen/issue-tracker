package fi.moonglow.issuetracker;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
    
    // Dependency managed by constructor injection
    private final IssueService issueService;

    // Implicitly @Autowired as the only constructor method
    public HomeController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/status")
    public StatusResponse getStatus() {
        return new StatusResponse("Service running");
    }
    
    @GetMapping("/issues")
    public List<Issue> getIssuesByProjectAbbreviation(@RequestParam String projectAbbreviation) {
        return issueService.getIssuesByProjectAbbreviation(projectAbbreviation);
    }
    

}
