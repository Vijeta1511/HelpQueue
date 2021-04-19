import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListQueueTicketComponent from './components/ListQueueTicketComponent.jsx';
import CreateTicketComponent from './components/CreateTicketComponent';
import ListCompletedTicketsComponent from './components/ListCompletedTicketsComponent';
import ListQueueTicketsDevelopmentComponent from './components/ListQueueTicketsDevelopmentComponent';
import ListQueueTicketsDeploymentComponent from './components/ListQueueTicketsDeploymentComponent';
import ListCompletedTicketsDevelopmentComponent from './components/ListCompletedTicketsDevelopmentComponent';
import ListCompletedTicketsDeploymentComponent from './components/ListCompletedTicketsDeploymentComponent';
import UpdateTicketComponent from './components/UpdateTicketComponent';
import AssignTicketComponent from './components/AssignTicketComponent';
import AddSolutionTicketComponent from './components/AddSolutionTicketComponent';
import DeleteTicketComponent from './components/DeleteTicketComponent';

import UpdateTicketDevelopmentComponent from './components/UpdateTicketDevelopmentComponent';
import AssignTicketDevelopmentComponent from './components/AssignTicketDevelopmentComponent';
import AddSolutionTicketDevelopmentComponent from './components/AddSolutionTicketDevelopmentComponent';
import DeleteTicketDevelopmentComponent from './components/DeleteTicketDevelopmentComponent';

import UpdateTicketDeploymentComponent from './components/UpdateTicketDeploymentComponent';
import AssignTicketDeploymentComponent from './components/AssignTicketDeploymentComponent';
import AddSolutionTicketDeploymentComponent from './components/AddSolutionTicketDeploymentComponent';
import DeleteTicketDeploymentComponent from './components/DeleteTicketDeploymentComponent';


function App() {
  return (
    <div>
      <Router>
          <HeaderComponent />
            <div className="container">
              <Switch>
                <Route path = "/" exact component = {ListQueueTicketComponent}></Route>
                <Route path = "/queue-tickets" exact component = {ListQueueTicketComponent}></Route>
                <Route path = "/create-ticket" component = {CreateTicketComponent}></Route>
                <Route path = "/completed-tickets" component = {ListCompletedTicketsComponent}></Route>
                <Route path = "/development-tickets" component = {ListQueueTicketsDevelopmentComponent}></Route>
                <Route path = "/deployment-tickets" component = {ListQueueTicketsDeploymentComponent}></Route>
                <Route path = "/completed-development-tickets" component = {ListCompletedTicketsDevelopmentComponent}></Route>
                <Route path = "/completed-deployment-tickets" component = {ListCompletedTicketsDeploymentComponent}></Route>
                <Route path = "/update-ticket/:id" exact component = {UpdateTicketComponent}></Route>
                <Route path = "/assign-ticket/:id" exact component = {AssignTicketComponent}></Route>
                <Route path = "/solution-ticket/:id" exact component = {AddSolutionTicketComponent}></Route>
                <Route path = "/delete-ticket/:id" exact component = {DeleteTicketComponent}></Route>

                <Route path = "/update-ticket-development/:id" exact component = {UpdateTicketDevelopmentComponent}></Route>
                <Route path = "/assign-ticket-development/:id" exact component = {AssignTicketDevelopmentComponent}></Route>
                <Route path = "/solution-ticket-development/:id" exact component = {AddSolutionTicketDevelopmentComponent}></Route>
                <Route path = "/delete-ticket-development/:id" exact component = {DeleteTicketDevelopmentComponent}></Route>
                
                <Route path = "/update-ticket-deployment/:id" exact component = {UpdateTicketDeploymentComponent}></Route>
                <Route path = "/assign-ticket-deployment/:id" exact component = {AssignTicketDeploymentComponent}></Route>
                <Route path = "/solution-ticket-deployment/:id" exact component = {AddSolutionTicketDeploymentComponent}></Route>
                <Route path = "/delete-ticket-deployment/:id" exact component = {DeleteTicketDeploymentComponent}></Route>
              </Switch>
            </div>
          <FooterComponent />
      </Router>
    </div>
     

  );
}

export default App;
