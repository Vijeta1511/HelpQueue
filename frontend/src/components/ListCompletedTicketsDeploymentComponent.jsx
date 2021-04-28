import React, { Component } from 'react';
import TicketService from '../services/TicketService';
import DeploymentNavbarComponent from './DeploymentNavbarComponent';

class ListCompletedTicketsDeploymentComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            deploymentCompletedTickets: []
        }
    }

    componentDidMount() {
        TicketService.getDeploymentCompletedTickets().then((res) => {
            this.setState({deploymentCompletedTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <DeploymentNavbarComponent />
                <h2 className="text-center"><br />Completed Deployment Tickets</h2>
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
                                this.state.deploymentCompletedTickets.map(
                                    deploymentCompletedTickets =>
                                    <tr key = {deploymentCompletedTickets.id}>
                                        <td> {deploymentCompletedTickets.title} </td>
                                        <td> {deploymentCompletedTickets.author} </td>
                                        <td> {deploymentCompletedTickets.description} </td>
                                        <td> {deploymentCompletedTickets.time_created} </td>
                                        <td> {deploymentCompletedTickets.solution} </td>
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

export default ListCompletedTicketsDeploymentComponent;