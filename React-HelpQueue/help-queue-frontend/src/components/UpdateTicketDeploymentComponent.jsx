import React, { Component } from 'react';
import {withRouter} from 'react-router-dom';
import TicketService from '../services/TicketService';

class UpdateTicketDeplopymentComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            
            id: this.props.match.params.id,
            title: '',
            author: '',
            description: '',
            department: ''
        }

        this.changeTitleHandler = this.changeTitleHandler.bind(this); 
        this.changeAuthorHandler = this.changeAuthorHandler.bind(this); 
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this); 
        this.changeDepartmentHandler = this.changeDepartmentHandler.bind(this); 
        this.updateTicket = this.updateTicket.bind(this); 
        this.cancel = this.cancel.bind(this);
    }

    changeTitleHandler=(event) =>{
        this.setState({title: event.target.value});
    }

    changeAuthorHandler=(event) =>{
        this.setState({author: event.target.value});
    }

    changeDescriptionHandler=(event) =>{
        this.setState({description: event.target.value});
    }

    changeDepartmentHandler=(event) =>{
        this.setState({department: event.target.value});
    }

    componentDidMount(){
        TicketService.getTicketById(this.state.id).then( (res) => {
            let ticket = res.data;
            this.setState({title: ticket.title,
                author: ticket.author,
                description: ticket.description,
                department: ticket.department
            });
            console.log('ticket => ' + JSON.stringify(ticket));
        });
    }

    updateTicket = (e) => {
        e.preventDefault();
        let ticket = {title: this.state.title, author: this.state.author, 
            description: this.state.description, department: this.state.department}
        console.log('ticket => ' + JSON.stringify(ticket));

        TicketService.updateTicket(this.state.id, ticket).then( res => {
            this.props.history.push('/deployment-tickets');
        });
    }

    cancel(){
        this.props.history.push('/deployment-tickets');
    }

    render() {
        return (
            <div>
                <br /><br />
                <div className= "container">
                    <div className= "row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center"><br />Update Ticket</h3>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Title: </label>
                                        <input placeholder="Title" name="title" className="form-control"
                                            value={this.state.title} onChange={this.changeTitleHandler}/>
                                    </div>

                                    <div className = "form-group">
                                        <label> <br />Author: </label>
                                        <input placeholder="Author" name="author" className="form-control"
                                            value={this.state.author} onChange={this.changeAuthorHandler}/>
                                    </div>

                                    <div className = "form-group">
                                        <label> <br />Description: </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                            value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                    </div>

                                    <div className = "form-group">
                                        <label> <br />Department: </label>
                                        <select className="form-control" name="department" 
                                            value={this.state.department} onChange={this.changeDepartmentHandler}>
                                            <option value="" selected disabled>Please select</option>
                                            <option value="Development">Development</option>
                                            <option value="Deployment">Deployment</option>
                                        </select>
                                        <br />
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateTicket} style={{marginLeft: "150px"}}>Submit</button>
                                    <button className="btn btn-danger" onClick={this.cancel} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(UpdateTicketDeplopymentComponent);