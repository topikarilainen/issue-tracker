import React from 'react';
import './css/Sidebar.css';

export default function Sidebar({ text }: Readonly<{ text: string }>) {
	return (
		<div className='Sidebar'>
			{text}
		</div>
	);
}