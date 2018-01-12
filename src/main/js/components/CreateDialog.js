import React from 'react';
import ReactDOM from 'react-dom';

export default class CreateDialog extends React.Component {
		constructor(props) {
			super(props);
			this.handleSubmit = this.handleSubmit.bind(this);
		}

		handleSubmit(e) {
			e.preventDefault();
			var newPublisher = {};
			this.props.attributes.forEach(attribute => {
				newPublisher[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
			});
			this.props.onCreate(newPublisher);

			// clear out the dialog's inputs
			this.props.attributes.forEach(attribute => {
				ReactDOM.findDOMNode(this.refs[attribute]).value = '';
			});

			// Navigate away from the dialog to hide it.
			window.location = "#";
		}

		render() {
			var inputs = this.props.attributes.map(attribute =>
				<p key={attribute}>
					<input type="text" placeholder={attribute} ref={attribute} className="field" />
				</p>
			);

			return (
				<div>
					<a href="#createPublisher">Create</a>

					<div id="createPublisher" className="modalDialog">
						<div>
							<a href="#" title="Close" className="close">X</a>

							<h2>Create new publisher</h2>

							<form>
								{inputs}
								<button onClick={this.handleSubmit}>Create</button>
							</form>
						</div>
					</div>
				</div>
			)
		}

}