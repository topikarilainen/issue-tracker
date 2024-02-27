import React, { useState } from 'react';
import './css/IssueList.css';
import Card from './Card';
import { Issue, User } from './types';

export default function IssueList({ project }: Readonly<{ project: string }>) {

	const [issues, setIssues] = useState<Issue[]>(generateSomeIssues());

	// This function returns some generated data for testing.
	function generateSomeIssues(): Issue[] {
		let issues: Issue[] = [];
		let simpsoli: User = { id: 1, fullName: 'Lisa Simpson', username: 'simpsoli' };
		let einsteal: User = { id: 2, fullName: 'Albert Einstein', username: 'einsteal' };
	
		issues.push({ id: 1, assignee: einsteal, creator: simpsoli, description: 'Check for interplay between light and electricity', priority: 1, projectAbbreviation: 'SIA', status: 'Closed', title: 'Investigate Photoelectric Effect', type: 'Feature' });
		issues.push({ id: 2, assignee: einsteal, creator: simpsoli, description: 'Think what would happen if you are in an accelerating elevator', priority: 1, projectAbbreviation: 'SIA', status: 'In progress', title: 'Consider Special Relativity', type: 'Idea' });
		issues.push({ id: 3, assignee: einsteal, creator: simpsoli, description: 'See if there are any implications on gravity', priority: 1, projectAbbreviation: 'SIA', status: 'Open', title: 'Envision General Relativity', type: 'Bug' });
		return issues;
	}

	function getIssuesByProjectAbbreviation(projectAbbreviation: string): Issue[] {
		// TODO: add effect for fetching issues
		return [];
	}

	return (
		<div className='IssueList'>
			<h1>Issues in {project}</h1>
			<ul>
				{issues.map(issue => 
					<Card
						key={issue.id}
						title={issue.title}
						text={issue.description}
					/>
				)}
			</ul>
		</div >
	);
}