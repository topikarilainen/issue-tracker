import React from 'react';
import './css/Card.css';

export default function Card({ title, text }: Readonly<{ title: string, text: string }>) {
	return (
		<div className='Card'>
			<h1>{title}</h1>
			<div>{text}</div>
		</div>
	);
}