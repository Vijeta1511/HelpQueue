import React, { Component } from 'react';
import TicketService from '../services/TicketService';
import {withRouter} from 'react-router-dom';

class ListQueueTicketComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            queueTickets: []
        }
        this.editTicket = this.editTicket.bind(this);
        this.assignTicket = this.assignTicket.bind(this);
        this.resolveTicket = this.resolveTicket.bind(this);
        this.deleteTicket = this.deleteTicket.bind(this);
    }

    editTicket(id){
        this.props.history.push(`/update-ticket/${id}`);
    }

    assignTicket(id){
        this.props.history.push(`/assign-ticket/${id}`);
    }

    resolveTicket(id){
        this.props.history.push(`/solution-ticket/${id}`);
    }

    deleteTicket(id){
        this.props.history.push(`/delete-ticket/${id}`);
    }

    componentDidMount() {
        TicketService.getQueueTickets().then((res) => {
            this.setState({queueTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <h2 className="text-center"><br />Ticket Queue</h2>
                <br />
                <div className="row">
                    <table className= "table table-stripped table-bordered">
                        
                        <thead>
                            <tr>
                                <th> Title </th>
                                <th> Author </th>
                                <th> Description </th>
                                <th> Department </th>
                                <th> Assignee </th>
                                <th> Time Created </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.queueTickets.map(
                                    queueTickets =>
                                    <tr key = {queueTickets.id}>
                                        <td> {queueTickets.title} </td>
                                        <td> {queueTickets.author} </td>
                                        <td> {queueTickets.description} </td>
                                        <td> {queueTickets.department} </td>
                                        <td> {queueTickets.asignee} </td>
                                        <td> {queueTickets.time_created} </td>
                                        <td> <button onClick= { () => this.editTicket(queueTickets.id)} 
                                        className = "btn btn-success"> Update </button>{' '}
                                        <button onClick= { () => this.assignTicket(queueTickets.id)} 
                                        className = "btn btn-success"> Assign </button>{' '}
                                        <button onClick= { () => this.resolveTicket(queueTickets.id)} 
                                        className = "btn btn-success"> Resolve </button>{' '}
                                        <button onClick= { () => this.deleteTicket(queueTickets.id)} 
                                        className = "btn btn-danger"> Delete </button></td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>

            </div>
        );
    }
}

export default withRouter(ListQueueTicketComponent);