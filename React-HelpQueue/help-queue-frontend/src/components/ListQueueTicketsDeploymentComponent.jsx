 import React, { Component } from 'react';
 import TicketService from '../services/TicketService';
import DeploymentNavbarComponent from './DeploymentNavbarComponent';
import {withRouter} from 'react-router-dom';

class ListQueueTicketsDeploymentComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            deploymentQueueTickets: []
        }
        this.editTicket = this.editTicket.bind(this);
        this.assignTicket = this.assignTicket.bind(this);
        this.resolveTicket = this.resolveTicket.bind(this);
        this.deleteTicket = this.deleteTicket.bind(this);
    }

    editTicket(id){
        this.props.history.push(`/update-ticket-deployment/${id}`);
    }

    assignTicket(id){
        this.props.history.push(`/assign-ticket-deployment/${id}`);
    }

    resolveTicket(id){
        this.props.history.push(`/solution-ticket-deployment/${id}`);
    }

    deleteTicket(id){
        this.props.history.push(`/delete-ticket-deployment/${id}`);
    }

    componentDidMount() {
        TicketService.getDeploymentQueueTickets().then((res) => {
            this.setState({deploymentQueueTickets: res.data})
        });
    }
    render() {
        return (
            <div>
                <DeploymentNavbarComponent />
                <h2 className="text-center"><br />Deployment Queue</h2>
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
                                this.state.deploymentQueueTickets.map(
                                    deploymentQueueTickets =>
                                    <tr key = {deploymentQueueTickets.id}>
                                        <td> {deploymentQueueTickets.title} </td>
                                        <td> {deploymentQueueTickets.author} </td>
                                        <td> {deploymentQueueTickets.description} </td>
                                        <td> {deploymentQueueTickets.asignee} </td>
                                        <td> {deploymentQueueTickets.time_created} </td>
                                        <td> <button onClick= { () => this.editTicket(deploymentQueueTickets.id)} 
                                        className = "btn btn-success"> Update </button>{' '}
                                        <button onClick= { () => this.assignTicket(deploymentQueueTickets.id)} 
                                        className = "btn btn-success"> Assign </button>{' '}
                                        <button onClick= { () => this.resolveTicket(deploymentQueueTickets.id)} 
                                        className = "btn btn-success"> Resolve </button>{' '}
                                        <button onClick= { () => this.deleteTicket(deploymentQueueTickets.id)} 
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

export default withRouter(ListQueueTicketsDeploymentComponent);