import React, { Component } from 'react';
import TechnicianService from '../services/TechnicianService';
import TicketService from '../services/TicketService';
import {withRouter} from 'react-router-dom';

class AssignTicketComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            id: this.props.match.params.id,
            technicians: [],
            asignee: ''
        }
        this.changeTechnicianHandler = this.changeTechnicianHandler.bind(this); 
        this.assignTicket = this.assignTicket.bind(this); 
        this.cancel = this.cancel.bind(this);
    }

    changeTechnicianHandler=(event) =>{
        this.setState({asignee: event.target.value});
    }

    componentDidMount(){
        console.log(this.state.id);
        TechnicianService.getTechnicians(this.state.id).then( (res) => {
            this.setState({technicians: res.data});
        });

        TicketService.getTicketById(this.state.id).then( (res) => {
            let ticket = res.data;
            this.setState({asignee: ticket.asignee});
            console.log('ticket => ' + JSON.stringify(ticket));
        });
    }

    assignTicket = (e) => {
        e.preventDefault();
        let ticket = {asignee: this.state.asignee}
        console.log('ticket => ' + JSON.stringify(ticket));

        TicketService.assignTicket(this.state.id, ticket).then( res => {
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
                            <h3 className="text-center"><br />Assign Ticket</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Select Technician: </label>
                                        <select name="asignee" className="form-control" 
                                        value={this.state.asignee} onChange={this.changeTechnicianHandler}>
                                            <option value="" selected disabled>Please select</option>
                                            {this.state.technicians.map((e, key) => {
                                                return <option key={key} value={e.value}>{e.name}</option>;
                                            })}
                                        </select>
                                        <br />
                                    </div>
                                    <button className="btn btn-success" onClick={this.assignTicket} style={{marginLeft: "150px"}}>Submit</button>
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

export default withRouter(AssignTicketComponent);