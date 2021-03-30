import React, { Component } from 'react';
import TicketService from '../services/TicketService';

class ListQueueTicketComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            queueTickets: []
        }
    }

    componentDidMount() {
        TicketService.getQueueTickets().then((res) => {
            this.setState({queueTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <h2 className="text-center">Ticket Queue</h2>
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
                                        <td> {queueTickets.assignee} </td>
                                        <td> {queueTickets.time_created} </td>
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

export default ListQueueTicketComponent;