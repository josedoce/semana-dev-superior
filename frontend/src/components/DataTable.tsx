import axios from "axios";
import { useEffect, useState } from "react"
import { SalePage } from "types/sale"
import { formatLocalDate } from "utils/format";
import { BASE_URL } from "utils/requests";
import { Pagination } from "./Pagination";

export function DataTable() {
  const[activePage, setActivePage] = useState(0);
  const[page, setPage] = useState<SalePage>({
    first: true,
    last: true,
    number: 0,
    totalElements: 0,
    totalPages: 0,
  });

  useEffect(()=>{
    axios.get<SalePage>(`${BASE_URL}/sales?page=${activePage}&size=20&sort=date,desc`)
    .then(response=>{
      setPage(response.data);
    });
  },[activePage]);

  function changePage(index:number){
    setActivePage(index);
  }
  return(
    <>
      <Pagination page={page} onPageChange={changePage}/>
      <div className="table-responsive">
        <table className="table table-striped table-sm">
          <thead>
            <tr>
              <th>Data</th>
              <th>Vendedor</th>
              <th>Clientes visitados</th>
              <th>Negócios fechados</th>
              <th>Valor</th>
            </tr>
          </thead>
          <tbody>
            {
              page.content?
                page.content.map((e)=>(
                    <tr key={e.id}>
                        <td>{formatLocalDate(e.date, 'dd/MM/yyyy')}</td>
                        <td>{e.seller.name}</td>
                        <td>{e.visited}</td>
                        <td>{e.deals}</td>
                        <td>R$ {e.amount.toFixed(2)}</td>
                    </tr>
                ))
              :<tr>
                  <td rowSpan={1} colSpan={5}>
                    <div className="text-center text-primary p-2">
                      <div className="spinner-border" role="status">
                      </div>
                    </div>
                  </td>
                </tr>
            }  
          </tbody>
        </table>
      </div>
    </>
  )
}