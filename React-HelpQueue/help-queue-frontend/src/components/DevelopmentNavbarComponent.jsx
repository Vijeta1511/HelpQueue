import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import * as MyNavbar from "react-bootstrap";

class DevelopmentNavbarComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
        this.queueDevelopmentTickets = this.queueDevelopmentTickets.bind(this);
        this.completedDevelopmentTickets = this.completedDevelopmentTickets.bind(this);
    }
    

    queueDevelopmentTickets(){
        this.props.history.push('/development-tickets');
    }

    completedDevelopmentTickets(){
        this.props.history.push('/completed-development-tickets');
    }

    render() {
        return (
            <div>
                <header>
                    <br />
                <MyNavbar.Button variant="outline-secondary" onClick={()=> this.queueDevelopmentTickets()}>
                Development Queue
                </MyNavbar.Button>{' '}
                <MyNavbar.Button variant="outline-secondary" onClick={()=> this.completedDevelopmentTickets()}>
                Completed Development Tickets
                </MyNavbar.Button>{' '}
                </header>
            </div>
        );
    }
}

export default withRouter(DevelopmentNavbarComponent);