import React from 'react';
import Card from './Card';

export default function App() {
	return (
		<>
			<Card
				title={'Card 1 title'}
				text={'Card 1 text'}
			/>
			<Card
				title={'Card 2 title'}
				text={'Card 2 text'}
			/>
		</>
	);
}