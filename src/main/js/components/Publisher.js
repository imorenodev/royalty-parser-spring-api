import React from 'react';

export default class Publisher extends React.Component {
	render() {
		return (
				<tr>
					<td>{ this.props.publisherData.firstName }</td>
					<td>{ this.props.publisherData.lastName }</td>
					<td>{ this.props.publisherData.email}</td>
				</tr>
				)
	}
}