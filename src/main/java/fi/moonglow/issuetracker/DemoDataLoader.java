package fi.moonglow.issuetracker;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoDataLoader implements CommandLineRunner {

        private final IssueRepository issueRepository;
        private final ProjectRepository projectRepository;
        private final UserRepository userRepository;

        public DemoDataLoader(IssueRepository issueRepository, ProjectRepository projectRepository,
                        UserRepository userRepository) {
                this.issueRepository = issueRepository;
                this.projectRepository = projectRepository;
                this.userRepository = userRepository;
        }

        @Override
        @Transactional
        public void run(String... args) {
                // Initializes the database with demo data.

                // Create users
                User mcducscr = new User("mcducscr", "Scrooge McDuck", new ArrayList<>(), new ArrayList<>(),
                                new HashSet<>());
                User messilio = new User("messilio", "Lionel Messi", new ArrayList<>(), new ArrayList<>(),
                                new HashSet<>());
                User swifttay = new User("swifttay", "Taylor Swift", new ArrayList<>(), new ArrayList<>(),
                                new HashSet<>());
                userRepository.save(mcducscr);
                userRepository.save(messilio);
                userRepository.save(swifttay);

                // Create projects
                Project enterpriseProject = new Project("SAP Integration for ACME", "SIA", new ArrayList<>(),
                                new HashSet<>());
                enterpriseProject.addUser(mcducscr);
                enterpriseProject.addUser(messilio);
                enterpriseProject.addUser(swifttay);
                Project webProject = new Project("Online Tea Shop", "OTS", new ArrayList<>(),
                                new HashSet<>());
                webProject.addUser(messilio);
                webProject.addUser(swifttay);

                projectRepository.save(enterpriseProject);
                projectRepository.save(webProject);

                // Create issues for SAP Integration For ACME
                issueRepository.save(
                                new Issue(IssueConstants.BUG, 3, "Bad color in error texts", "Should be red, is green.",
                                                mcducscr, messilio, IssueConstants.OPEN, enterpriseProject));
                issueRepository.save(
                                new Issue(IssueConstants.BUG, 3, "Bad color in done texts", "Should be green, is red.",
                                                mcducscr, messilio, IssueConstants.IN_PROGRESS, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 3, "Bad color in title texts",
                                "Should be black, is yellow.",
                                mcducscr, messilio, IssueConstants.CLOSED, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Does not start on Saturdays",
                                "Tried on three Saturdays now.", mcducscr, swifttay, IssueConstants.OPEN,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 2, "Implement date picker",
                                "Implement a date picker.",
                                mcducscr, swifttay, IssueConstants.CLOSED, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Login Page Alignment Issue",
                                "Alignment issue observed on the login page", mcducscr, messilio, IssueConstants.OPEN,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 3, "Dark Mode Implementation",
                                "Implement dark mode feature for better user experience", messilio, swifttay,
                                IssueConstants.IN_PROGRESS, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.IDEA, 2, "Integration with Third-party API",
                                "Explore possibilities of integrating with third-party API for additional functionalities",
                                swifttay,
                                mcducscr, IssueConstants.CLOSED, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Search Functionality Not Working",
                                "Users unable to search for items using search functionality", mcducscr, messilio,
                                IssueConstants.OPEN, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 2, "Multi-language Support",
                                "Add support for multiple languages in the application", messilio, swifttay,
                                IssueConstants.IN_PROGRESS, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.IDEA, 3, "Implement Chatbot",
                                "Explore the possibility of implementing a chatbot for user assistance", swifttay,
                                swifttay,
                                IssueConstants.OPEN, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 2, "404 Error on Product Page",
                                "Users are encountering a 404 error when accessing the product page", mcducscr,
                                messilio,
                                IssueConstants.OPEN, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 3, "Notification System Upgrade",
                                "Upgrade the notification system to support real-time notifications", messilio,
                                swifttay,
                                IssueConstants.IN_PROGRESS, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.IDEA, 1, "Implement Gamification",
                                "Introduce gamification elements to enhance user engagement", swifttay, mcducscr,
                                IssueConstants.CLOSED, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Incorrect Price Display",
                                "Incorrect prices are being displayed for certain products", mcducscr, messilio,
                                IssueConstants.OPEN,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 2, "Dark Theme Option",
                                "Add an option for users to switch to a dark theme", messilio, swifttay,
                                IssueConstants.IN_PROGRESS,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.IDEA, 3, "Implement SSO",
                                "Implement Single Sign-On (SSO) functionality for seamless user authentication",
                                swifttay, mcducscr,
                                IssueConstants.OPEN, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 2, "Image Upload Fails",
                                "Users are unable to upload images when creating a new post", mcducscr, messilio,
                                IssueConstants.OPEN,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 1, "Integration with Payment Gateway",
                                "Integrate with a payment gateway to support online transactions", messilio, swifttay,
                                IssueConstants.IN_PROGRESS, enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.IDEA, 3, "Implement AI Chatbot",
                                "Implement an AI-powered chatbot for customer support", swifttay, mcducscr,
                                IssueConstants.OPEN,
                                enterpriseProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Slow Page Load Times",
                                "Page load times are unusually slow, impacting user experience", mcducscr, messilio,
                                IssueConstants.OPEN, enterpriseProject));

                // Create issues for Online Tea Shop
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Login fails", "Login fails for all users.",
                                messilio, messilio, IssueConstants.CLOSED, webProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 3, "Page loads slowly", "Takes several seconds.",
                                messilio, messilio, IssueConstants.IN_PROGRESS, webProject));
                issueRepository.save(
                                new Issue(IssueConstants.BUG, 3, "Wrong color in sidebar",
                                                "Background should be blue, is yellow.",
                                                messilio, messilio, IssueConstants.OPEN, webProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Form submit fails",
                                "When clicking submit.", messilio, swifttay, IssueConstants.OPEN, webProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 2, "Implement date picker",
                                "Implement a date picker.",
                                messilio, swifttay, IssueConstants.CLOSED, webProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 1, "Implement tea brewing functionality",
                                "Should present user with a fresh cup.", swifttay, messilio, IssueConstants.OPEN,
                                webProject));
                issueRepository.save(new Issue(IssueConstants.BUG, 1, "Checkout payment options issue",
                                "Incomplete list", messilio, messilio, IssueConstants.OPEN, webProject));
                issueRepository.save(new Issue(IssueConstants.FEATURE, 3, "Dark Mode Implementation",
                                "Implement dark mode feature for better user experience", messilio, swifttay,
                                IssueConstants.IN_PROGRESS, webProject));

        }
}
