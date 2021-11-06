import {BrowserRouter,Switch, Route} from 'react-router-dom';

export function Router(){
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={()=><h1>Joseildo</h1>}/>
      </Switch>
    </BrowserRouter>
  );
}