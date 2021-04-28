import React, { Component } from 'react';
import TicketService from '../services/TicketService';
import DevelopmentNavbarComponent from './DevelopmentNavbarComponent';
import {withRouter} from 'react-router-dom';

class ListQueueTicketsDevelopmentComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            developmentQueueTickets: []
        }
        this.editTicket = this.editTicket.bind(this);
        this.assignTicket = this.assignTicket.bind(this);
        this.resolveTicket = this.resolveTicket.bind(this);
        this.deleteTicket = this.deleteTicket.bind(this);
    }

    editTicket(id){
        this.props.history.push(`/update-ticket-development/${id}`);
    }

    assignTicket(id){
        this.props.history.push(`/assign-ticket-development/${id}`);
    }

    resolveTicket(id){
        this.props.history.push(`/solution-ticket-development/${id}`);
    }

    deleteTicket(id){
        this.props.history.push(`/delete-ticket-development/${id}`);
    }

    componentDidMount() {
        TicketService.getDevelopmentQueueTickets().then((res) => {
            this.setState({developmentQueueTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <DevelopmentNavbarComponent />
                <h2 className="text-center"><br />Development Queue</h2>
                <br />
                <div className="row">
                    <table className= "table table-stripped table-bordered">
                        
                        <thead>
                            <tr>
                                <th> Title </th>
                                <th> Author </th>
                                <th> Description </th>
                                <th> Assignee </th>
                                <th> Time Created </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.developmentQueueTickets.map(
                                    developmentQueueTickets =>
                                    <tr key = {developmentQueueTickets.id}>
                                        <td> {developmentQueueTickets.title} </td>
                                        <td> {developmentQueueTickets.author} </td>
                                        <td> {developmentQueueTickets.description} </td>
                                        <td> {developmentQueueTickets.asignee} </td>
                                        <td> {developmentQueueTickets.time_created} </td>
                                        <td> <button onClick= { () => this.editTicket(developmentQueueTickets.id)} 
                                        className = "btn btn-success"> Update </button>{' '}
                                        <button onClick= { () => this.assignTicket(developmentQueueTickets.id)} 
                                        className = "btn btn-success"> Assign </button>{' '}
                                        <button onClick= { () => this.resolveTicket(developmentQueueTickets.id)} 
                                        className = "btn btn-success"> Resolve </button>{' '}
                                        <button onClick= { () => this.deleteTicket(developmentQueueTickets.id)} 
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

export default withRouter(ListQueueTicketsDevelopmentComponent);