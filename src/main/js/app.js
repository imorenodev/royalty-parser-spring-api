'use strict'

import React from 'react';
import ReactDOM from 'react-dom';
import client from './client';
import follow from './follow';
import PublisherContainer from './containers/PublisherContainer.js';
import CreateDialog from './components/CreateDialog.js';

const root = '/api';

class App extends React.Component {
	constructor(props) {
		super(props);
		this.state = {publishers: [], attributes: [], pageSize: 2, links: {}};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
		
	}
	
	loadFromServer(pageSize) {
		follow(client, root, [ { rel: 'publishers', params: {size: pageSize} } ]
		).then(publisherCollection => {
			return client({
				method: 'GET',
				path: publisherCollection.entity._links.profile.href,
				headers: {'Accept': 'application/schema+json'}
			}).then(schema => {
				this.schema = schema.entity;
				return publisherCollection;
			});
		}).done(publisherCollection => {
			this.setState({
				publishers: publisherCollection.entity._embedded.publishers,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: publisherCollection.entity._links});
		});
	}
	
	// tag::create[]
	onCreate(newPublisher) {
		follow(client, root, ['publishers']).then(publisherCollection => {
			return client({
				method: 'POST',
				path: publisherCollection.entity._links.self.href,
				entity: newPublisher,
				headers: {'Content-Type': 'application/json'}
			})
		}).then(response => {
			return follow(client, root, [
				{rel: 'publishers', params: {'size': this.state.pageSize}}]);
		}).done(response => {
			if (typeof response.entity._links.last != "undefined") {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		});
	}
	// end::create[]

	// tag::delete[]
	onDelete(publisher) {
		client({method: 'DELETE', path: publisher._links.self.href}).done(response => {
			this.loadFromServer(this.state.pageSize);
		});
	}
	// end::delete[]

	// tag::navigate[]
	onNavigate(navUri) {
		client({method: 'GET', path: navUri}).done(publisherCollection => {
			this.setState({
				publishers: publisherCollection.entity._embedded.publishers,
				attributes: this.state.attributes,
				pageSize: this.state.pageSize,
				links: publisherCollection.entity._links
			});
		});
	}
	// end::navigate[]

	// tag::update-page-size[]
	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}
	// end::update-page-size[]


	// end::follow-1[]
	
	render() {
		return (
				<div>
					<h2>{"Publishers"}</h2>
					<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
					<PublisherContainer publishers={this.state.publishers}
									   links={this.state.links}
									   pageSize={this.state.pageSize}
									   onNavigate={this.onNavigate}
									   onDelete={this.onDelete}
									   updatePageSize={this.updatePageSize} />
				</div>
				)
	}
}


ReactDOM.render(
		<App />,
		document.getElementById('react')
		);