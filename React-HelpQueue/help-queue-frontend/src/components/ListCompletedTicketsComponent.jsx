import React, { Component } from 'react';
import TicketService from '../services/TicketService';


class ListCompletedTicketsComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            completedTickets: []
        }
    }

    componentDidMount() {
        TicketService.getCompletedTickets().then((res) => {
            this.setState({completedTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <h2 className="text-center"><br />Completed Tickets</h2>
                <br />
                <div className="row">
                    <table className= "table table-stripped table-bordered">
                        
                        <thead>
                            <tr>
                                <th> Title </th>
                                <th> Author </th>
                                <th> Description </th>
                                <th> Department </th>
                                <th> Time Created </th>
                                <th> Solution </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.completedTickets.map(
                                    completedTickets =>
                                    <tr key = {completedTickets.id}>
                                        <td> {completedTickets.title} </td>
                                        <td> {completedTickets.author} </td>
                                        <td> {completedTickets.description} </td>
                                        <td> {completedTickets.department} </td>
                                        <td> {completedTickets.time_created} </td>
                                        <td> {completedTickets.solution} </td>
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

export default ListCompletedTicketsComponent;