import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import TicketService from '../services/TicketService';

class DeleteTicketComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            solution: ''
        }

        this.deleteTicket = this.deleteTicket.bind(this); 
        this.cancel = this.cancel.bind(this);
    }

    deleteTicket = (e) => {
        e.preventDefault();
        TicketService.deleteTicket(this.state.id).then( res => {
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
                            <h3 className="text-center"><br />Delete Ticket</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group text-center">
                                        <label> Are you sure you want to delete this ticket? </label>
                                    </div>
                                    <button className="btn btn-danger" onClick={this.deleteTicket} style={{marginLeft: "150px"}}>Delete</button>
                                    <button className="btn btn-success" onClick={this.cancel} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(DeleteTicketComponent);