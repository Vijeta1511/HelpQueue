import axios from 'axios';

const REACT_APP_BASE_URL = "/api/v1/tickets";

class TicketService {
    getQueueTickets(){
        return axios.get(REACT_APP_BASE_URL);
    }

    getCompletedTickets(){
        return axios.get(REACT_APP_BASE_URL + '/complete');
    }

    getDevelopmentQueueTickets(){
        return axios.get(REACT_APP_BASE_URL + '/development');
    }

    getDeploymentQueueTickets(){
        return axios.get(REACT_APP_BASE_URL + '/deployment');
    }

    getDevelopmentCompletedTickets(){
        return axios.get(REACT_APP_BASE_URL + '/development/complete');
    }

    getDeploymentCompletedTickets(){
        return axios.get(REACT_APP_BASE_URL + '/deployment/complete');
    }

    createTicket(ticket){
        return axios.post(REACT_APP_BASE_URL + '/createTicket', ticket);
    }

    getTicketById(ticketId){
        return axios.get(REACT_APP_BASE_URL + '/' + ticketId);
    }

    updateTicket(ticketId, ticket){
        return axios.put(REACT_APP_BASE_URL + '/updateTicket/' + ticketId, ticket);
    }

    assignTicket(ticketId, ticket){
        return axios.put(REACT_APP_BASE_URL + '/assignTicket/' + ticketId, ticket);
    }

    resolveTicket(ticketId, ticket){
        return axios.put(REACT_APP_BASE_URL + '/completeTicket/' + ticketId, ticket);
    }

    deleteTicket(ticketId){
        return axios.delete(REACT_APP_BASE_URL + '/deleteTicket/' + ticketId);
    }
    
}

export default new TicketService()