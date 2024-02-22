import React from 'react';
import Card from './Card';
import './css/MainContent.css';

export default function MainContent() {
	const sampleCardContent = { title: 'A title', text: 'Card text' };
	return (
		<div className='MainContent'>
			<Card
				title={'Card 1 title'}
				text={'Card 1 text'}
			/>
			<Card
				title={'Card 2 title'}
				text={'Card 2 text'}
			/>
			<Card {...sampleCardContent} />
			<Card
				title={sampleCardContent.title}
				text={sampleCardContent.text}
			></Card>
			<Card
				title={sampleCardContent.text}
				text='A different text'
			></Card>
			<Card
				{...sampleCardContent}
				title='Another different title'
			></Card>
		</div>
	);
}