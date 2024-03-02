import React, { useEffect, useState } from 'react';
import './css/IssueList.css';
import Card from './Card';
import { Issue, User } from './types';

export default function IssueList({ project }: Readonly<{ project: string }>) {

	const [issues, setIssues] = useState<Issue[]>([]);

	useEffect(() => {
		let ignore = false;

		async function fetchIssuesByProjectAbbreviation(projectAbbreviation: string) {
			try {
				const response = await fetch('/issues?projectAbbreviation=' + projectAbbreviation);
				if (!response.ok) {
					console.error('Response not OK: ' + response.status);
					return;
				}
				const fetchedIssues = await response.json();
				if (!ignore) {
					setIssues(fetchedIssues);
				}
			} catch (error) {
				console.error('Error fetching issues:', error);
			}
		}

		fetchIssuesByProjectAbbreviation(project);

		return (() => {
			ignore = true;
		});
	}, [project]);

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