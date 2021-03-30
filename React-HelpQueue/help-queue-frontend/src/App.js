import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListQueueTicketComponent from './components/ListQueueTicketComponent.jsx';

function App() {
  return (
    <div>
      <Router>
          <HeaderComponent />
            <div className="container">
              <Switch>
                <Route path = "/" component = {ListQueueTicketComponent}></Route>
                <Route path = "/tickets" component = {ListQueueTicketComponent}></Route>

                <ListQueueTicketComponent />
              </Switch>
            </div>
          <FooterComponent />
      </Router>
    </div>
    

  );
}

export default App;
