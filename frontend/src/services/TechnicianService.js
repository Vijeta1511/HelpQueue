import axios from 'axios';

const REACT_APP_BASE_URL = "/api/v1/tickets";

class TechnicianService {
    getTechnicians(ticketId){
        return axios.get(REACT_APP_BASE_URL + '/assignTicket/' + ticketId);
    }
}

export default new TechnicianService()