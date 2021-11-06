import { Dashboard } from 'pages/Dashboard';
import { Home } from 'pages/Home';
import {BrowserRouter,Switch, Route} from 'react-router-dom';

export function Router(){
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Home}/>
        <Route path="/dashboard" component={Dashboard}/>
      </Switch>
    </BrowserRouter>
  );
}