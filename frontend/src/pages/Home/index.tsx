import {Link} from 'react-router-dom';
export function Home(){
  return(
    <div>
      <h2>Home</h2><br/>
      <Link to="/dashboard">Dashboard</Link>
    </div>
  )
}