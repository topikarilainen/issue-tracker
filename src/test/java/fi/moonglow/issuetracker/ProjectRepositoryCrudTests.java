package fi.moonglow.issuetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProjectRepositoryCrudTests {

    // Field injection is OK in tests. (Outside of tests, prefer constructor
    // injection.)
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void smokeTest() {
        // Test that tests are able to run
        int i = 0;
        assertEquals(0, i);
    }

    @Test
    void testCreateProject() {

        // Check that table is empty at start
        assertEquals(0, projectRepository.count());

        // Create project
        final String projectName = "Sand Undulation Selector";
        Project projectToCreate = new Project(projectName, "SUS", null, null);
        Project createdProject = projectRepository.save(projectToCreate);
        Long id = createdProject.getId();
        assertNotNull(id);

        // Verify creation by fetching project from database and checking the title
        Optional<Project> fetchedProject = projectRepository.findById(id);
        fetchedProject.ifPresentOrElse(
                project -> {
                    assertEquals(project.getName(), projectName);
                },
                () -> {
                    fail("Fetched project is empty");
                });

        // Check that table has a record
        assertEquals(1, projectRepository.count());

    }

    @Test
    void testCreateProjectAndUser() {

        // Check that tables are empty at start
        assertEquals(0, projectRepository.count());
        assertEquals(0, userRepository.count());

        // Create project
        Project projectToCreate = new Project("Sand Undulation Selector", "SUS", new ArrayList<>(), new HashSet<>());
        Project createdProject = projectRepository.save(projectToCreate);
        Long projectId = createdProject.getId();
        assertNotNull(projectId);

        // Verify creation by fetching project from database and checking the title
        Optional<Project> fetchedProject = projectRepository.findById(projectId);
        fetchedProject.ifPresentOrElse(
                project -> {
                    assertEquals("Sand Undulation Selector", project.getName());
                },
                () -> {
                    fail("Fetched project is empty");
                });

        // Check that table has a record
        assertEquals(1, projectRepository.count());
        
        // Create user
        User userToCreate = new User("einsteal", "Albert Einstein", new ArrayList<>(), new ArrayList<>(), new HashSet<>());
        User createdUser = userRepository.save(userToCreate);
        Long userId = createdUser.getId();
        assertNotNull(userId);

        // Verify creation by fetching user from database and checking the title
        Optional<User> fetchedUser = userRepository.findById(userId);
        fetchedUser.ifPresentOrElse(
                user -> {
                    assertEquals("einsteal", user.getUsername());
                },
                () -> {
                    fail("Fetched user is empty");
                });

        // Check that table has a record
        assertEquals(1, userRepository.count());

        // Add user to project
        Project p = fetchedProject.get();
        p.addUser(fetchedUser.get());
        projectRepository.save(p);

        // Verify that user has been added to project
        fetchedProject = projectRepository.findById(projectId);
        fetchedProject.ifPresentOrElse(
                project -> {
                    assertEquals("Sand Undulation Selector", project.getName());
                    assertEquals(1, project.getUsers().size());
                    project.getUsers().forEach(u -> {assertEquals("einsteal", u.getUsername());});
                },
                () -> {
                    fail("Fetched project is empty");
                });
    }

}
