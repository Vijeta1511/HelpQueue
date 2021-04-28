import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import * as MyNavbar from "react-bootstrap";

class DeploymentNavbarComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
        this.queueDeploymentTickets = this.queueDeploymentTickets.bind(this);
        this.completedDeploymentTickets = this.completedDeploymentTickets.bind(this);
    }
    

    queueDeploymentTickets(){
        this.props.history.push('/deployment-tickets');
    }

    completedDeploymentTickets(){
        this.props.history.push('/completed-deployment-tickets');
    }

    render() {
        return (
            <div>
                <header>
                <br />
                <MyNavbar.Button variant="outline-secondary" onClick={()=> this.queueDeploymentTickets()}>
                Development Queue
                </MyNavbar.Button>{' '}
                <MyNavbar.Button variant="outline-secondary" onClick={()=> this.completedDeploymentTickets()}>
                Completed Development Tickets
                </MyNavbar.Button>{' '}
                </header>
            </div>
        );
    }
}

export default withRouter(DeploymentNavbarComponent);