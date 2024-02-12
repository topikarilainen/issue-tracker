package fi.moonglow.issuetracker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p.issues FROM Project p WHERE p.abbreviation = :abbreviation")
    public List<Issue> findIssuesByProjectAbbreviation(@Param("abbreviation") String abbreviation);
}
