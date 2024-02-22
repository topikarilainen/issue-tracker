import React from 'react';
import './css/Header.css';

export default function Header({ text }: Readonly<{ text: string }>) {
	return (
		<div className='Header'>
			<h1>{text}</h1>
		</div>
	);
}