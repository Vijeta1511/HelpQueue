import React, { Component } from 'react';
import TicketService from '../services/TicketService';
import DevelopmentNavbarComponent from './DevelopmentNavbarComponent';

class ListCompletedTicketsDevelopmentComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            developmentCompletedTickets: []
        }
    }

    componentDidMount() {
        TicketService.getDevelopmentCompletedTickets().then((res) => {
            this.setState({developmentCompletedTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <DevelopmentNavbarComponent />
                <h2 className="text-center"><br />Completed Development Tickets</h2>
                <br />
                <div className="row">
                    <table className= "table table-stripped table-bordered">
                        
                        <thead>
                            <tr>
                                <th> Title </th>
                                <th> Author </th>
                                <th> Description </th>
                                <th> Time Created </th>
                                <th> Solution </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.developmentCompletedTickets.map(
                                    developmentCompletedTickets =>
                                    <tr key = {developmentCompletedTickets.id}>
                                        <td> {developmentCompletedTickets.title} </td>
                                        <td> {developmentCompletedTickets.author} </td>
                                        <td> {developmentCompletedTickets.description} </td>
                                        <td> {developmentCompletedTickets.time_created} </td>
                                        <td> {developmentCompletedTickets.solution} </td>                                       
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

export default ListCompletedTicketsDevelopmentComponent;