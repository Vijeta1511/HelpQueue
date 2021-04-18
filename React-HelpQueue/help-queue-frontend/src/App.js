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
                <Route path = "/update-ticket" component = {UpdateTicketComponent}></Route>
                <Route path = "/assign-ticket" component = {AssignTicketComponent}></Route>
                <Route path = "/solution-ticket" component = {AddSolutionTicketComponent}></Route>
              </Switch>
            </div>
          <FooterComponent />
      </Router>
    </div>
     

  );
}

export default App;
