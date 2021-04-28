import axios from 'axios';

const QUEUE_TICKETS_URL = "http://localhost:9001/api/v1/tickets";

class TicketService {
    getQueueTickets(){
        return axios.get(QUEUE_TICKETS_URL);
    }

    getCompletedTickets(){
        return axios.get(QUEUE_TICKETS_URL + '/complete');
    }

    getDevelopmentQueueTickets(){
        return axios.get(QUEUE_TICKETS_URL + '/development');
    }

    getDeploymentQueueTickets(){
        return axios.get(QUEUE_TICKETS_URL + '/deployment');
    }

    getDevelopmentCompletedTickets(){
        return axios.get(QUEUE_TICKETS_URL + '/development/complete');
    }

    getDeploymentCompletedTickets(){
        return axios.get(QUEUE_TICKETS_URL + '/deployment/complete');
    }

    createTicket(ticket){
        return axios.post(QUEUE_TICKETS_URL + '/createTicket', ticket);
    }

    getTicketById(ticketId){
        return axios.get(QUEUE_TICKETS_URL + '/' + ticketId);
    }

    updateTicket(ticketId, ticket){
        return axios.put(QUEUE_TICKETS_URL + '/updateTicket/' + ticketId, ticket);
    }

    assignTicket(ticketId, ticket){
        return axios.put(QUEUE_TICKETS_URL + '/assignTicket/' + ticketId, ticket);
    }

    resolveTicket(ticketId, ticket){
        return axios.put(QUEUE_TICKETS_URL + '/completeTicket/' + ticketId, ticket);
    }

    deleteTicket(ticketId){
        return axios.delete(QUEUE_TICKETS_URL + '/deleteTicket/' + ticketId);
    }
    
}

export default new TicketService()