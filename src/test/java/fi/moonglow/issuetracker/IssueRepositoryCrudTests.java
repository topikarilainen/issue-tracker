package fi.moonglow.issuetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class IssueRepositoryCrudTests {

    // Field injection is OK in tests. (Outside of tests, prefer constructor injection.)
    @Autowired
    IssueRepository issueRepository;

    @Test
    void smokeTest() {
        // Test that tests are able to run
        int i = 0;
        assertEquals(0, i);
    }

    @Test
    void testCreateIssue() {

        // Check that table is empty at start
        assertEquals(0, issueRepository.count());

        // Create issue
        final String issueTitle = "Dark Mode Implementation";
        Issue issueToCreate = new Issue(IssueConstants.FEATURE, 3, issueTitle,
                "Implement dark mode feature for better user experience", null, null, IssueConstants.IN_PROGRESS, null);
        Issue createdIssue = issueRepository.save(issueToCreate);
        Long id = createdIssue.getId();
        assertNotNull(id);

        // Verify creation by fetching issue from database and checking the title
        Optional<Issue> fetchedIssue = issueRepository.findById(id);
        fetchedIssue.ifPresentOrElse(
                issue -> {
                    assertEquals(issue.getTitle(), issueTitle);
                },
                () -> {
                    fail("Fetched issue is empty");
                });

        // Check that table has a record
        assertEquals(1, issueRepository.count());

    }

    @Test
    void testCreateAndUpdateIssue() {

        // Create issue
        final String issueTitle = "Dark Mode Implementation";
        Issue issueToCreate = new Issue(IssueConstants.FEATURE, 3, issueTitle,
                "Implement dark mode feature for better user experience", null, null, IssueConstants.IN_PROGRESS, null);
        Issue createdIssue = issueRepository.save(issueToCreate);
        Long id = createdIssue.getId();
        assertNotNull(id);

        // Fetch issue from database and change the description
        Optional<Issue> fetchedIssue = issueRepository.findById(id);
        fetchedIssue.ifPresentOrElse(
                issue -> {
                    issue.setDescription(issue.getDescription() + " Description updated");
                    // No need to call save(), because @DataJpaTest makes all methods in this class
                    // @Transactional
                },
                () -> {
                    fail("Fetched issue is empty");
                });

        // Fetch issue again and verify that the description changed
        Optional<Issue> updatedAndFetchedIssue = issueRepository.findById(id);
        updatedAndFetchedIssue.ifPresentOrElse(
                issue -> {
                    assertTrue(issue.getDescription().contains(" Description updated"));
                },
                () -> {
                    fail("Fetched issue is empty");
                });
    }

    @Test
    void testCreateAndDeleteIssue() {

        // Check that repository is empty at start
        assertEquals(0, issueRepository.count());

        // Create issue
        final String issueTitle = "Dark Mode Implementation";
        Issue issueToCreate = new Issue(IssueConstants.FEATURE, 3, issueTitle,
                "Implement dark mode feature for better user experience", null, null, IssueConstants.IN_PROGRESS, null);
        Issue createdIssue = issueRepository.save(issueToCreate);
        Long id = createdIssue.getId();
        assertNotNull(id);

        // Verify creation by fetching issue from database
        Optional<Issue> fetchedIssue = issueRepository.findById(id);
        assertTrue(fetchedIssue.isPresent());
        assertEquals(1, issueRepository.count());

        // Delete issue from database
        issueRepository.deleteById(id);

        // Verify that issue has been deleted
        Optional<Issue> fetchedIssueAfterDelete = issueRepository.findById(id);
        assertFalse(fetchedIssueAfterDelete.isPresent());
        assertEquals(0, issueRepository.count());
    }
}
