export { getIssues };

async function getIssues() {
    try {
        const response = await fetch("/issues?projectAbbreviation=SIA");
        if (!response.ok) {
            console.error("Response not OK: " + response.status);
            return;
        }
        const issues = await response.json();
        const contentElem = document.getElementById("issues");
        for (let issue of issues) {
            const issueElem = document.createElement("div");
            issueElem.textContent = `${issue.project.abbreviation}-${issue.id} ${issue.type} ${issue.title} ${issue.creator.fullName} ${issue.assignee.fullName} ${issue.status} ${issue.priority} ${issue.description}`
            contentElem.appendChild(issueElem);
        }
    } catch (error) {
        console.error("Error fetching issues:", error);
    }
}
