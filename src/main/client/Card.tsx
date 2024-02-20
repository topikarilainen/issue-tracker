import React from 'react';
import './css/style.css';

export default function Card({ title, text }: Readonly<{ title: string, text: string }>) {
	return (
		<div className='card'>
			<h1>{title}</h1>
			<div>{text}</div>
		</div>
	);
}