import React from 'react';
import Publisher from './Publisher.js';

export default class PublisherList extends React.Component {
	render() {
		var publishers = this.props.publishers.map(publisher =>
			<Publisher key={ publisher._links.self.href } publisherData={ publisher } />
		);
		
		return (
				<table>
					<tbody>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
						</tr>
						{ publishers }
					</tbody>
				</table>
				)
	}
}

//module.exports = PublisherList;