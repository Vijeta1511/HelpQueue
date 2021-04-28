import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import * as MyNavbar from "react-bootstrap";

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
        this.createTicket = this.createTicket.bind(this); 
        this.developmentTickets = this.developmentTickets.bind(this);
        this.deploymentTickets = this.deploymentTickets.bind(this);
        this.completedTickets = this.completedTickets.bind(this);
        this.queueTickets = this.queueTickets.bind(this);
    }
    

    createTicket(){
        this.props.history.push('/create-ticket');
    }

    developmentTickets(){
        this.props.history.push('/development-tickets');
    }

    deploymentTickets(){
        this.props.history.push('/deployment-tickets');
    }

    completedTickets(){
        this.props.history.push('/completed-tickets');
    }

    queueTickets(){
        this.props.history.push('/queue-tickets');
    }

    render() {
        return (
            <div>
                <header>
                <MyNavbar.Navbar bg="dark" variant="dark">
                <MyNavbar.Image
                        alt=""
                        src="/barclays_logo.png"
                        width="200"
                        height="30"
                        className="d-inline-block align-top"
                    />
                    <MyNavbar.Navbar.Brand href="/" data-testid="Help-Queue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Help Queue
                    </MyNavbar.Navbar.Brand>
                    <MyNavbar.Nav variant="pills" defaultActiveKey="/" className="nav-items">
                        <MyNavbar.Nav.Item>
                            <MyNavbar.Nav.Link onClick={()=> this.queueTickets()} data-testid="Ticket-Queue">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Ticket Queue
                            </MyNavbar.Nav.Link>
                        </MyNavbar.Nav.Item>
                        <MyNavbar.Nav.Item>
                            <MyNavbar.Nav.Link onClick={()=> this.completedTickets()} data-testid="Completed-Tickets">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Completed Tickets&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </MyNavbar.Nav.Link>
                        </MyNavbar.Nav.Item>
                        <MyNavbar.Nav.Item>
                            <MyNavbar.NavDropdown title="Departments" id="collasible-nav-dropdown" data-testid="Departments">
                                <MyNavbar.NavDropdown.Item onClick={()=> this.developmentTickets()} data-testid="Development">
                                    Development</MyNavbar.NavDropdown.Item>
                                <MyNavbar.NavDropdown.Item onClick={()=> this.deploymentTickets()} data-testid="Deployment">
                                    Deployment</MyNavbar.NavDropdown.Item>
                            </MyNavbar.NavDropdown>
                        </MyNavbar.Nav.Item>
                        <MyNavbar.Nav.Item>
                            <MyNavbar.Nav.Link onClick={()=> this.createTicket()} data-testid="Create-Ticket">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Create Ticket
                            </MyNavbar.Nav.Link>
                        </MyNavbar.Nav.Item>
                    </MyNavbar.Nav>
                </MyNavbar.Navbar>
                </header>
            </div>
        );
    }
}

export default withRouter(HeaderComponent);