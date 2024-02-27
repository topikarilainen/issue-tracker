import React from 'react';
import './css/App.css';
import Header from './Header';
import MainContent from './MainContent';
import Sidebar from './Sidebar';

export default function App() {
	return (
		<div className='app-grid-container'>
			<header className='app-grid-container__header'>
				<Header text='Issue Tracker' />
			</header>
			<div className='app-grid-container__sidebar'>
				<Sidebar text='This is the sidebar...'></Sidebar>
			</div>
			<main className='app-grid-container__main-content'>
				<MainContent></MainContent>
			</main>
		</div>
	);
}