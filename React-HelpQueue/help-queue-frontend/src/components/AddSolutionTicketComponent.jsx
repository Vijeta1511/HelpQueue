import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import TicketService from '../services/TicketService';

class AddSolutionTicketComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            solution: ''
        }

        this.changeSolutionHandler = this.changeSolutionHandler.bind(this);
        this.resolveTicket = this.resolveTicket.bind(this); 
        this.cancel = this.cancel.bind(this);
    }

    changeSolutionHandler=(event) =>{
        this.setState({solution: event.target.value});
    }

    resolveTicket = (e) => {
        e.preventDefault();
        let ticket = {solution: this.state.solution}
        console.log('ticket => ' + JSON.stringify(ticket));

        TicketService.resolveTicket(this.state.id, ticket).then( res => {
            this.props.history.push('/queue-tickets');
        });
    }

    cancel(){
        this.props.history.push('/queue-tickets');
    }

    render() {
        return (
            <div>
                <br /><br />
                <div className= "container">
                    <div className= "row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center"><br />Resolve Ticket</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Solution: </label>
                                        <input type="textarea" placeholder="Add here" name="solution" className="form-control"
                                            value={this.state.solution} onChange={this.changeSolutionHandler}/>
                                        <br />
                                    </div>
                                    <button className="btn btn-success" onClick={this.resolveTicket} style={{marginLeft: "150px"}}>Submit</button>
                                    <button className="btn btn-danger" onClick={this.cancel} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(AddSolutionTicketComponent);