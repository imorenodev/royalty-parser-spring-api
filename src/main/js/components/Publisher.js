import React from 'react';

export default class Publisher extends React.Component {
	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.publisher);
	}

	render() {
		return (
				<tr>
					<td>{ this.props.publisher.firstName }</td>
					<td>{ this.props.publisher.lastName }</td>
					<td>{ this.props.publisher.email}</td>
				</tr>
				)
	}
}