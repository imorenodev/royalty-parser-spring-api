import React from 'react';
import ReactDOM from 'react-dom';
import client from './client';
import PublisherContainer from './containers/PublisherContainer.js';

class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = { publishers: [] };
	}

	componentDidMount() {
		client({ method: 'GET',
				 path: '/api/publishers' })
			.done(response => {
				this.setState({ publishers: response.entity._embedded.publishers });
			});
	}
	
	render() {
		return (
				<div>
					<h2>{"Publishers"}</h2>
					<PublisherContainer publishers={ this.state.publishers }/>
				</div>
				)
	}
}


ReactDOM.render(
		<App />,
		document.getElementById('react')
		);