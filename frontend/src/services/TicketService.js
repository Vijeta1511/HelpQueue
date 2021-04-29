import axios from 'axios';

// const QUEUE_TICKETS_URL = "http://localhost:9001/api/v1/tickets";

class TicketService {
    getQueueTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL);
    }

    getCompletedTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL + '/complete');
    }

    getDevelopmentQueueTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL + '/development');
    }

    getDeploymentQueueTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL + '/deployment');
    }

    getDevelopmentCompletedTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL + '/development/complete');
    }

    getDeploymentCompletedTickets(){
        return axios.get(process.env.REACT_APP_BASE_URL + '/deployment/complete');
    }

    createTicket(ticket){
        return axios.post(process.env.REACT_APP_BASE_URL + '/createTicket', ticket);
    }

    getTicketById(ticketId){
        return axios.get(process.env.REACT_APP_BASE_URL + '/' + ticketId);
    }

    updateTicket(ticketId, ticket){
        return axios.put(process.env.REACT_APP_BASE_URL + '/updateTicket/' + ticketId, ticket);
    }

    assignTicket(ticketId, ticket){
        return axios.put(process.env.REACT_APP_BASE_URL + '/assignTicket/' + ticketId, ticket);
    }

    resolveTicket(ticketId, ticket){
        return axios.put(process.env.REACT_APP_BASE_URL + '/completeTicket/' + ticketId, ticket);
    }

    deleteTicket(ticketId){
        return axios.delete(process.env.REACT_APP_BASE_URL + '/deleteTicket/' + ticketId);
    }
    
}

export default new TicketService()