import React from 'react';
import './css/MainContent.css';
import IssueList from './IssueList';

export default function MainContent() {
	return (
		<div className='MainContent'>
			<IssueList
				project='SIA'>
			</IssueList>
		</div>
	);
}