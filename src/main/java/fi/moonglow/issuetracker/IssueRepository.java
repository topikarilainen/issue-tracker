package fi.moonglow.issuetracker;

import org.springframework.data.jpa.repository.JpaRepository;



public interface IssueRepository extends JpaRepository<Issue, Long> {

}
