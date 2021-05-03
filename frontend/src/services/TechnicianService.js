import axios from 'axios';


class TechnicianService {
    getTechnicians(ticketId){
        return axios.get(process.env.REACT_APP_BASE_URL + '/assignTicket/' + ticketId);
    }
}

export default new TechnicianService()