import axios from 'axios';

const QUEUE_TICKETS_URL = "http://localhost:9001/api/v1/tickets";
const COMPLETED_TICKETS_URL = "http://localhost:9001/api/v1/tickets/complete";
const DEVELOPMENT_QUEUE_TICKETS_URL = "http://localhost:9001/api/v1/tickets/development";
const DEPLOYMENT_QUEUE_TICKETS_URL = "http://localhost:9001/api/v1/tickets/deployment";
const DEVELOPMENT_COMPLETED_TICKETS_URL = "http://localhost:9001/api/v1/tickets/development/complete";
const DEPLOYMENT_COMPLETED_TICKETS_URL = "http://localhost:9001/api/v1/tickets.deployment/complete";


class TicketService {
    getQueueTickets(){
        return axios.get(QUEUE_TICKETS_URL);
    }

    getCompletedTickets(){
        return axios.get(COMPLETED_TICKETS_URL);
    }

    getDevelopmentQueueTickets(){
        return axios.get(DEVELOPMENT_QUEUE_TICKETS_URL);
    }

    getDeploymentQueueTickets(){
        return axios.get(DEPLOYMENT_QUEUE_TICKETS_URL);
    }

    getDevelopmentCompletedTickets(){
        return axios.get(DEVELOPMENT_COMPLETED_TICKETS_URL);
    }

    getDeploymentCompletedTickets(){
        return axios.get(DEPLOYMENT_COMPLETED_TICKETS_URL);
    }
}

export default new TicketService()