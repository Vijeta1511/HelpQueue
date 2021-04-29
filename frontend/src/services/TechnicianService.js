import axios from 'axios';

// const FETCH_TECHNICIANS_URL = 'http://localhost:9001/api/v1/tickets/assignTicket';

class TechnicianService {
    getTechnicians(ticketId){
        return axios.get(process.env.REACT_APP_BASE_URL + '/assignTicket/' + ticketId);
    }
}

export default new TechnicianService()